package com.loic.daily.exercise.dynamicProgramming;

/**
 * https://en.wikipedia.org/wiki/Levenshtein_distance
 */
public class LevenshteinDistance {
  private LevenshteinDistance() {
  }

  /**
   * the minimum number of single-character edits (insertions, deletions or substitutions) required to change one word into the other
   */
  public static int distance(String s1, String s2) {
    //DIS(i, j) is the edit distance of all pairs of prefixes of the strings
    int[][] dis = new int[s1.length() + 1][s2.length() + 1];
    for (int i = 0; i <= s1.length(); i++) {
      for (int j = 0; j <= s2.length(); j++) {
        if (i == 0) {
          //dis(0, j) = j
          dis[0][j] = j;
        } else if (j == 0) {
          //dis(i, 0) = i
          dis[i][0] = i;
        } else {
          //ATTENTION: this i-th character of a String is charAt(i - 1)
          int temp = s1.charAt(i - 1) == s2.charAt(j - 1) ? dis[i - 1][j - 1] : dis[i - 1][j - 1] + 1;
          temp = Math.min(temp, dis[i][j - 1] + 1);
          temp = Math.min(temp, dis[i - 1][j] + 1);
          dis[i][j] = temp;
        }
      }
    }
    return dis[s1.length()][s2.length()];
  }
}

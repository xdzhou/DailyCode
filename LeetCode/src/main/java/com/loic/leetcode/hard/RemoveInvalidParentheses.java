package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 301. Remove Invalid Parentheses
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * <p>
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * <p>
 * Note: The input string may contain letters other than the parentheses ( and ).
 * <p>
 * Example 1:
 * <p>
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 * <p>
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 * <p>
 * Input: ")("
 * Output: [""]
 */
public class RemoveInvalidParentheses {

  /**
   * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution
   * <p>
   * We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
   * The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.
   * <p>
   * To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix. However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.
   * <p>
   * After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string. However, we need to keep another information: the last removal position. If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
   * For this, we keep tracking the last removal position and only remove ‘)’ after that.
   * <p>
   * Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
   * The answer is: do the same from right to left.
   * However a cleverer idea is: reverse the string and reuse the code!
   * Here is the final implement in Java.
   */
  public static List<String> resolve(String s) {
    List<String> result = new ArrayList<>();
    removal(result, new StringBuilder(s), 0, 0, '(', ')');
    return result;
  }

  private static void removal(List<String> result, StringBuilder sb, int scanPosition, int lastRemovalIndex, char openChar, char closeChar) {
    int delta = 0;
    // remove addition close char
    for (int i = scanPosition; i < sb.length(); i++) {
      if (sb.charAt(i) == openChar) {
        delta++;
      } else if (sb.charAt(i) == closeChar) {
        delta--;
      }
      if (delta < 0) {
        for (int j = lastRemovalIndex; j <= i; j++) {
          if (sb.charAt(j) == closeChar && (j == lastRemovalIndex || sb.charAt(j - 1) != closeChar)) {
            sb.deleteCharAt(j);
            removal(result, sb, i, j, openChar, closeChar);
            sb.insert(j, closeChar);
          }
        }
        //Attention: we need return here. As invalid close char has been resolved, no need to continue the scan
        return;
      }
    }
    if (openChar == '(') {
      // remove addition open char
      removal(result, sb.reverse(), 0, 0, closeChar, openChar);
      sb.reverse();
    } else {
      result.add(sb.reverse().toString());
      sb.reverse();
    }
  }
}

package com.sky.recursion;

import com.loic.algo.common.Pair;
import com.sky.problem.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class WordBreak implements Problem<Pair<String, Set<String>>, List<String>> {
    @Override
    public List<String> resolve(Pair<String, Set<String>> param) {
        Objects.requireNonNull(param);
        return wordBreak(param.getFirst(), param.getSecond());
    }

    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<String>();
        biDirectionGenerateWords(new ArrayList<String>(), new ArrayList<String>(), s, wordDict, result);
        return result;
    }

    private void biDirectionGenerateWords(List<String> preWords, List<String> postWords, String s, Set<String> wordDict,
                                          List<String> result) {
        if (s == null || s.equals("")) {
            StringBuilder sb = new StringBuilder();
            for (String word : preWords) {
                sb.append(word).append(" ");
            }
            for (String word : postWords) {
                sb.append(word).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            result.add(sb.toString());
        } else {
            for (String begin : wordDict) {
                if (s.startsWith(begin)) {
                    if (s.length() == begin.length()) {
                        preWords.add(begin);
                        biDirectionGenerateWords(preWords, postWords, null, wordDict, result);
                        preWords.remove(preWords.size() - 1);
                    } else {
                        for (String end : wordDict) {
                            if (s.endsWith(end)) {
                                int delta = s.length() - begin.length() - end.length();
                                if (delta >= 0) {
                                    preWords.add(begin);
                                    postWords.add(0, end);
                                    String nextString = delta == 0 ? null
                                            : s.substring(begin.length(), s.length() - end.length());
                                    biDirectionGenerateWords(preWords, postWords, nextString, wordDict, result);
                                    postWords.remove(0);
                                    preWords.remove(preWords.size() - 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

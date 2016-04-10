package com.sky.exercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sky.problem.Problem;

/**
 * Remove the minimum number of invalid parentheses in order to make the input
 * string valid. Return all possible results.
 * 
 * @link https://leetcode.com/problems/remove-invalid-parentheses/
 *
 */
public class RemoveInvalidParentheses implements Problem<String, List<String>> {
	private boolean needRemoveOpenParentheses = false;

	@Override
	public List<String> resolve(String s) {
		List<String> result = new ArrayList<String>();
		int len = s.length();
		if (len > 0) {
			Set<String> list = removeCloseParenthesesInvalid(s);
			if (needRemoveOpenParentheses && !list.isEmpty()) {
				String[] temp = new String[list.size()];
				list.toArray(temp);
				list.clear();
				for (String item : temp) {
					list.addAll(removeOpenParenthesesInvalid(item));
				}
			}
			result.addAll(list);
		}
		if (result.isEmpty()) {
			result.add("");
		}
		return result;
	}

	/**
	 * remove all close parentheses invalid
	 */
	private Set<String> removeCloseParenthesesInvalid(String originString) {
		Set<String> result = new HashSet<String>();

		int open = 0, close = 0;
		int len = originString.length();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < len; i++) {
			char c = originString.charAt(i);
			sb.append(c);
			if (c == '(') {
				open++;
			} else if (c == ')') {
				close++;
			} else {
				continue;
			}
			if (close > open) {
				if (result.isEmpty()) {
					result.addAll(removeChar(sb.toString(), ')'));
				} else {
					String[] temp = new String[result.size()];
					result.toArray(temp);
					result.clear();

					for (String s : temp) {
						result.addAll(removeChar(s + sb.toString(), ')'));
					}
				}
				sb.setLength(0);
				close--;
			}
		}

		if (sb.length() > 0) {
			if (result.isEmpty()) {
				result.add(sb.toString());
			} else {
				String[] temp = new String[result.size()];
				result.toArray(temp);
				result.clear();

				for (String s : temp) {
					result.add(s + sb.toString());
				}
			}
		}

		needRemoveOpenParentheses = open > close;

		return result;
	}

	/**
	 * remove all open parentheses invalid
	 */
	private Set<String> removeOpenParenthesesInvalid(String originString) {
		Set<String> result = new HashSet<String>();

		int open = 0, close = 0;
		int len = originString.length();

		StringBuilder sb = new StringBuilder();

		for (int i = len - 1; i >= 0; i--) {
			char c = originString.charAt(i);
			sb.insert(0, c);
			if (c == '(') {
				open++;
			} else if (c == ')') {
				close++;
			} else {
				continue;
			}
			if (open > close) {
				if (result.isEmpty()) {
					result.addAll(removeChar(sb.toString(), '('));
				} else {
					String[] temp = new String[result.size()];
					result.toArray(temp);
					result.clear();

					for (String s : temp) {
						result.addAll(removeChar(sb.toString() + s, '('));
					}
				}

				sb.setLength(0);
				open--;
			}
		}
		if (sb.length() > 0) {
			if (result.isEmpty()) {
				result.add(sb.toString());
			} else {
				String[] temp = new String[result.size()];
				result.toArray(temp);
				result.clear();

				for (String s : temp) {
					result.add(sb.toString() + s);
				}
			}
		}
		return result;
	}

	private List<String> removeChar(String s, char c) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < s.length(); i++) {
			if (c == s.charAt(i) && (i + 1 >= s.length() || s.charAt(i + 1) != c)) {
				result.add(removeCharAt(s, i));
			}
		}
		return result;
	}

	private String removeCharAt(String s, int index) {
		return new StringBuilder(s).deleteCharAt(index).toString();
	}

}

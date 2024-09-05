package com.example.arithmeticTest.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author liqi
 * create  2021-09-05 10:51
 */
public class StackTest {

    public static void main(String[] args) {
        boolean valid = isValid("(}");
        System.out.println(valid);
    }


    /**
     * 3.2.2. 检查符号是否成对出现
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断该字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        // 括号之间的对应规则
        HashMap<Character, Character> mappings = new HashMap<Character, Character>();
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (mappings.containsKey(chars[i])) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != mappings.get(chars[i])) {
                    return false;
                }
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
    }
}

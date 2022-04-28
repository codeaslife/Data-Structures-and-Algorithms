package com.codeaslife.Structures.Stacks;

import java.util.Stack;

public class StringReverser {

    public String reverse(String str) {
        if (str == null) {
            throw new IllegalStateException();
        }

        Stack<Character> stack = new Stack<>();

//        for (int i = 0; i < str.length(); i++) {
//            stack.push(str.charAt(i));
//        }

        for (char ch : str.toCharArray()) {
            stack.push(ch);
        }

//        String reversed = "";
        StringBuffer reversed = new StringBuffer();
        while (!stack.empty()) {
//            reversed += stack.pop();
            reversed.append(stack.pop());
        }
        return reversed.toString();
    }
}

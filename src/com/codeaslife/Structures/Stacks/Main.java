package com.codeaslife.Structures.Stacks;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
//        Stack<Integer> stack = new Stack<>();
//        stack.push(10);
//        stack.push(20);
//        stack.push(30);
//        System.out.println(stack);
//        Integer top = stack.pop();
//        System.out.println(top);
//        System.out.println(stack);
//        stack.peek(); // without removing top value

        String str = "abcd";
        StringReverser reverser = new StringReverser();
        String result = reverser.reverse(null);
        System.out.println(result);
    }
}

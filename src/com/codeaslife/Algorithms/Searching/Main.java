package com.codeaslife.Algorithms.Searching;

public class Main {
    public static void main(String[] args) {
        int[] numbers = { 1, 3, 4, 5, 8 };
        Search search = new Search();
        int index = search.jumpSearch(numbers, 5);
        System.out.println(index);
    }
}

package com.codeaslife.Algorithms.Sorting;

public class BubbleSort {
    public void sort(int[] array) {
        boolean isSorted;
        for (int i = 0; i < array.length; i++) {
            isSorted = true; // 每次循环的时候首先看是否已经有序
            for (int j = 1; j < array.length - i; j++) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                    isSorted = false;
                }
            }
            if (isSorted) {
                return;
            }
        }
    }

    public void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}

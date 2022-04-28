package com.codeaslife.Algorithms.Sorting;

public class MergeSort {

    public void sort(int[] array) {
        // Divide and Conquer 分治
        // Dividing and Merging
        // O(nlogn)
        // Space O(n)
        // in-place mergesort - google
        // Divide this array into half
        // Base Condition
        if (array.length < 2) {
            return;
        }

        int middle = array.length / 2;
        int[] left = new int[middle];
        for (int i = 0; i < middle; i++) {
            left[i] = array[i];
        }

        int[] right = new int[array.length - middle];
        for (int i = middle; i < array.length; i++) {
            right[i - middle] = array[i];
        }
        // Sort each half
        sort(left);
        sort(right);

        // Merge the result
        merge(left, right, array);
    }

    public void merge(int[] left, int[] right, int[] result) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // 当上述两个数组的长度不一致时，还需要如下操作
        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }
}

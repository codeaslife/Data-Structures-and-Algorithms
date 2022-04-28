package com.codeaslife.Structures.Heaps;

public class MaxHeap {
    // 这里使用静态的原因，就是为了之后不用实例化对象就可以调用
    public static void heapify(int[] array) {
        for (int i = 0; i < array.length; i++) {
            heapify(array, i);
        }
    }

    private static void heapify(int[] array, int index) {
        int largerIndex = index;

        int leftIndex = index * 2 + 1;
        if (leftIndex < array.length &&
                array[leftIndex] > array[largerIndex]) {
            largerIndex = leftIndex;
        }

        int rightIndex = index * 2 + 2;
        if (rightIndex < array.length && array[rightIndex] > array[largerIndex]) {
            largerIndex = rightIndex;
        }

        // 终止条件
        if (index == largerIndex) { // 如果该项大于两个孩子节点，啥事不做，直接返回
            return;
        }
        swap(array, index, largerIndex);
        heapify(array, largerIndex);

    }

    private static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}

package com.codeaslife.Structures.Heaps;

public class Heap {
    private int[] items = new int[10];
    private int size; // remove方法不是物理删除，只是减少size

    public void insert(int value) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        items[size++] = value;

        bubbleUp();
    }

    // 总是移除最大的值，也就是堆顶的值（对于最大堆）
    public int remove() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        // 移除堆顶之前，用root存储起来
        int root = items[0];

        // 将最后一个元素移动到堆顶，然后执行下沉操作
        items[0] = items[--size];

        bubbleDown();

        return root; // 最后返回堆顶元素
    }

    private void bubbleDown() {
        // items(root) < children, bubbleDown()
        int index = 0;
        while (index <= size && !isValidParent(index)) { // index可能会越界，需要加上index <= size
            int largerChildIndex = largerChildIndex(index);
            swap(index, largerChildIndex);
            index = largerChildIndex; // reset index to largerChildIndex
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int largerChildIndex(int index) {
        if (!hasLeftChild(index)) {
            return index;
        }

        if (!hasRightChild(index)) {
            return leftChildIndex(index);
        }

        return (leftChild(index) > rightChild(index)) ?
                leftChildIndex(index) : rightChildIndex(index);
    }

    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) <= size;
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) <= size;
    }

    private boolean isValidParent(int index) {
        if (!hasLeftChild(index)) {
            return true;
        }
        boolean isValid = items[index] >= items[leftChild(index)];

        if (hasRightChild(index)) {
            isValid &= items[index] >= items[rightChild(index)];
        }

        return isValid;
    }


    private int leftChild(int index) {
        return items[leftChildIndex(index)];
    }

    private int rightChild(int index) {
        return items[rightChildIndex(index)];
    }

    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    public boolean isFull() {
        return size == items.length;
    }

    private void bubbleUp() {
        // newItem > parent
        int index = size - 1;
        while (index > 0 && items[index] > items[parent(index)]) {
            // first swap the value
            swap(index, parent(index));
            // then reset the index
            index = parent(index);
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int first, int second) {
        int temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }
}

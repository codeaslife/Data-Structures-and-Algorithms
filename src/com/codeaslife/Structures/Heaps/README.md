# 堆

实现一个堆

- insert()

```java
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
```

- remove()

```java
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
    public void remove() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        // 将最后一个元素移动到堆顶，然后执行下沉操作
        items[0] = items[--size];

        // items(root) < children, bubbleDown()
        int index = 0;
        while (index <= size && !isValidParent(index)) { // index可能会越界，需要加上index <= size
            int largerChildIndex = largerChildIndex(index);
            swap(index, largerChildIndex);
            index = largerChildIndex; // reset index to largerChildIndex
        }
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private int largerChildIndex(int index) {
        return (leftChild(index) > rightChild(index)) ?
                leftChildIndex(index) : rightChildIndex(index);
    }

    private boolean isValidParent(int index) {
        return items[index] >= items[leftChild(index)] &&
                items[index] >= items[rightChild(index)];
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
```

有可能一个节点没有孩子节点，或者只有一个节点，需要处理这种情况。
直接求出左右子节点的Index，然后和size比较。
在 largerChildIndex 和  方法中都是假设存在两个节点都存在的情况下，现在需要如下修改：
```java
public class Heap {
    private int[] items = new int[10];
    private int size; // remove方法不是物理删除，只是减少size
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
        if (!hasRightChild(index)) {
            return items[index] >= items[leftChild(index)];
        }
    
        return items[index] >= items[leftChild(index)] &&
                items[index] >= items[rightChild(index)];
    }
}
```


还可以进一步优化 isValidParent 方法
```java
public class Heap {
    /*
    省略其他部分
    */
    
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
}
```

Main.java
```java
public class Main {

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insert(10);
        heap.insert(5);
        heap.insert(17);
        heap.insert(4);
        heap.insert(22);
        heap.remove();
        System.out.println("Done");
    }
}
```

remove() 演示图



## 如何使用Heap处理数据

堆排序
```java
public class Main {

    public static void main(String[] args) {
        int[] numbers = { 5, 3, 10, 1, 4, 2 };
        Heap heap = new Heap();
        for (int number : numbers) {
            heap.insert(number);
        }
//        while (!heap.isEmpty()) {
//            System.out.println(heap.remove());
//        }
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = heap.remove();
        }
//        // 降序
//        for (int i = numbers.length; i >= 0; i--) {
//            numbers[i] = heap.remove();
//        }
        System.out.println(Arrays.toString(numbers));
    }
}
```


实现一个 PriorityQueue
PriorityQueue.java
```java
import java.util.Arrays;

public class PriorityQueue {

    private int[] items = new int[5];
    private int count;

    public void add(int item) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        int i = shiftItemsToInsert(item);
        items[i] = item;
        count++;
    }

    private int shiftItemsToInsert(int item) {
        int i;
        for (i = count - 1; i >= 0; i--) {
            if (items[i] > item) {
                items[i + 1] = items[i];
            } else {
                break;
            }
        }
        return i + 1;
    }

    public int remove() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return items[--count];
    }

    public boolean isFull() {
        return count == items.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
```

Main.java
```java
public class Main {

    public static void main(String[] args) {
        // insert: O(n)
        // delete: O(1)    
        PriorityQueue queue = new PriorityQueue();
        queue.add(30);
        queue.add(20);
        queue.add(10);
        queue.add(40);
        System.out.println(queue);
    }
}
```

删除元素的时间复杂度为O(1)，没有在物理上删除元素，只是将count减小了。

我们也可以使用堆来实现一个优先队列。
PriorityQueueWithHeap.java
```java
public class PriorityQueueWithHeap {
    private Heap heap = new Heap();

    public void enqueue(int item) {
        heap.insert(item);
    }

    public int dequeue() {
        return heap.remove();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
}
```
将插入的时间复杂度降低到O(log n)
这是由于我们每插入一个元素，他就会存储在最后一个节点？如果不是正确的位置，就会使用BubbleUp方法直到移动到正确的位置。
log n is equal to height of a complete tree
删除元素的时间复杂度也是O(log n)

表格比较两者


如何实现 Heapify 函数？
MaxHeap.java
```java
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
```


```java
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] numbers = { 5, 3, 8, 4, 1, 2 };
        MaxHeap.heapify(numbers);
        System.out.println(Arrays.toString(numbers));
    }
}
```
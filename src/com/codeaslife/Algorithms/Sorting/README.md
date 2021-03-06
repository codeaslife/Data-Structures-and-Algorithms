# 排序算法


比较排序：
- 冒泡排序
- 选择排序
- 插入排序
- 归并排序
- 快速排序

非比较排序：
- 计数排序
- 桶排序
- 基数排序

|排序算法|时间复杂度|空间复杂度|是否稳定|
|------|------|------|------|
|冒泡排序|O(n^2)|O(1)|稳定|
|选择排序|O(n^2)|O(1)|不稳定|
|插入排序|O(n^2)|O(1)|稳定|
|归并排序|O(nlogn)|O(n)|稳定|
|快速排序|O(nlogn)|O(logn)|不稳定|
|堆排序|O(nlogn)|O(1)|不稳定|
|计数排序||||
|桶排序||||
|基数排序||||


目前没有找到时间复杂度为O(nlogn)，额外空间复杂度为O(1)，又稳定的算法。
> 注：快速排序可以不适用额外空间来实现，搜索



## 冒泡排序



## 选择排序
在0到N-1区间首先选出最小值，与第一个位置交换。


## 插入排序



## 归并排序

如何merge是保证稳定性的关键，当我们比较左右的时候，当遇到相等的时候，先拷贝左边的值。
归并排序的额外空间复杂度可以变为O(1)，但是非常难，不需要掌握。
> 归并排序 内部缓存法
> 小河问题，不是稳定性。

## 快速排序
快速排序的常数项是最低的，实际是最快的排序，但是不稳定。

快速排序不具备稳定性
小于区域] 6 7 6 6 3
> 快速排序可以解决稳定性问题


## 堆排序
不具备稳定性。
如果有空间限制，就可以使用堆排序。


## 计数排序


什么时候使用计数排序？
- Allocating extra sapce is not a issue
- Values are positive integers
- Most of the values in the range are present







## 桶排序



## 工程上对排序的优化
- 重复利用O(nlogn)和O(n^2)排序各自的优势
- 稳定性的考虑

```java
```

大调度利用时间复杂度低的优势。
小样本下利用插入排序常数项低的优势。
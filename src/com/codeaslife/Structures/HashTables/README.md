# 哈希表

哈希表介绍：
使用层面上可以理解为一种集合结构。
有key，无value，HashSet（C++中叫UnOrderSet或者UnSortedSet）
有key，有value，HashMap（C++中叫UnOrderMap或者UnSortedMap）
有无伴随数据，是HashMap和HashSet唯一的区别，底层的实际结构是一回事。
使用哈希表增删改查的操作，可以认为时间复杂度为O(1)，但是常数时间比较大。
放在哈希表中的数据，如果是基础数据类型，内部按值传递
如果是引用数据类型，内部引用传递
内存地址占用空间8字节。

有序表介绍：
有序表在使用层面上可以理解为
TreeSet和TreeMap
红黑树、AVL树、size-balance-tree和跳表都属于有序表结构，只是底层实现不同。（性能都一样）
放入有序表的东西，如果是基础数据类型，内部按值传递，内部占用空间就是这个值的大小。
放入有序表的东西，如果不是引用数据类型，必须提供比较器，内部按引用传递，内部占用空间就是该对象的内存地址的大小。

有序表的固定操作
put()
get()
remove()
containsKey()
firstKey()
lastKey()
floorKey()
ceilingKey()
上述所有操作的时间复杂度都是O(logN)，N为有序表包含的记录数。

package com.zhongs.sort;

import java.util.Arrays;
// 选择排序
//每次最小/大排在相应的位置
//效率O（n的2次方），适用于排序小的列表。 

//首先，找到数组中最小的那个元素，其次，将它和数组的第
//一个元素交换位置（如果第一个元素就是最小元素那么它就和自己交换）。再次，在剩下的元素中
//找到最小的元素，将它与数组的第二个元素交换位置。如此往复，直到将整个数组排序。这种方法
//叫做选择排序，因为它在不断地选择剩余元素之中的最小者。

// 索引index 右侧遍历交换

// 对于长度为N 的数组，选择排序需要大约N2/2 次比较和N 次交换。

//1. 运行时间和输入无关。为了找出最小的元素而扫描一遍数组并不能为下一遍扫描提供什么信息。
//这种性质在某些情况下是缺点，因为使用选择排序的人可能会惊讶地发现，一个已经有序的数组或
//是主键全部相等的数组和一个元素随机排列的数组所用的排序时间竟然一样长！我们将会看到，其
//他算法会更善于利用输入的初始状态。
//2. 数据移动是最少的。每次交换都会改变两个数组元素的值，因此选择排序用了N 次交换——交
//换次数和数组的大小是线性关系。我们将研究的其他任何算法都不具备这个特征（大部分的增长数
//量级都是线性对数或是平方级别）。


public class SelectSort extends AbstractSort {

    public void sort(int[] a) { // 将a[]按升序排列
        int N = a.length; // 数组长度
        for (int i = 0; i < N; i++) { // 将a[i]和a[i+1..N]中最小的元素交换
            int min = i; // 最小元素的索引
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exchange(a, i, min);
        }
    }
    
    public static void main(String[] args) {
        AbstractSort so= new SelectSort();
        so.sort(a);
        so.show(a);
    }

    public static void main1(String[] args) {
        // 另外一种简单 实现
        int[] a = new int[] { 5, 4, 8, 8, 9, 6, 7, 6, 3, 1, };
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int index = i;
            for (int j = i + 1; j < n; j++) {
                // 每次扫描选择最小项
                if (a[j] < a[index]) {
                    index = j;
                }
            }
            if (index != i) {
                // 找到最小项交换，即将这一项移到列表中的正确位置
                int temp;
                temp = a[i];
                a[i] = a[index];
                a[index] = temp;
            }
        }
        System.out.println(Arrays.toString(a));
    }
}

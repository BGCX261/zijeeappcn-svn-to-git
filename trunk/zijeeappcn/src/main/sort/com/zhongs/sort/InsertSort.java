package com.zhongs.sort;

import java.util.Arrays;

//插入排序——将下一个插入已排好的序列中 
//最佳效率O（n）；最糟效率O（n的2次方）与冒泡、选择相同，适用于排序小列表 
//若列表基本有序，则插入排序比冒泡、选择更有效率


//插入排序（Insertion Sortion）的基本思想是：把n个待排序的元素看成一个有序表和一个无序表，
//开始有序表只包含一个元素，无序表中包含n-1个元素，排序过程中每次从无序表中取出第一个元素，
//把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。

// 索引index 左侧排序交换

//对于随机排列的长度为N 且主键不重复的数组，平均情况下插入排序需要～ N2/4 次比
//较以及～ N2/4 次交换。最坏情况下需要～ N2/2 次比较和～ N2/2 次交换，最好情况下需要N-1
//次比较和0 次交换。

//插入排序所需的时间取决于输入中元素的初始顺序。例如，对一个很大
//且其中的元素已经有序（或接近有序）的数组进行排序将会比对随机顺序的数组或是逆序数组进行
//排序要快得多。
public class InsertSort extends AbstractSort{
    
    public void sort(int[] a) {
        // 将a[]按升序排列
        int N = a.length;
        for (int i = 1; i < N; i++) { // 将 a[i] 插入到 a[i-1]、a[i-2]、a[i-3]...之中
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exchange(a, j, j - 1);
        }
    }

	public static void main(String[] args) {
	    // 另外一种简单的实现方式
		int n = a.length;
		for (int i = 1; i < n; i++) {
			int temp = a[i], j = i;
			if (a[j - 1] > temp) {
				while (j >= 1 && a[j - 1] > temp) {
					a[j] = a[j - 1];
					j--;
				}
			}
			a[j] = temp;
		}

		System.out.println(Arrays.toString(a));
	}

}

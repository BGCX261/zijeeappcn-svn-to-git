package com.zhongs.sort;

import java.util.Arrays;

// 归并排序
//归并排序是另一类不同的排序方法，所谓归并,就是把两个或者两个以上的有序表合并成一个新的有序表的过程。

//归并排序的基本思想:
//将一个含有N个序列的有序表看成是n个长度为1的有序表,然后两两归并,得到[n/2]个长度为2的有序表,然后再两两归并,
//直到得到一个长度为n的有序表为止。

//(1)定义指针i,指向第一个序列表的第一个元素
//(2)定义指针j，指向第二个序列表的第一个元素 
//(3)比较i,j指向的元素大小，若前者大，将后者插入到新表中 否则，把前者插入到后表中
//(4)直到取完第一个序列表或者第二个序列表为止

//性能分析:
//    时间复杂度:
//        由于归并的趟数,等于树的高度Logn,每趟归并需要移动记录n次,因此归并排序的时间复杂度为nlogn.
//    空间复杂度:
//        从上面的算法可以看出,需要一个辅助空间num2,其长度等于n，所以归并排序的辅助空间为O(n).
//    稳定性:
//        归并排序不涉及到交换,因此它是一种稳定的排序算法。

//归并排序是典型的用空间去换取时间,它的时间开销比简单排序要优越，但需要与序列等长的辅助空间。

//JDK Arrays 类 
public class MergeSort extends AbstractSort {
    /**
     * Tuning parameter: list size at or below which insertion sort will be
     * used in preference to mergesort or quicksort.
     */
    private static final int INSERTIONSORT_THRESHOLD = 7;

    public void sort(int[] a) {
        int[] aux = (int[]) a.clone();
        mergeSort(aux, a, 0, a.length);
    }

    private void mergeSort(int[] src, int[] dest, int low, int high) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < INSERTIONSORT_THRESHOLD) {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && dest[j - 1] > dest[j]; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int destLow = low;
        int destHigh = high;
//        low += off;
//        high += off;
        int mid = (low + high) >>> 1;// 移位
//        System.out.println("off:" + off);
//        mergeSort(dest, src, low, mid, -off);
//        mergeSort(dest, src, mid, high, -off);
        mergeSort(dest, src, low, mid);
        mergeSort(dest, src, mid, high);

        // If list is already sorted, just copy from src to dest. This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (src[mid - 1] <= src[mid]) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }
        System.out.println(":"+Arrays.toString(a));
        // Merge sorted halves (now in src) into dest
        for (int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && src[p] <= src[q])
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(int[] x, int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    public static void main(String[] args) {

        MergeSort so = new MergeSort();
        so.sort(a);
        System.out.println(Arrays.toString(a));
    }
}

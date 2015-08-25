package com.zhongs.sort;

import java.util.Arrays;

public abstract class AbstractSort {

//    static int[] a = new int[] { 5, 4, 8, 8, 9, 6, 7, 6, 3, 1, };
    static int[] a = new int[] { 12, 23, 9, 24, 15, 3, 18, };

    /* 请见算法2.1、算法2.2、算法2.3、算法2.4、算法2.5或算法2.7 */
    public void sort(int[] data) {
    }

    public boolean less(int v, int w) {
        return v < w;
    }

    public void exchange(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public void show(int[] a) { // 在单行中打印数组
//        for (int i = 0; i < a.length; i++)
//            System.out.println(a[i] + " ");
//        System.out.println();
        System.out.println(Arrays.toString(a));
    }

    public boolean isSorted(int[] a) { // 测试数组元素是否有序
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }

}

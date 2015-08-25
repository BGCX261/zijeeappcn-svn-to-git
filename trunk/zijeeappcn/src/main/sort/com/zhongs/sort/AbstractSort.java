package com.zhongs.sort;

import java.util.Arrays;

public abstract class AbstractSort {

//    static int[] a = new int[] { 5, 4, 8, 8, 9, 6, 7, 6, 3, 1, };
    static int[] a = new int[] { 12, 23, 9, 24, 15, 3, 18, };

    /* ����㷨2.1���㷨2.2���㷨2.3���㷨2.4���㷨2.5���㷨2.7 */
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

    public void show(int[] a) { // �ڵ����д�ӡ����
//        for (int i = 0; i < a.length; i++)
//            System.out.println(a[i] + " ");
//        System.out.println();
        System.out.println(Arrays.toString(a));
    }

    public boolean isSorted(int[] a) { // ��������Ԫ���Ƿ�����
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }

}

package com.zhongs.sort;

// 快速排序

public class QuickSort_Improved extends AbstractSort {
    private static int MAX_STACK_SIZE = 4096;
    private static int THRESHOLD = 10;

    public void sort(int[] data) {
        int[] stack = new int[MAX_STACK_SIZE];

        int top = -1;
        int pivot;
        int pivotIndex, l, r;

        stack[++top] = 0;
        stack[++top] = data.length - 1;

        while (top > 0) {
            int j = stack[top--];
            int i = stack[top--];

            pivotIndex = (i + j) / 2;
            pivot = data[pivotIndex];

            exchange(data, pivotIndex, j);

            // partition
            l = i - 1;
            r = j;
            do {
                while (less(data[++l], pivot))
                    ;
                while ((r != 0) && !less(data[--r], pivot))
                    ;
                exchange(data, l, r);
            } while (l < r);
            exchange(data, l, r);
            exchange(data, l, j);

            if ((l - i) > THRESHOLD) {
                stack[++top] = i;
                stack[++top] = l - 1;
            }
            if ((j - l) > THRESHOLD) {
                stack[++top] = l + 1;
                stack[++top] = j;
            }

        }
        insertSort(data);
    }

    /**
     * @param data
     */
    private void insertSort(int[] data) {
        int temp;
        for (int i = 1; i < data.length; i++) {
            for (int j = i; (j > 0) && less(data[j], data[j - 1]); j--) {
                exchange(data, j, j - 1);
            }
        }
    }
}

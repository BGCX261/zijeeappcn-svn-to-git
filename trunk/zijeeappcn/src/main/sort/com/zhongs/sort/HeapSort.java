package com.zhongs.sort;

import java.util.Arrays;

//若将此序列所存储的向量R[1..n]看做是一棵完全二叉树的存储结构，则堆实质上是满足如下性质的完全二叉树：
//树中任一非叶子结点的关键字均不大于（或不小于）其左右孩子（若存在）结点的关键字。

//大根堆和小根堆：根结点（亦称为堆顶）的关键字是堆里所有结点关键字中最小者的堆称为小根堆，又称最小堆。
//根结点（亦称为堆顶）的关键字是堆里所有结点关键字中最大者，称为大根堆，又称最大堆。
//注意：①堆中任一子树亦是堆。②以上讨论的堆实际上是二叉堆（Binary Heap），类似地可定义k叉堆。 
public class HeapSort {

    private static int N = 10;

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = ((Double) (Math.random() * 1000)).intValue();
        }
        initHeap(arr, N);
        System.out.println(Arrays.toString(arr));
        System.out.println("init check's result is " + checkInit(arr));
        doSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("result check's result is " + checkResult(arr));
    }

    private static boolean checkInit(int[] arr) {
        boolean result = true;
        for (int i = 1; i <= N; i++) {
            result = result && isHeap(arr, i, N);
        }
        return result;
    }

    private static boolean checkResult(int[] arr) {
        boolean result = true;
        for (int i = 1; i <= N - 1; i++) {
            result = result && (arr[i] <= arr[i + 1]);
        }
        return result;
    }

    private static void doSort(int[] arr) {
        int maxIndex = N;
        while (maxIndex > 0) {
            arr[0] = arr[maxIndex];
            arr[maxIndex] = arr[1];
            arr[1] = arr[0];
            maxIndex--;
            buildHeap(arr, 1, maxIndex);
        }
    }

    private static void initHeap(int[] arr, int maxIndex) {
        for (int p = maxIndex / 2; p >= 1; p--) {
            buildHeap(arr, p, maxIndex);
        }
    }

    private static boolean isHeap(int[] arr, int i, int maxIndex) {
        boolean result = false;
        if (i > maxIndex / 2) {
            result = true;
        } else {
            if (arr[i] >= arr[2 * i] && ((2 * i + 1 > maxIndex) || arr[i] >= arr[2 * i + 1])) {
                result = true;
            }
        }
        return result;
    }

    private static void buildHeap(int[] arr, int p, int maxIndex) {
        if (!isHeap(arr, p, maxIndex)) {
            int larger = p * 2;
            if (p * 2 + 1 <= maxIndex && arr[p * 2] < arr[p * 2 + 1]) {
                larger = p * 2 + 1;
            }
            arr[0] = arr[larger];
            arr[larger] = arr[p];
            arr[p] = arr[0];
            buildHeap(arr, larger, maxIndex);
        }
    }

}

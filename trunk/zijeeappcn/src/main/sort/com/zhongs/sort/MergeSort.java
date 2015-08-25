package com.zhongs.sort;

import java.util.Arrays;

// �鲢����
//�鲢��������һ�಻ͬ�����򷽷�����ν�鲢,���ǰ����������������ϵ������ϲ���һ���µ������Ĺ��̡�

//�鲢����Ļ���˼��:
//��һ������N�����е����������n������Ϊ1�������,Ȼ�������鲢,�õ�[n/2]������Ϊ2�������,Ȼ���������鲢,
//ֱ���õ�һ������Ϊn�������Ϊֹ��

//(1)����ָ��i,ָ���һ�����б�ĵ�һ��Ԫ��
//(2)����ָ��j��ָ��ڶ������б�ĵ�һ��Ԫ�� 
//(3)�Ƚ�i,jָ���Ԫ�ش�С����ǰ�ߴ󣬽����߲��뵽�±��� ���򣬰�ǰ�߲��뵽�����
//(4)ֱ��ȡ���һ�����б���ߵڶ������б�Ϊֹ

//���ܷ���:
//    ʱ�临�Ӷ�:
//        ���ڹ鲢������,�������ĸ߶�Logn,ÿ�˹鲢��Ҫ�ƶ���¼n��,��˹鲢�����ʱ�临�Ӷ�Ϊnlogn.
//    �ռ临�Ӷ�:
//        ��������㷨���Կ���,��Ҫһ�������ռ�num2,�䳤�ȵ���n�����Թ鲢����ĸ����ռ�ΪO(n).
//    �ȶ���:
//        �鲢�����漰������,�������һ���ȶ��������㷨��

//�鲢�����ǵ��͵��ÿռ�ȥ��ȡʱ��,����ʱ�俪���ȼ�����Ҫ��Խ������Ҫ�����еȳ��ĸ����ռ䡣

//JDK Arrays �� 
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
        int mid = (low + high) >>> 1;// ��λ
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

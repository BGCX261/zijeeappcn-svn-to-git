package com.zhongs.sort;

import java.util.Arrays;


// 快速排序是对冒泡排序的一种改进
// 平均时间复杂度是O(n)。

//把整个序列看做一个数组，
//把第零个位置看做中轴，和最后一个比，如果比它小交换，比它大不做任何处理；
//交换了以后再和小的那端比，比它小不交换，比他大交换。
//这样循环往复，一趟排序完成，左边就是比中轴小的，右边就是比中轴大的，
//然后再用分治法，分别对这两个独立的数组进行排序。


//数据结构    不定
//最差时间复杂度  n^2
//最优时间复杂度 nlogn
//平均时间复杂度 nlogn
//最差空间复杂度 根据实现的方式不同而不同

public class QuickSort extends AbstractSort {

    private int partition(int data[], int low, int hight) {
        // 数组的第一个作为中轴
        int key = data[low];
        System.out.println("key:"+key);
        while (low < hight) {

            // 比中轴小的记录移到低端
            while (low < hight && data[hight] >= key)
                hight--;
            data[low] = data[hight];
            System.out.println("A:"+Arrays.toString(data)+"low:"+low+"，height:"+hight);

            // 比中轴大的记录移到高端
            while (low < hight && data[low] <= key)
                low++;
            data[hight] = data[low];
            System.out.println("B:"+Arrays.toString(data)+"low:"+low+"，height:"+hight);
        }
        
        // 中轴记录 位置数据
        data[low] = key;
        System.out.println("DATA："+Arrays.toString(data)+"low:"+low+"，height:"+hight);
        // 返回中轴的位置
        return low;
    }

    public void sort(int data[], int low, int hight) {
        if (low < hight) {
            // 将list数组进行一分为二
            int result = partition(data, low, hight);
            // 对低字表进行递归排序
            sort(data, low, result - 1);
            // 对高字表进行递归排序
            sort(data, result + 1, hight);
        }
//        System.out.println("dd:" + Arrays.toString(data));
    }

    public void sort(int data[]) {
        sort(data, 0, data.length - 1);
    }

    // public void display() {
    // for (int i = 0; i < data.length; i++) {
    // System.out.print(data[i]);
    // System.out.print(" ");
    // }
    // }

    public static void main(String[] args) {
        AbstractSort so = new QuickSort();
//        int[] a = new int[]{7,6};
        System.out.println("DATA:" + Arrays.toString(a));
        so.sort(a);
        so.show(a);
    }

    // public static void main1(String[] args) {
    // QuickSort qs = new QuickSort();
    // int data[] = { 44, 22, 2, 32, 54, 22, 88, 77, 99, 11 };
    // qs.data = data;
    // qs.sort(0, qs.data.length - 1);
    // // qs.display();
    // }
}

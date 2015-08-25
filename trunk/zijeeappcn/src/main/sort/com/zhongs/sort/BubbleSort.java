package com.zhongs.sort;

import java.util.Arrays;

//冒泡（Bubble）排序——挨个比较，小的就交换

//从小到大排序n个数
//效率 O(n的2次方),适用于排序小列表


//冒泡排序算法的运作如下：
//比较相邻的元素。如果第一个比第二个大，就交换他们两个。
//对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
//针对所有的元素重复以上的步骤，除了最后一个。
//持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
public class BubbleSort extends AbstractSort {

    public static void main(String[] args) {
        int[] array = a.clone(); // 将传递的数组参数a克隆对象，不改变a数组的值
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {// 总共进行length -1 趟排序
            for (int j = 0; j < length - 1 - i; j++) {
                if (array[j + 1] <= array[j]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                System.out.println(Arrays.toString(array));
            }
            System.out.println("::"+Arrays.toString(array));
        }
        System.out.println(Arrays.toString(array));
    }

    // public static void main(String[] args) {
    // int[] a = new int[] { 5, 4, 8, 8, 9, 6, 7, 6, 3, 1, };
    // int n = a.length;
    //
    // int temp;
    // for (int i = 0; i < n; i++) {
    // for (int j = i; j < n; j++) {
    // if (a[i] > a[j]) {
    // // 方法一：
    // temp = a[i];
    // a[i] = a[j];
    // a[j] = temp;
    //
    // /*
    // * //方法二: a[i] = a[i] + a[j]; a[j] = a[i] - a[j]; a[i] =
    // * a[i] - a[j];
    // */
    // }
    // System.out.println(Arrays.toString(a));
    // }
    // }
    // System.out.println(Arrays.toString(a));
    // }
}

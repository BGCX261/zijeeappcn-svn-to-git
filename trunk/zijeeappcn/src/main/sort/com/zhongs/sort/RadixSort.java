package com.zhongs.sort;

import java.util.Arrays;

// 基数排序

//基数排序是一种线性排序，分为distribute（分配）和collect（回收）两个阶段。
//算法需要：长度为10的队列。
//每一轮分配，按照radix（基数）分别进入相应队列


//LSD的基数排序适用于位数小的数列，如果位数多的话，使用MSD的效率会比较好。MSD的方式与LSD相反，是由高位数为基底开始进行分配，但在分配之后并不马上合并回一个数组中，而是在每个“桶子”中建立“子桶”，将每个桶子中的数值按照下一数位的值分配到“子桶”中。在进行完最低位数的分配后再合并回单一的数组中。
public class RadixSort {

    // 基于计数排序的基数排序算法
    private static void radixSort(int[] data, int radix, int distance) {
        // array为待排序数组
        // radix，代表基数
        // distance 代表排序元素的位数

        int length = data.length;
        int[] temp = new int[length];// 用于暂存元素
        int[] count = new int[radix];// 用于计数排序
        int divide = 1;

        for (int i = 0; i < distance; i++) {

            System.arraycopy(data, 0, temp, 0, length);
            Arrays.fill(count, 0);

            // 算每一位的个数
            for (int j = 0; j < length; j++) {
                int tempKey = (temp[j] / divide) % radix;
                count[tempKey]++;
            }
            System.out.println(":"+Arrays.toString(count));
             // 算data的索引index 值
            for (int j = 1; j < radix; j++) {
                count[j] = count[j] + count[j - 1];
            }
            System.out.println(":"+Arrays.toString(count));
            // 个人觉的运用计数排序实现计数排序的重点在下面这个方法
            for (int j = length - 1; j >= 0; j--) {
                int tempKey = (temp[j] / divide) % radix;
                count[tempKey]--;
                data[count[tempKey]] = temp[j];
            }

            divide = divide * radix;
            
            System.out.println(i+":"+Arrays.toString(data));
        }
    }
    
    public static void sort(int[] number, int d) {
        int k = 0;
        int n = 1;
        int m = 1;// 控制键值排序依据在哪一位
        int[][] temp = new int[number.length][number.length];
        int[] order = new int[number.length];
        while (m <= d) {
            for (int i = 0; i < number.length; i++) {
                int lsd = ((number[i] / n) % 10);
                temp[lsd][order[lsd]] = number[i];
                order[lsd]++;
            }
            for (int i = 0; i < d; i++) {
                if (order[i] != 0)
                    for (int j = 0; j < order[i]; j++) {
                        number[k] = temp[i][j];
                        k++;
                    }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        int[] data = { 3, 2, 3, 2, 5, 333, 45566, 2345678, 78, 990, 12, 432, 56 };
        radixSort(data, 10, 7);
        System.out.println(Arrays.toString(data));
        
//        sort(data,7);
        System.out.println(Arrays.toString(data));
        

    }

}

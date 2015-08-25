package com.zhongs.sort;

import java.util.Arrays;

// ��������

//����������һ���������򣬷�Ϊdistribute�����䣩��collect�����գ������׶Ρ�
//�㷨��Ҫ������Ϊ10�Ķ��С�
//ÿһ�ַ��䣬����radix���������ֱ������Ӧ����


//LSD�Ļ�������������λ��С�����У����λ����Ļ���ʹ��MSD��Ч�ʻ�ȽϺá�MSD�ķ�ʽ��LSD�෴�����ɸ�λ��Ϊ���׿�ʼ���з��䣬���ڷ���֮�󲢲����Ϻϲ���һ�������У�������ÿ����Ͱ�ӡ��н�������Ͱ������ÿ��Ͱ���е���ֵ������һ��λ��ֵ���䵽����Ͱ���С��ڽ��������λ���ķ�����ٺϲ��ص�һ�������С�
public class RadixSort {

    // ���ڼ�������Ļ��������㷨
    private static void radixSort(int[] data, int radix, int distance) {
        // arrayΪ����������
        // radix���������
        // distance ��������Ԫ�ص�λ��

        int length = data.length;
        int[] temp = new int[length];// �����ݴ�Ԫ��
        int[] count = new int[radix];// ���ڼ�������
        int divide = 1;

        for (int i = 0; i < distance; i++) {

            System.arraycopy(data, 0, temp, 0, length);
            Arrays.fill(count, 0);

            // ��ÿһλ�ĸ���
            for (int j = 0; j < length; j++) {
                int tempKey = (temp[j] / divide) % radix;
                count[tempKey]++;
            }
            System.out.println(":"+Arrays.toString(count));
             // ��data������index ֵ
            for (int j = 1; j < radix; j++) {
                count[j] = count[j] + count[j - 1];
            }
            System.out.println(":"+Arrays.toString(count));
            // ���˾������ü�������ʵ�ּ���������ص��������������
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
        int m = 1;// ���Ƽ�ֵ������������һλ
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

package com.zhongs.sort;

import java.util.Arrays;

//ð�ݣ�Bubble�����򡪡������Ƚϣ�С�ľͽ���

//��С��������n����
//Ч�� O(n��2�η�),����������С�б�


//ð�������㷨���������£�
//�Ƚ����ڵ�Ԫ�ء������һ���ȵڶ����󣬾ͽ�������������
//��ÿһ������Ԫ����ͬ���Ĺ������ӿ�ʼ��һ�Ե���β�����һ�ԡ�����һ�㣬����Ԫ��Ӧ�û�����������
//������е�Ԫ���ظ����ϵĲ��裬�������һ����
//����ÿ�ζ�Խ��Խ�ٵ�Ԫ���ظ�����Ĳ��裬ֱ��û���κ�һ��������Ҫ�Ƚϡ�
public class BubbleSort extends AbstractSort {

    public static void main(String[] args) {
        int[] array = a.clone(); // �����ݵ��������a��¡���󣬲��ı�a�����ֵ
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {// �ܹ�����length -1 ������
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
    // // ����һ��
    // temp = a[i];
    // a[i] = a[j];
    // a[j] = temp;
    //
    // /*
    // * //������: a[i] = a[i] + a[j]; a[j] = a[i] - a[j]; a[i] =
    // * a[i] - a[j];
    // */
    // }
    // System.out.println(Arrays.toString(a));
    // }
    // }
    // System.out.println(Arrays.toString(a));
    // }
}

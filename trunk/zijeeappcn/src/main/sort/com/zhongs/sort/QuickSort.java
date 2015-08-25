package com.zhongs.sort;

import java.util.Arrays;


// ���������Ƕ�ð�������һ�ָĽ�
// ƽ��ʱ�临�Ӷ���O(n)��

//���������п���һ�����飬
//�ѵ����λ�ÿ������ᣬ�����һ���ȣ��������С���������������κδ���
//�������Ժ��ٺ�С���Ƕ˱ȣ�����С�������������󽻻���
//����ѭ��������һ��������ɣ���߾��Ǳ�����С�ģ��ұ߾��Ǳ������ģ�
//Ȼ�����÷��η����ֱ�������������������������


//���ݽṹ    ����
//���ʱ�临�Ӷ�  n^2
//����ʱ�临�Ӷ� nlogn
//ƽ��ʱ�临�Ӷ� nlogn
//���ռ临�Ӷ� ����ʵ�ֵķ�ʽ��ͬ����ͬ

public class QuickSort extends AbstractSort {

    private int partition(int data[], int low, int hight) {
        // ����ĵ�һ����Ϊ����
        int key = data[low];
        System.out.println("key:"+key);
        while (low < hight) {

            // ������С�ļ�¼�Ƶ��Ͷ�
            while (low < hight && data[hight] >= key)
                hight--;
            data[low] = data[hight];
            System.out.println("A:"+Arrays.toString(data)+"low:"+low+"��height:"+hight);

            // �������ļ�¼�Ƶ��߶�
            while (low < hight && data[low] <= key)
                low++;
            data[hight] = data[low];
            System.out.println("B:"+Arrays.toString(data)+"low:"+low+"��height:"+hight);
        }
        
        // �����¼ λ������
        data[low] = key;
        System.out.println("DATA��"+Arrays.toString(data)+"low:"+low+"��height:"+hight);
        // ���������λ��
        return low;
    }

    public void sort(int data[], int low, int hight) {
        if (low < hight) {
            // ��list�������һ��Ϊ��
            int result = partition(data, low, hight);
            // �Ե��ֱ���еݹ�����
            sort(data, low, result - 1);
            // �Ը��ֱ���еݹ�����
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

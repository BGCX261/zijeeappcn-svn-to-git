package com.zhongs.sort;

import java.util.Arrays;

// Shell ����
//ϣ��������һ�ֲ��������㷨��������D.L.Shell����˶�������Shell�����ֳ�����С��������
//������Ҫһ���ݼ��Ĳ�������������ʹ�õ���9��5��3��1�����Ĳ���������1����Ϊh����
//����ԭ�������ȶ����9-1��Ԫ�ص�������������Ȼ����ʹ��ͬ���ķ��������5-1��Ԫ�ص�����
// ���h����������
public class ShellSort extends AbstractSort{

    /*
     * D. Shell ������㷨��
     */
    int shellsortSh(int p[], int n) {
        int op = 0;
        int h, i, j, temp;
        for (h = n / 2; h > 0; h = h / 2) {
            for (i = h; i < n; i++) {
                temp = p[i];
                for (j = i - h; j >= 0 && p[j] > temp; j -= h) {
                    p[j + h] = p[j];
                    op++;
                }
                p[j + h] = temp;
                op++;
            }
        }
        return op;
    }

    // Lazarus-Frank �㷨��1960 �귢��
    /*
     * ԭΪ�ڱ�Ҫʱ�� 1 ʹ����������Ϊ������������Ϊ�� 1��
     */
    int shellsortLF(int p[], int n) {
        int op = 0;
        int h, i, j, temp;
        for (h = n / 2; h > 0; h = h / 2) {
            if (h % 2 == 0)
                h--;
            for (i = h; i < n; i++) {
                temp = p[i];
                for (j = i - h; j >= 0 && p[j] > temp; j -= h) {
                    p[j + h] = p[j];
                    op++;
                }
                p[j + h] = temp;
                op++;
            }
        }
        return op;
    }

    // Hibbard �㷨��1963 �귢��
    /*
     * 1965 �� Papernov-Stasevich ��������ѧ֤����
     */
    int shellsortHb(int p[], int n) {
        int op = 0;
        int h, i, j, temp;
        for (h = 1; h <= n / 4; h = h * 2 + 1)
            ;
        for (; h > 0; h = (h - 1) / 2) {
            /* h = 1,3,7,15,31 ... 2^i-1 */
            for (i = h; i < n; i++) {
                temp = p[i];
                for (j = i - h; j >= 0 && p[j] > temp; j -= h) {
                    p[j + h] = p[j];
                    op++;
                }
                p[j + h] = temp;
                op++;
            }
        }
        return op;
    }

    // Papernov-Stasevich �㷨��1965�귢��

    int shellsortPS(int p[], int n) {
        int op = 0;
        int h, i, j, temp;
        for (h = 2; h <= n / 4; h = h * 2 - 1)
            ;
        for (; h > 1;) {
            h = (h == 3) ? 1 : (h + 1) / 2;
            /* h = 1,3,5,9,17,33 ... 2^i+1 */
            for (i = h; i < n; i++) {
                temp = p[i];
                for (j = i - h; j >= 0 && p[j] > temp; j -= h) {
                    p[j + h] = p[j];
                    op++;
                }
                p[j + h] = temp;
                op++;
            }
        }
        return op;
    }

    // Knuth �㷨���������� n<1000 ʱʹ�á�

    int shellsortKn(int p[], int n) {
        int op = 0;
        int h, i, j, temp;
        for (h = 1; h <= n / 9; h = h * 3 + 1)
            ;
        for (; h > 0; h = h / 3) {
            /* h = 1,4,13,40,121,364... 3*h+1 */
            for (i = h; i < n; i++) {
                temp = p[i];
                for (j = i - h; j >= 0 && p[j] > temp; j -= h) {
                    p[j + h] = p[j];
                    op++;
                }
                p[j + h] = temp;
                op++;
            }
        }
        return op;
    }

    // Pratt �㷨��1971 �귢��

    /*
     * ԭΪ h=2^p*3^q ������Ϊ 7^p*8^q��
     */
    int shellsortPr(int p[], int n) {
        int op = 0;
        int h, i, j, t, temp;
        int incs[] = { 262144, 229376, 200704, 175616, 153664, 134456, 117649, 32768, 28672, 25088, 21952, 19208,
                16807, 4096, 3584, 3136, 2744, 2401, 512, 448, 392, 343, 64, 56, 49, 8, 7, 1 };
        for (t = 0; t < 28; t++) {
            h = incs[t];
            for (i = h; i < n; i++) {
                temp = p[i];
                for (j = i - h; j >= 0 && p[j] > temp; j -= h) {
                    p[j + h] = p[j];
                    op++;
                }
                p[j + h] = temp;
                op++;
            }
        }
        return op;
    }

    // Sedgewick �㷨��1982 �귢��

    int shellsortSe82(int p[], int n) {
        int op = 0;
        int h, i, j, t, temp;
        for (t = 1; t * t <= n / 4; t += t)
            ;
        for (h = n / 4; t > 0; t /= 2, h = t * t - (3 * t) / 2 + 1) {
            /*
             * h = 1,8,23,77,281,1073,4193,16577, 65921,262913,1050113...
             * 4^i+3*2^(i-1)+1
             */
            for (i = h; i < n; i++) {
                temp = p[i];
                for (j = i - h; j >= 0 && p[j] > temp; j -= h) {
                    p[j + h] = p[j];
                    op++;
                }
                p[j + h] = temp;
                op++;
            }
        }
        return op;
    }

    // Gonnet �㷨�������� 1991 �ꡣ

    int shellsortGo(int p[], int n) {
        int op = 0;
        int h, i, j, temp;
        for (h = n; h > 1;) {
            h = (h < 5) ? 1 : (h * 5 - 1) / 11;
            for (i = h; i < n; i++) {
                temp = p[i];
                for (j = i - h; j >= 0 && p[j] > temp; j -= h) {
                    p[j + h] = p[j];
                    op++;
                }
                p[j + h] = temp;
                op++;
            }
        }
        return op;
    }

    // Incerpj-Sedgewick �㷨��1985 �귢��

    int shellsortIS(int p[], int n) {
        int op = 0;
        int h, i, j, t, temp;
        int incs[] = { /* a1=3,a2=7,a3=16,a4=41,a5=101 */
        1391376,/* a1*a2*a3*a4*a5 */
        463792,/* a2*a3*a4*a5 */
        198768,/* a1*a3*a4*a5 */
        86961,/* a1*a2*a4*a5 */
        33936,/* a1*a2*a3*a5 */
        13776,/* a1*a2*a3*a4 */
        4592,/* a2*a3*a4 */
        1968,/* a1*a3*a4 */
        861,/* a1*a2*a4 */
        336,/* a1*a2*a3 */
        112,/* a2*a3 */
        48,/* a1*a3 */
        21,/* a1*a2 */
        7,/* a2 */
        3,/* a1 */
        1 };
        for (t = 0; t < incs.length; t++) {
            h = incs[t];
            for (i = h; i < n; i++) {
                temp = p[i];
                for (j = i - h; j >= 0 && p[j] > temp; j -= h) {
                    p[j + h] = p[j];
                    op++;
                }
                p[j + h] = temp;
                op++;
            }
        }
        return op;
    }

    
    public void sort(int[] data) {
        int length = data.length;

        int op = 0;
        int h, i, j, t, temp;
        int incs[] = { 9, 5, 3, 1 };
        for (t = 0; t < incs.length; t++) {
            h = incs[t];
            for (i = h; i < length; i++) {
                temp = data[i];
                for (j = i - h; j >= 0 && data[j] > temp; j -= h) {
                    data[j + h] = data[j];
                    op++;
                }
                data[j + h] = temp;
                op++;
            }
            System.out.println(h+":"+Arrays.toString(data));
        }
        System.out.println(op);
        return;
    }
    
    public static void main(String[] args) {
        ShellSort sh = new ShellSort();
        sh.sort(a);
        System.out.println(Arrays.toString(a));
    }
}

package com.zhongs.sort;

import java.util.Arrays;

// Shell 排序
//希尔排序是一种插入排序算法，它出自D.L.Shell，因此而得名。Shell排序又称作缩小增量排序。
//首先需要一个递减的步长，这里我们使用的是9、5、3、1（最后的步长必须是1，称为h）。
//工作原理是首先对相隔9-1个元素的所有内容排序，然后再使用同样的方法对相隔5-1个元素的排序
// 间隔h的内容排序
public class ShellSort extends AbstractSort{

    /*
     * D. Shell 最初的算法。
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

    // Lazarus-Frank 算法，1960 年发表。
    /*
     * 原为在必要时加 1 使所有增量都为奇数，现修正为减 1。
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

    // Hibbard 算法，1963 年发表。
    /*
     * 1965 年 Papernov-Stasevich 给出了数学证明。
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

    // Papernov-Stasevich 算法，1965年发表

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

    // Knuth 算法，他建议在 n<1000 时使用。

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

    // Pratt 算法，1971 年发表

    /*
     * 原为 h=2^p*3^q 现修正为 7^p*8^q。
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

    // Sedgewick 算法，1982 年发表

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

    // Gonnet 算法，发表于 1991 年。

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

    // Incerpj-Sedgewick 算法，1985 年发表。

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

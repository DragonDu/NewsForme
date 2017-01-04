package com.du.newsforme;

import android.util.Log;

import org.junit.Test;

import java.util.List;
import java.util.Scanner;

import static android.R.attr.max;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//        int[] a = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
//        int[] maopao = maopao(a);
//        for (int i = 0; i < maopao.length; i++) {
//            System.out.println(maopao[i]);
//        }

////        xuanze(a);
//        System.out.println(digui(5));
        baohan(100);
    }


    public int[] maopao(int[] shuzu) {
        int count = 0;
        for (int i = 1; i < shuzu.length; i++) {
            for (int j = 0; j < shuzu.length - 1; j++) {
                if (shuzu[j] > shuzu[j + 1]) {
                    int temp = shuzu[j];
                    shuzu[j] = shuzu[j + 1];
                    shuzu[j + 1] = temp;

                }
                count++;
            }

        }
        System.out.println("count" + count);
        return shuzu;
    }

    public int digui(int a) {

        if (a > 0) {
            int sum = a + digui(a - 1);

            return sum;
        }
        return 0;
    }

    public void xuanze(int[] p) {

        for (int i = 0; i < p.length; i++) {
            int j = i + 1;
            for (; j < p.length; j++) {
                if (p[i] > p[j]) {
                    int temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;
                }

            }
        }
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]);
        }
    }


    public void baohan(int a) {
        for (int b = 1; b <= a; b++) {

            int yushu = b % 6;
            int shang100 = b / 100;
            int shang10 = b % 100 / 10;
            int shang1 = b % 100 % 10;

            //判断余数
            if (yushu == 0) {
                System.out.println("余数：" + b);
            }
            //判断百位数
            if (shang100 == 6) {
                System.out.println("百位数：" + b);

            } else if (shang10 == 6) {
                System.out.println("十位数：" + b);
            } else if (shang1 == 6) {
                System.out.println("个位数：" + b);
            }

        }

    }
}
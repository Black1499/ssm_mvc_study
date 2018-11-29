package com.lzx.main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class ArraySort implements Runnable {
    private String num;

    public ArraySort(int num) {
        this.num = num + "";
    }

    public static void main(String[] args) {
        int[] num = {1, 100, 67, 4, 29, 91, 20, 22, 88, 51, 2, 3};
//        for (int i = 0; i < num.length ; i++) {
//            new Thread(new ArraySort(num[i])).start();
//        }

//        ArrayList<Integer> list = new ArrayList<>();
//        for (int i = 0; i < num.length; i++) {
//            list.add(num[i]);
//        }
//        Collections.sort(list);
//        for (int nums :
//                list) {
//            System.out.println(nums);
//        }

        sort(num);
        System.out.println("aa");
    }

    public static void sort(int[] num) {
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if (num[i] > num[j]) {
                    int temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
            }
            System.out.println(num[i]);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(Integer.parseInt(num));
            System.out.println(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

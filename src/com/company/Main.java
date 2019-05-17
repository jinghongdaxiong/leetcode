package com.company;

import com.company.algorithms.TwoSum;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        System.out.print("ddd");

        /**
         * 几个快捷键
         * psvm main方法
         * for for循环
         * sout System.out.print();
         */
        TwoSum twoSum = new TwoSum();
        int[] arr = new int[]{2,4,7,5};
        int tmp = 9;
        int[] result = twoSum.twoSum(arr,tmp);
        System.out.println(Arrays.toString(result));
    }
}

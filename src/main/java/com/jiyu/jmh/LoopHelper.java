package com.jiyu.jmh;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * <b>功能名：LoopHelper</b><br>
 *  @description: 循环工具类 实现基于java8的多种方式循环List
 *  通过 5 种不同的方式遍历List所有的值来查找最大值
 *
 * @author 2022-04-02 xuxiongzi
 */

public class LoopHelper {

    /**
     * 使用迭代器循环
     *
     * @param list
     * @return
     */
    public static int iteratorMaxInteger(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            max = Integer.max(max, (Integer) it.next());
        }
        return max;
    }

    /**
     * 使用foreach循环
     *
     * @param list
     * @return
     */
    public static int forEachLoopMaxInteger(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (Integer n : list) {
            max = Integer.max(max, n);
        }
        return max;
    }

    /**
     * 使用for循环
     *
     * @return
     */
    public static int forMaxInteger(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            max = Integer.max(max, list.get(i));
        }
        return max;
    }

    /**
     * 使用java8并行流
     *
     * @return
     */
    public static int parallelStreamMaxInteger(List<Integer> list) {
        Optional max = list.parallelStream().reduce(Integer::max);
        return (int) max.get();
    }

    /**
     * 使用 lambda 表达式及流
     *
     * @return
     */
    public static int lambdaMaxInteger(List<Integer> list) {
        return list.stream().reduce(Integer.MIN_VALUE, (a, b) -> Integer.max(a, b));
    }
}
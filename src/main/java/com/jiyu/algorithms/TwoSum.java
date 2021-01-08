package com.jiyu.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers,return indices of two
 * numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly
 * one solution,and you may not use the same element
 * twice.
 * <p>
 * 给定一个整数数组nums和一个目标值target,请你在该数组中
 * 找出和为目标值得两个整数,并返回他们的数组下标.
 * <p>
 * 你可以假设每种输入只会应对一个答案,但是,你不能重复利用数组中
 * 同样的元素.
 * <p>
 * 示例
 * <p>
 * 给定 nums = [2,7,11,15], target = 9
 * <p>
 * 因为 num[0] + num[1] = 2 + 7 = 9
 * 所以返回[0,1]
 * @author xuxiongzi
 */
public class TwoSum {

    /**
     * 方法一:暴力法
     * 遍历每个元素x,并查找是否存在一个值与
     * target - x 相等的目标元素
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 方法二: 两遍哈希表
     * <p>
     * 为了对运行时间复杂度进行优化,我们需要一种更有效的方法来检查数组
     * 中是否存在目标元素.如果存在,我们就找出它的索引.保持数组中的每个
     * 元素与其索引相互对应的最好方法是什么? 哈希表.通过以空间换取速度
     * 的方式,我们可以将查找时间从O(n)降到O(1).哈希表正是为此目的而
     * 构建的,它支持以近似恒定的时间进行快速查找.我用"近似"来描述,是
     * 因为一旦出现冲突,查找用时可能会退化到O(n).但只要你仔细地挑选
     * 哈希函数,在哈希表中进行查找的用时应当被推销为O(1).
     * <p>
     * 一个简单的实现使用了两次迭代.在第一次迭代中,我们将每个元素的值
     * 和它的索引添加到表中,然后,在第二次迭代中,我们将检查每个元素所
     * 应对的目标元素(target - nums[i])是否存在于表中.注意,改目标
     * 元素不能是nums[i]本身!
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度:O(n),我们把包含有n个元素的列表遍历两次.由于哈希表
     * 将查找时间缩短到O(1),所以时间复杂度为O(n).
     * <p>
     * 空间复杂度:O(n),所需的额外空间取决于哈希表中存储的元素数量,
     * 改表中存储了n个元素.
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 方法三: 一遍哈希表
     * 事实证明,我们可以一次完成,在进行迭代并将元素插入到表中的同时,
     * 我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素.
     * 如果它存在,那我们已经找到了对应解,并立即将其返回.
     * <p>
     * 时间复杂度 O(
     */
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


}

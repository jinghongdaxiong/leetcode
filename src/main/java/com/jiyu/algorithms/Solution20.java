package com.jiyu.algorithms;

import java.util.*;

/**
 * <b>功能名：Solution20</b><br>
 * <b>说明：</b><br>
 *
 * @author 2022-04-02 xuxiongzi
 * @see <a href="https://leetcode.com/problems/valid-parentheses">leetcode</a>
 */
public class Solution20 {

    public static final int TWO = 2;

    /**
     * 方法一栈
     * <p>
     * 判断括号的有效性可以使用『栈』这一数据结构来解决。
     * <p>
     * 我们遍历给定的字符串s。当我们遇到一个左括号时，我们会期望在后
     * 续的遍历中，有一个相同类型的右括号将其闭合。由于后遇到的左括号
     * 要先闭合，因此我们可以将这个左括号放入栈顶。
     * <p>
     * 当我们遇到一个右括号时，我们需要将一个相同类型的左括号闭合。此
     * 时，我们可以取出栈顶的左括号判断它们是否是x相同类型的括号。如
     * 果不是相同的类型，或者栈中并没有左括号，那么字符串s无效,返回
     * False。为了快速判断括号的类型，我们可以使用哈希表存储每一种
     * 括号。哈希表的键为右括号，值为相同类型的左括号。
     * <p>
     * 在遍历结束后，如果栈中没有左括号，说明我们将字符串s中的所有左括号
     * 闭合，返回True，否则返回False。
     * <p>
     * 注意到有效字符串的长度一定为偶数，因此如果字符串的长度为奇数，
     * 我们可以直接返回False，省去后续的遍历判断过程。
     */

    public static boolean isValid(String s) {

        // 获取字符串长度
        int n = s.length();

        // 由于标准括号一定是成对出现的，字符串长度为奇数则验证失败
        if (n % TWO == 1) {
            return false;
        }

        // 定义括号Map用于遍历验证
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        /*
         * 用双端队列定义栈：
         *
         * 为啥用Deque而不是Stack
         * 因为Stack只能上进上出，有点像刺胞动物
         * （腔肠动物），就是那种从哪里吃进去就哪
         * 里拉出来的那种生活在海洋里的比较低级的
         * 生物。Deque上进上出，上进下出，甚至下
         * 进上出，非常上流，只有你想不到，没有我
         * Deque做不到的。
         *
         * https://blog.csdn.net/qq_44013629/article/details/106461200
         *
         */
        Deque<Character> stack = new LinkedList<>();

        // 遍历字符串
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // 括号map中key包含字符串
            if (pairs.containsKey(ch)) {
                // 栈是空的则说明压栈操作压入了空值
                if (stack.isEmpty() || !Objects.equals(stack.peek(), pairs.get(ch))) {
                    return false;
                }
                // 出栈操作
                stack.pop();
            } else {
                // 括号map中key不包含字符串(即字符串为左括号时) 压栈操作
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(isValid(scanner.next()));
        }

    }
}
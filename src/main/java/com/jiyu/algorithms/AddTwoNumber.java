package com.jiyu.algorithms;

/**
 * Add Two Numbers
 * You are given two non-empty linked lists representing
 * two non-negative integers. The digits are stored in
 * reverse order and each of their nodes contain a single
 * digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading
 * zero,except the number 0 itself.
 * <p>
 * 两数相加
 * <p>
 * 给出两个非空的链表用来表示两个非空的整数.
 * 其中,它们各自的位数是按照逆序的方式存储的,
 * 并且它们的每个节点只能存储一位数字.
 * <p>
 * 如果我们将这两个数相加起来,则会返回一个新
 * 的链表来表示它们的和.
 * 您可以假设除了数字0之外,这两个数都不会以
 * 0开头.
 * <p>
 * 示例:
 * 输入:(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 0 -> 8
 * 原因: 342 + 465 = 807
 * @author xuxiongzi
 */
public class AddTwoNumber {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.nextNode = new ListNode(sum % 10);
            curr = curr.nextNode;
            if (p != null) {
                p = p.nextNode;
            }
            if (q != null) {
                q = q.nextNode;
            }
        }
        if (carry > 0) {
            curr.nextNode = new ListNode(carry);
        }
        return dummyHead.nextNode;
    }

    class ListNode {
        int val;
        ListNode nextNode;

        ListNode(int val) {
            this.val = val;
            this.nextNode = null;
        }
    }


}

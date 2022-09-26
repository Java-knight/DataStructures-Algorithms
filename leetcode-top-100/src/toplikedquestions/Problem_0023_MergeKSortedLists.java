package toplikedquestions;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// 合并K个升序链表
// https://leetcode.cn/problems/merge-k-sorted-lists/
public class Problem_0023_MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        // (1) 把每个链表的头节点加入 minHeap 中
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        // (2) 判断 minHeap 是否为空
        if (minHeap.isEmpty()) {
            return null;
        }

        // (3) 弹出加入resultList, 将弹出的节点.next加入minHeap
        ListNode head = minHeap.poll();
        ListNode pre = head;
        ListNode cur = null;
        if (pre.next != null) {
            minHeap.offer(pre.next);
        }
        while (!minHeap.isEmpty()) {
            cur = minHeap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                minHeap.offer(cur.next);
            }
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val;this.next = next;}
    }
}

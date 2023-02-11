package toplikedquestions;


// 排序链表
// https://leetcode.cn/problems/sort-list/
public class Problem_0148_SortList {

  // 思路: 使用归并排序的思想, 时间复杂度: O(NlogN), 空间复杂度O(1)【因为是链表merge不需要额外的空间】
  public ListNode sortList(ListNode head) {
    int size = 0;
    ListNode cur = head;
    while (cur != null) {
      cur = cur.next;
      size++;
    }
    ListNode res = head;  // 结果: 第一组链表的头节点
    ListNode teamFirst = head;  // 下一组的链表的头节点
    ListNode pre = null;  // 上一个组的最后一个元素
    for (int len = 1; len < size; len <<= 1) {  // 步长从1开始, 1 -> 2 -> 4 -> 8 ->...
      while (teamFirst != null) {
        ListNode[] splitRes = splitList(teamFirst, len);
        ListNode[] mergeRes = merge(splitRes[0], splitRes[1], splitRes[2], splitRes[3]);
        if (res == teamFirst) {  // 现在分割合并的就是第一组链表
          res = mergeRes[0];
          pre = mergeRes[1];
        } else {  // 现在分割合并的不是第一组链表
          pre.next = mergeRes[0];
          pre = mergeRes[1];
        }
        teamFirst = splitRes[4];
      }
      teamFirst = res;
      pre = null;
    }
    return res;
  }

  /**
   * 将链表分割成两组
   * case1:
   * 分割前: 1->4->3->8->9->5->null , len=4
   * 分割后: 1->4->3->8->null 9->5->null
   * case2:
   * 分割前: 1->4->3->8->9->5->null , len=2
   * 分割后: 1->4->null 3->8->null  5->null
   * @param head 需要分割链表的头节点
   * @param len  需要分割每组的长度
   * @return leftStart, leftEnd, rightStart, rightEnd, next; 左右两组链表的头尾节点 和 分完后的next节点
   */
  private ListNode[] splitList(ListNode head, int len) {
    ListNode leftStart = head;
    ListNode leftEnd = head;
    ListNode rightStart = null;
    ListNode rightEnd = null;
    ListNode next = null;
    int pass = 0;
    while (head != null) {  // head 在链表上就是一个指针, 一步步向后跑的找这3个点的位置
      pass++;
      if (pass <= len) {
        leftEnd = head;
      }
      if (pass == len + 1) {
        rightStart = head;
      }
      if (pass > len) {
        rightEnd = head;
      }
      if (pass == (len << 1)) {  // 这个必须要, 因为链表长度可能大于2*len
        break;
      }
      head = head.next;
    }
    // 设置leftEnd和rightEnd的next节点(split操作)
    leftEnd.next = null;
    if (rightEnd != null) {
      next = rightEnd.next;
      rightEnd.next = null;
    }
    return new ListNode[] {leftStart, leftEnd, rightStart, rightEnd, next};
  }

  /**
   * 合并两组链表
   * @param leftStart  左组的头节点
   * @param leftEnd    左组的尾节点
   * @param rightStart 右组的头节点
   * @param rightEnd   右组的尾节点
   * @return start, end; 返回合并链表的头尾节点
   */
  private ListNode[] merge(ListNode leftStart, ListNode leftEnd, ListNode rightStart, ListNode rightEnd) {
    if (rightStart == null) {  // 右链表为null, 不需要检查
      return new ListNode[] {leftStart, leftEnd};
    }
    ListNode head = null;
    ListNode pre = null;  // 最后合并后的链表上跑(合并链表的最后一个节点)
    ListNode cur = null;  // 临时指针, 指的node是从左右链表start的最小值, 准备去合并链表的
    ListNode tail = null;
    while (leftStart != leftEnd.next && rightStart != rightEnd.next) {  // 左右两边的链表都没有跑完
      if (leftStart.val <= rightStart.val) {
        cur = leftStart;
        leftStart = leftStart.next;
      } else {
        cur = rightStart;
        rightStart = rightStart.next;
      }
      if (pre == null) {
        head = cur;
        pre = cur;
      } else {
        pre.next = cur;
        pre = cur;
      }
    }

    // 出现左右链表可能存在没有跑完的
    if (leftStart != leftEnd.next) {  // 左链表没有跑完
      while (leftStart != leftEnd.next) {
        pre.next = leftStart;
        pre = leftStart;
        tail = leftStart;
        leftStart = leftStart.next;
      }
    } else {  // 左链表没有跑完
      while (rightStart != rightEnd.next) {
        pre.next = rightStart;
        pre = rightStart;
        tail = rightStart;
        rightStart = rightStart.next;
      }
    }
    return new ListNode[] {head, tail};
  }

  public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}

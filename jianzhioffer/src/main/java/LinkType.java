/**
 * @author: zhegong
 **/
public class LinkType {

    /**
     给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

     示例 1:

     输入: 1->2->3->3->4->4->5
     输出: 1->2->5
     示例 2:

     输入: 1->1->1->2->3
     输出: 2->3

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode ret = new ListNode(-1);

        ListNode retCurrent = ret;
        ListNode currentNode = head;

        while (currentNode != null) {
            int nowValue = currentNode.val;

            if (currentNode.next != null) {
                if (currentNode.next.val == nowValue) {
                    while (currentNode.next.next != null && currentNode.next.next.val == nowValue) {
                        currentNode = currentNode.next;
                    }
                    currentNode = currentNode.next.next;
                } else {
                    ListNode addNode = new ListNode(currentNode.val);
                    retCurrent.next = addNode;
                    retCurrent = addNode;

                    currentNode = currentNode.next;
                }
            } else {
                ListNode addNode = new ListNode(currentNode.val);
                retCurrent.next = addNode;

                currentNode = currentNode.next;
            }
        }
        return ret.next;
    }




    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}

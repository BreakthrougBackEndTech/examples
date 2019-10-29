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

        ListNode firstNode = findFirstNonDuplicateNode(head);

        if (firstNode == null){
            return null;
        }

        ListNode ret = new ListNode(firstNode.val);

        ListNode currentNode = ret;

        while (firstNode != null ){


            firstNode = findFirstNonDuplicateNode(firstNode.next);

            if(firstNode == null){
                break;
            }else{
                ListNode node = new ListNode(firstNode.val);

                currentNode.next = node;
                currentNode = node;
                firstNode = firstNode.next;
            }
        }

        return ret;
    }

    private ListNode findFirstNonDuplicateNode(ListNode currentNode) {
        if(currentNode == null){
            return  null;
        }

        int nowValue = currentNode.val;

        while (currentNode != null && currentNode.next != null && currentNode.next.val == nowValue ){

            currentNode = currentNode.next;
            if(currentNode != null){
                nowValue = currentNode.val;
            }
        }

        return currentNode;
    }


    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}

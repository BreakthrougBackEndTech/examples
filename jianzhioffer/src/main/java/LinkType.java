//import java.util.Stack;

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

    /**
     题目描述
     输入一个链表，输出该链表中倒数第k个结点。
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }

        ListNode ahead = head, behind = head;

        //先将一个节点往后移动k-1个距离
        while (k > 1) {
            if (ahead.next == null) {
                return null;
            }
            ahead = ahead.next;
            k--;
        }

        while (ahead.next != null) {
            ahead = ahead.next;
            behind = behind.next;
        }

        return behind;
    }


    /**
     给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode aheadOneStep = pHead, aheadTwoStep = pHead;

        boolean existLoop = false;
//        int steps = 0;
        //找环
        while (aheadTwoStep != null && aheadOneStep != null) {
            aheadOneStep = aheadOneStep.next;
            if (aheadTwoStep.next != null) {
                aheadTwoStep = aheadTwoStep.next.next;
            } else {
                aheadTwoStep = null;
            }

//            steps++;

            if (aheadOneStep!= null && aheadOneStep == aheadTwoStep) {
                existLoop = true;
                break;
            }
        }

        if (!existLoop) {
            return null;
        }

        //计算环的长度
        int loopSize = 0;
        do{
            aheadOneStep = aheadOneStep.next;
            loopSize++;
        }while (aheadOneStep != aheadTwoStep);

        //找入口
        aheadOneStep = pHead;
        aheadTwoStep = pHead;

        for(int i=0; i< loopSize; i++){
            aheadOneStep = aheadOneStep.next;
        }

        while (aheadOneStep != aheadTwoStep){
            aheadOneStep = aheadOneStep.next;
            aheadTwoStep = aheadTwoStep.next;
        }

        return aheadOneStep;
    }


    /**
     * 输入一个链表，反转链表后，输出新链表的表头
     */
    public ListNode ReverseList(ListNode head) {

        if(head == null){
            return null;
        }

        ListNode preNode, currentNode, nextNode;

        preNode = null;
        currentNode = head;

        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }

        return preNode;
    }

    /**
     输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }

        ListNode mergeList = null;

        if(list1.val <= list2.val){
            mergeList = list1;
            mergeList.next = Merge(list1.next, list2);
        }else{
            mergeList = list2;
            mergeList.next = Merge(list1, list2.next);
        }

        return mergeList;
    }




    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}

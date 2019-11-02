import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkTypeTest {

    @Test
    public void deleteDuplicates() {
        LinkType linkType = new LinkType();

        LinkType.ListNode list1 = linkType.new ListNode(1);
        LinkType.ListNode list2 = linkType.new ListNode(2);
        LinkType.ListNode list3 = linkType.new ListNode(3);
        LinkType.ListNode list4 = linkType.new ListNode(3);
        LinkType.ListNode list5 = linkType.new ListNode(4);
        LinkType.ListNode list6 = linkType.new ListNode(4);
        LinkType.ListNode list7 = linkType.new ListNode(5);

        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;
        list5.next = list6;
        list6.next = list7;

        LinkType.ListNode result = linkType.deleteDuplicates(list1);

        Assert.assertEquals(1, result.val);
        Assert.assertEquals(2, result.next.val);
        Assert.assertEquals(5, result.next.next.val);
        Assert.assertEquals(null, result.next.next.next);
    }


    @Test
    public void FindKthToTail(){
        LinkType linkType = new LinkType();

        LinkType.ListNode list1 = linkType.new ListNode(6);
        LinkType.ListNode list2 = linkType.new ListNode(5);
        LinkType.ListNode list3 = linkType.new ListNode(4);
        LinkType.ListNode list4 = linkType.new ListNode(3);
        LinkType.ListNode list5 = linkType.new ListNode(2);
        LinkType.ListNode list6 = linkType.new ListNode(1);

        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;
        list5.next = list6;

        Assert.assertEquals(list4, linkType.FindKthToTail(list1, 3));

        Assert.assertEquals(list6, linkType.FindKthToTail(list1, 1));
    }

    @Test
    public void EntryNodeOfLoop(){
        LinkType linkType = new LinkType();

        LinkType.ListNode list1 = linkType.new ListNode(1);
        LinkType.ListNode list2 = linkType.new ListNode(2);
        LinkType.ListNode list3 = linkType.new ListNode(3);
        LinkType.ListNode list4 = linkType.new ListNode(4);
        LinkType.ListNode list5 = linkType.new ListNode(5);
        LinkType.ListNode list6 = linkType.new ListNode(6);

        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;
        list5.next = list6;
        //环
        list6.next = list3;

        Assert.assertEquals(list3, linkType.EntryNodeOfLoop(list1));
    }


    @Test
    public void ReverseList(){
        LinkType linkType = new LinkType();

        LinkType.ListNode list1 = linkType.new ListNode(1);
        LinkType.ListNode list2 = linkType.new ListNode(2);
        LinkType.ListNode list3 = linkType.new ListNode(3);
        LinkType.ListNode list4 = linkType.new ListNode(4);
        LinkType.ListNode list5 = linkType.new ListNode(5);
        LinkType.ListNode list6 = linkType.new ListNode(6);

        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;
        list5.next = list6;


        LinkType.ListNode ret = linkType.ReverseList(list1);

        Assert.assertEquals(list6, ret);
        Assert.assertEquals(list5, ret.next);
        Assert.assertEquals(list4, ret.next.next);
        Assert.assertEquals(list3, ret.next.next.next);
        Assert.assertEquals(list2, ret.next.next.next.next);
        Assert.assertEquals(list1, ret.next.next.next.next.next);
    }

    @Test
    public void Merge(){
        LinkType linkType = new LinkType();

        LinkType.ListNode list1 = linkType.new ListNode(1);
        LinkType.ListNode list2 = linkType.new ListNode(3);
        LinkType.ListNode list3 = linkType.new ListNode(5);

        LinkType.ListNode list4 = linkType.new ListNode(2);
        LinkType.ListNode list5 = linkType.new ListNode(4);
        LinkType.ListNode list6 = linkType.new ListNode(6);

        list1.next = list2;
        list2.next = list3;

        list4.next = list5;
        list5.next = list6;

        LinkType.ListNode mergeList = linkType.Merge(list1, list4);


        Assert.assertEquals(list1, mergeList);
        Assert.assertEquals(list4, mergeList.next);
        Assert.assertEquals(list2, mergeList.next.next);
        Assert.assertEquals(list5, mergeList.next.next.next);
        Assert.assertEquals(list3, mergeList.next.next.next.next);
        Assert.assertEquals(list6, mergeList.next.next.next.next.next);
        Assert.assertEquals(null, mergeList.next.next.next.next.next.next);
    }
}
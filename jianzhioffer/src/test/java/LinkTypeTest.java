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

        Assert.assertEquals(result.val, 1);
        Assert.assertEquals(result.next.val, 2);
        Assert.assertEquals(result.next.next.val, 5);
        Assert.assertEquals(result.next.next.next, null);
    }
}
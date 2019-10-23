import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TreeTypeTest {

    @Test
    public void buildTree() {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        /**    3
              / \
             9  20
              /   \
             15    7 */

        TreeType treeType = new TreeType();
        TreeType.TreeNode treeNode =  treeType.buildTree(preorder,inorder);


        Assert.assertEquals(treeNode.val, 3);
        Assert.assertEquals(treeNode.left.val, 9);
        Assert.assertEquals(treeNode.left.left, null);
        Assert.assertEquals(treeNode.left.right, null);

        Assert.assertEquals(treeNode.right.val, 20);
        Assert.assertEquals(treeNode.right.left.val, 15);
        Assert.assertEquals(treeNode.right.right.val, 7);
    }


    @Test
    public void GetNext(){

        /**   1
           /     \
         2         3
       /   \      / \
      4     5    6   7 */
        TreeType treeType = new TreeType();
        TreeType.TreeLinkNode rootNode = treeType.new TreeLinkNode(1);

        TreeType.TreeLinkNode node2 = treeType.new TreeLinkNode(2);
        TreeType.TreeLinkNode node3 = treeType.new TreeLinkNode(3);
        TreeType.TreeLinkNode node4 = treeType.new TreeLinkNode(4);
        TreeType.TreeLinkNode node5 = treeType.new TreeLinkNode(5);
        TreeType.TreeLinkNode node6 = treeType.new TreeLinkNode(6);
        TreeType.TreeLinkNode node7 = treeType.new TreeLinkNode(7);

        rootNode.left = node2;
        rootNode.right = node3;

        node2.left = node4;
        node2.right = node5;
        node2.next = rootNode;
        node4.next=node2;
        node5.next = node2;

        node3.left = node6;
        node3.right = node7;
        node3.next = rootNode;
        node6.next=node3;
        node7.next = node3;

        print(rootNode);




        Assert.assertEquals(node2, treeType.GetNext(node4));

        Assert.assertEquals(rootNode, treeType.GetNext(node5));

        Assert.assertEquals(node6, treeType.GetNext(rootNode));
    }

    private void print(TreeType.TreeLinkNode treeLinkNode){
        if(treeLinkNode != null) {
            print(treeLinkNode.left);
            System.out.println(treeLinkNode.val);
            print(treeLinkNode.right);
        }
    }


    @Test
    public void buildHeap(){
        TreeType treeType = new TreeType();

        int[] array = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        int[] expectedArray = new int[]{1, 5, 2, 6, 7, 3, 8, 9, 10};
        treeType.buildHeap(array, array.length);

        Assert.assertTrue(Arrays.equals(expectedArray, array));
    }
}

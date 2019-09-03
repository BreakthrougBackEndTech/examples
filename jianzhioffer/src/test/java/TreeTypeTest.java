import org.junit.Assert;
import org.junit.Test;

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
}

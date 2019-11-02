import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-09-03 10:12
 **/
public class TreeType {


    /**根据一棵树的前序遍历与中序遍历构造二叉树。

    注意:
    你可以假设树中没有重复的元素。

    例如，给出

    前序遍历 preorder = [3,9,20,15,7]
    中序遍历 inorder = [9,3,15,20,7]
    返回如下的二叉树：

             3
            / \
           9  20
            /   \
          15    7

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        // 将中序遍历的结果放置到map结构中，方便通过根植找下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, 0, inorder.length - 1, map);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (preEnd < preStart || inEnd < inStart)
            return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int i = map.get(root.val);
        root.left = buildTree(preorder, preStart + 1, preStart + (i - inStart), inStart, i - 1, map);
        root.right = buildTree(preorder, preStart + (i - inStart) + 1, preEnd, i + 1, inEnd, map);
        return root;
    }


    /**
     * 牛客网
     题目描述
     给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if(pNode == null){
            return null;
        }

        TreeLinkNode nextNode;
        if(pNode.right != null){
            nextNode = pNode.right;
            while(nextNode.left!=null){
                nextNode = nextNode.left;
            }
            return nextNode;
        }

        if(pNode.next != null){
            if(pNode.next.left == pNode){
                return pNode.next;
            }else{
                nextNode = pNode.next;
                boolean isLeft = false;
                while (nextNode.next != null){
                    if(nextNode.next.left == nextNode){
                        isLeft = true;
                    }
                    nextNode = nextNode.next;
                }

                if(isLeft){
                    return nextNode;
                }
            }
        }

        return null;
    }

    /**
     * 二叉堆 上浮
     */
    public void upAdjust(int[] arr, int upIndex, int usedLength) {
        if (upIndex > usedLength - 1) {
            return;
        }

        int childIndex = upIndex;
        int parentIndex = (childIndex - 1) / 2;

        int temp = arr[childIndex];
        while (parentIndex >= 0 && arr[parentIndex] > temp) {

            arr[childIndex] = arr[parentIndex];

            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }

        arr[childIndex] = temp;
    }

    public void downAdjust(int[] arr, int downIndex, int usedLength){
        int parentIndex = downIndex;
        int childIndex = 2*parentIndex + 1;

        int temp  = arr[parentIndex];
        while (childIndex < usedLength){

            if ((childIndex + 1) < usedLength && arr[childIndex] > arr[childIndex + 1]) {
                childIndex++;
            }

            if(temp < arr[childIndex]){
                break;
            }

            arr[parentIndex] = arr[childIndex];

            parentIndex = childIndex;
            childIndex = 2* parentIndex +1;
        }

        arr[parentIndex] = temp;
    }

    public void buildHeap(int[] arr, int usedLength){
        // 从最后一个非叶子节点开始，依次做“下沉”调整
        for(int i= usedLength/2; i>=0; i--){
            downAdjust(arr, i, usedLength);
        }
    }

   /**
    * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
    */
   public boolean HasSubtree(TreeNode root1,TreeNode root2) {

       if(root2 == null){
           return false;
       }


       return false;
   }



    // * Definition for a binary tree node.
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        public TreeLinkNode(int val) {
            this.val = val;
        }
    }
}


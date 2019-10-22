import java.util.Stack;

/**
 * @author: zhegong
 **/
public class StackType {

    /**
     给定 pushed 和 popped 两个序列，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。

      

     示例 1：

     输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     输出：true
     解释：我们可以按以下顺序执行：
     push(1), push(2), push(3), push(4), pop() -> 4,
     push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     示例 2：

     输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
     输出：false
     解释：1 不能在 2 之前弹出。
      

     提示：

     0 <= pushed.length == popped.length <= 1000
     0 <= pushed[i], popped[i] < 1000
     pushed 是 popped 的排列。

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/validate-stack-sequences
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack();
        int popIndex = 0;

        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);

            while (!stack.empty() && stack.peek() == popped[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }

        return stack.empty();
    }


    /**
     题目描述
     用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node) {
        stack1.push(node);
    }
    public int pop() {

        if(stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }

}

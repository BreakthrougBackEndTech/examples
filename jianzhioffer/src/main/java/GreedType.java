import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: zhegong
 **/
public class GreedType {

    /**
     你的初始能量为 P，初始分数为 0，只有一包令牌。

     令牌的值为 token[i]，每个令牌最多只能使用一次，可能的两种使用方法如下：

     如果你至少有 token[i] 点能量，可以将令牌置为正面朝上，失去 token[i] 点能量，并得到 1 分。
     如果我们至少有 1 分，可以将令牌置为反面朝上，获得 token[i] 点能量，并失去 1 分。
     在使用任意数量的令牌后，返回我们可以得到的最大分数。

      
     示例 1：

     输入：tokens = [100], P = 50
     输出：0
     示例 2：

     输入：tokens = [100,200], P = 150
     输出：1
     示例 3：

     输入：tokens = [100,200,300,400], P = 200
     输出：2
      

     提示：

     tokens.length <= 1000
     0 <= tokens[i] < 10000
     0 <= P < 10000

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/bag-of-tokens
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int bagOfTokensScore(int[] tokens, int P) {
        int res = 0, point = 0, left = 0, right = tokens.length - 1;
        Arrays.sort(tokens);
        while (left <= right) {

            if (P >= tokens[left]) {
                P -= tokens[left++];
                res = Math.max(res, ++point);
            } else if (point > 0) {
                P += tokens[right--];
                point--;
            } else {
                break;
            }
        }
        return res;
    }

    /**
     给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。
     请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
     例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     输入描述:
     输入一个数n，意义见题面。（2 <= n <= 60）

     示例1
     输入
     8
     输出
     18
     */
    public int cutRope(int length) {
        if (2 > length) {
            return 0;
        }
        if (2 == length) {
            return 1;
        }
        if (3 == length) {
            return 2;
        }

        int timesOf3 = length / 3;

        //当最后只剩4时，  1*3 < 2*2
        if (length % 3 == 1) {
            timesOf3--;
        }

        int timesOf2 = (length - 3 * timesOf3) / 2;

        return (int) Math.pow(3, timesOf3) * (int) Math.pow(2, timesOf2);
    }
}

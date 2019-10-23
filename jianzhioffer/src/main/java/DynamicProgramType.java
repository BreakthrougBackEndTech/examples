/**
 * @description:
 * @author: zhegong
 * @create: 2019-10-22 10:24
 **/
public class DynamicProgramType {
    /**
     大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     n<=39
     */
    public int Fibonacci(int n) {
        int zero = 0;
        int one = 1;

        if(n ==0)
            return zero;
        if(n == 1)
            return one;

        int res=0;
        for(int i=2; i<=n; i++){
            res = one+zero;
            zero = one;
            one = res;
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
        int nums[] = new int[length + 1];

        if (2 > length) {
            return 0;
        }
        if (2 == length) {
            return 1;
        }
        if (3 == length) {
            return 2;
        }

        nums[0] = 0;
        nums[1] = 1;
        nums[2] = 2;
        nums[3] = 3;

        int max;
        for (int i = 4; i <= length; i++) {
            max = 0;
            for (int j = 1; j <= i/2; j++) {

                int temp = nums[i - j] * nums[j];
                if (temp > max)
                    max = temp;
            }
            nums[i] = max;
        }

        return nums[length];
    }

    public int NumberOf1(int n) {
        int count = 0;

        int positive = n;

        if(n < 0){
            positive = -n % Integer.MAX_VALUE;
        }


        if(positive%2 == 1)
            count++;

        while(positive/2 >0){
            positive = positive/2;
            if(positive%2 ==1)
                count++;
        }

        if(n < 0){
            count = 32 - count;
        }

        return count;
    }
}


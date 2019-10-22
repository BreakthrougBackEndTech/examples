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
}


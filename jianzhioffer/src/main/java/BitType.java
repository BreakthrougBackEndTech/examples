/**
 * @description:
 * @author: zhegong
 * @create: 2019-10-23 09:22
 **/
public class BitType {
    public int NumberOf1(int n) {
        int count = 0;
        int flag = 1;

        while (flag != 0) {
            if ((n & flag) != 0)
                count++;
            flag = flag << 1;
        }

        //最好方法
   /*     while (n != 0) {
            count++;
            n = (n - 1) & n;
        }*/

        return count;
    }
}


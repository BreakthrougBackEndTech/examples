import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-10-23 10:09
 **/
public class OtherType {
    public double Power(double base, int exponent) {
        if (Double.compare(base, 0.0) == 0) {
            return 0.0;
        }

        boolean isNegative = false;
        if(exponent < 0){
            isNegative = true;
            exponent = -exponent;
        }

        if(exponent == 0){
            return 1.0;
        }
        if(exponent == 1){
            return base;
        }

        double res = 1.0;
        for(int i=0; i< exponent; i++){
            res*=base;
        }

     /*   for(int i=0; i< exponent>>1; i++){
            res*=base;
        }

        res = res*res;

        if((exponent&1) != 0){
            res = res*base;
        }*/

        if(isNegative){
            res = 1.0/res;
        }

        return res;
    }

    /**
     给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

     '.' 匹配任意单个字符
     '*' 匹配零个或多个前面的那一个元素
     所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

     说明:

     s 可能为空，且只包含从 a-z 的小写字母。
     p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     示例 1:

     输入:
     s = "aa"
     p = "a"
     输出: false
     解释: "a" 无法匹配 "aa" 整个字符串。
     示例 2:

     输入:
     s = "aa"
     p = "a*"
     输出: true
     解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     示例 3:

     输入:
     s = "ab"
     p = ".*"
     输出: true
     解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     示例 4:

     输入:
     s = "aab"
     p = "c*a*b"
     输出: true
     解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     示例 5:

     输入:
     s = "mississippi"
     p = "mis*is*p*."
     输出: false

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/regular-expression-matching
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isMatch(String s, String p) {
        return match(s.toCharArray(), p.toCharArray());
//        return s.matches(p);
    }

    private boolean match(char[] str, char[] pattern) {
        return matchCore(str, pattern, 0, 0);
    }

    private boolean matchCore(char[] str, char[] pattern, int strIndex, int patternIndex) {
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        if ((strIndex < str.length && patternIndex == pattern.length)) {
            return false;
        }


        if (patternIndex < pattern.length - 1 && pattern[patternIndex + 1] == '*') {
            if (strIndex < str.length && (pattern[patternIndex] == '.' || pattern[patternIndex] == str[strIndex])) {
                return matchCore(str, pattern, strIndex + 1, patternIndex)
                        || matchCore(str, pattern, strIndex + 1, patternIndex + 2)
                        || matchCore(str, pattern, strIndex, patternIndex + 2);
            } else {
                return matchCore(str, pattern, strIndex, patternIndex + 2);
            }

        } else if (strIndex < str.length && (pattern[patternIndex] == '.' || pattern[patternIndex] == str[strIndex])) {
            return matchCore(str, pattern, strIndex + 1, patternIndex + 1);
        }

        return false;
    }

    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,
     * 则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     输入描述:
     输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> strList = new ArrayList<>();

        HashSet<String> set = new HashSet();

        if(str == null || str.length() == 0 ){
            return strList;
        }

        Pernutation(str.toCharArray(), set, 0);

        strList.addAll(set);

        Collections.sort(strList);

        return strList;
    }

    private void Pernutation(char[] chars, HashSet set, int index){
        if(index == chars.length -1){
            set.add(new String(chars));
        }else{
            for(int i = index; i< chars.length;i++){
                swap(chars, index, i);
                Pernutation(chars, set, index+1);
                swap(chars, index, i);
            }
        }
    }


    private void swap(char[] chars, int i, int j){
        if(i != j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }


    /**
     * 字符串的所有组合
     *  给一个字符串，比如ABC， 把所有的组合，即：A, B, C, AB, AC, BC, ABC, 都找出来
     * @param str
     */
    public void combine(String str){

        if(str == null || str.length() == 0) return ;

        char[] chs = str.toCharArray();

        Stack<Character> stack = new Stack<Character>();
        for(int number = 1; number <= chs.length; number++){
            combine(chs, 0, number, stack);
        }
    }
    //从字符数组中第begin个字符开始挑选number个字符加入stack中
    private void combine(char []chs, int begin, int number, Stack<Character> stack){
        if(number == 0){
            System.out.println(stack.toString());
            return ;
        }
        if(begin == chs.length){
            return;
        }
        stack.push(chs[begin]);
        combine(chs, begin + 1, number - 1, stack);
        stack.pop();
        combine(chs, begin + 1, number, stack);
    }


    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    public int lengthOfLongestSubstring(String s) {
        String str = "";
        int maxSize = 0;

        for (int i = 0; i < s.length(); i++) {
            if (-1 != str.indexOf(s.charAt(i))) {
                str = str.substring(str.indexOf(s.charAt(i)) + 1) + s.charAt(i);
            } else {
                str = str + s.charAt(i);

            }

            maxSize = Math.max(maxSize, str.length());
        }

        return maxSize;
    }

    /**
     * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
     * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,
     * 但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,
     * 可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int number = 0;
        for (int i = 1; i <= n; i++) {
            number += getNumberOf1(i);
        }
        return number;
    }


    private int getNumberOf1(int i) {
        int number = 0;

        while (i > 0) {
            if (i % 10 == 1) {
                number++;
            }

            i = i / 10;
        }

        return number;
    }

    /**
     * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
     * HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
     * 然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,
     * 然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数
     * ....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
     * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)

     如果没有小朋友，请返回-1
     */
    public int LastRemaining_Solution(int n, int m) {

        if(n<1 || m <1){
            return -1;
        }
        int last =0;
        for(int i=2; i<=n; i++){
            last = (last + m)%i;
        }

        return last;
    }

}


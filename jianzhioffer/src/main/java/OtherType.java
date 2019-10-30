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
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
//        return s.matches(p);
        return match(str, pattern);

    }

    private boolean match(char[] str, char[] pattern) {
        return matchCore(str, pattern, 0, 0);
    }

    private boolean matchCore(char[] str, char[] pattern, int strIndex, int patternIndex) {
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        if (strIndex < str.length-1 && patternIndex == pattern.length) {
            return false;
        }


        if (patternIndex < pattern.length - 1) {
            if (pattern[patternIndex + 1] == '*') {
                if ((pattern[patternIndex] == '.' && strIndex < str.length-1) || pattern[patternIndex] == str[strIndex]) {
                    return matchCore(str, pattern, strIndex + 1, patternIndex)
                            || matchCore(str, pattern, strIndex + 1, patternIndex + 2)
                            || matchCore(str, pattern, strIndex, patternIndex + 2);
                }else {
                    return matchCore(str, pattern, strIndex, patternIndex+2);
                }
            } else {
                if (pattern[patternIndex] == '.' || pattern[patternIndex] == str[strIndex]) {
                    return matchCore(str, pattern, strIndex+1, patternIndex+1);
                }else {
                    return false;
                }
            }

        } else {
            if (pattern[patternIndex] == '*') {
                return false;
            } else if ((pattern[patternIndex] == '.' && strIndex < str.length-1) || pattern[patternIndex] == str[strIndex]) {
                return matchCore(str, pattern, strIndex + 1, patternIndex+1);
            } else {
                return false;
            }
        }

    }
}


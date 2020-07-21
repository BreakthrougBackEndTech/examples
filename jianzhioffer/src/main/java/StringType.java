import java.util.BitSet;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-07-20 15:24
 **/
public class StringType {
    /**
     * 给定一个字符串，要求把字符串前面的若干个字符移动到字符串的尾部，
     * 如把字符串“abcdef”前面的2个字符'a'和'b'移动到字符串的尾部，
     * 使得原字符串变成字符串“cdefab”。请写一个函数完成此功能，
     * 要求对长度为n的字符串操作的时间复杂度为 O(n)，空间复杂度为 O(1)
     */

    public String rotateLeftString(String str, int m) {
        char[] chars = str.toCharArray();
        rotateLeftChars(chars, m - 1);
        return String.valueOf(chars);
    }

    /**
     * 给定两个分别由字母组成的字符串A和字符串B，字符串B的长度比字符串A短。请问，
     * 如何最快地判断字符串B中所有字母是否都在字符串A里？
     * 为了简单起见，我们规定输入的字符串只包含大写英文字母，
     * 请实现函数bool StringContains(string &A, string &B)
     * 比如，如果是下面两个字符串：
     * String 1：ABCD
     * String 2：BAD
     * 答案是true，即String2里的字母在String1里也都有，或者说String2是String1的真子集。
     */
    public boolean stringContain(String a, String b) {
        BitSet bitSet = new BitSet(26);
//        int hash = 0;  //或是使用自己移位的方式
        for (char ch : a.toCharArray()) {
            bitSet.set(ch - 'A', true);
//            hash |= 1 << (ch - 'A');
        }

        for (char ch : b.toCharArray()) {
            if (!bitSet.get(ch - 'A')) {
                return false;
            }

          /*  if ((hash & (1 << (ch - 'A'))) == 0){
                return false;
            }*/
        }

        return true;
    }

    private void rotateLeftChars(char[] chars, int index) {
        rotateCharsInScope(chars, 0, index);
        rotateCharsInScope(chars, index + 1, chars.length - 1);
        rotateCharsInScope(chars, 0, chars.length - 1);

    }

    private void rotateCharsInScope(char[] chars, int from, int to) {
        while (from < to) {
            char t = chars[from];
            chars[from++] = chars[to];
            chars[to--] = t;
        }
    }
}


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


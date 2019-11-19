import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class OtherTypeTest {

    @Test
    public void power() {
        OtherType otherType = new OtherType();
//        System.out.println(Double.compare(1.0, 1.00));
        Assert.assertEquals(8.0, otherType.Power(2.0, 3), 0.001);
        Assert.assertEquals(0.0, otherType.Power(0.0, 3), 0.001);
        Assert.assertEquals(0.125, otherType.Power(2.0, -3), 0.001);
    }


    @Test
    public void isMatch(){
        OtherType otherType = new OtherType();
        Assert.assertFalse(otherType.isMatch("aa", "a"));
        Assert.assertTrue(otherType.isMatch("aa", "a*"));
        Assert.assertTrue(otherType.isMatch("ab", ".*"));
        Assert.assertTrue(otherType.isMatch("a", "ab*"));
        Assert.assertTrue(otherType.isMatch("aab", "c*a*b"));
        Assert.assertFalse(otherType.isMatch("mississippi",  "mis*is*p*."));
        Assert.assertFalse(otherType.isMatch("ab",  ".*c"));
        Assert.assertTrue(otherType.isMatch("aasdfasdfasdfasdfas",  "aasdf.*asdf.*asdf.*asdf.*s"));
    }


    @Test
    public void Permutation() {
        OtherType otherType = new OtherType();
        ArrayList list = otherType.Permutation("abc");

        Assert.assertEquals(6, list.size());

        Assert.assertTrue(list.contains("abc"));
        Assert.assertTrue(list.contains("acb"));
        Assert.assertTrue(list.contains("bac"));
        Assert.assertTrue(list.contains("bca"));
        Assert.assertTrue(list.contains("cab"));
        Assert.assertTrue(list.contains("cba"));
    }

    @Test
    public void combine(){
        OtherType otherType = new OtherType();
        otherType.combine("abc");
    }


    @Test
    public void lengthOfLongestSubstring(){
        OtherType otherType = new OtherType();

        Assert.assertEquals(3, otherType.lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(3, otherType.lengthOfLongestSubstring("aabaab!bb"));
    }
}

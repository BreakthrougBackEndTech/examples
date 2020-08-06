import org.junit.Assert;
import org.junit.Test;

public class StringTypeTest {
    StringType stringType = new StringType();

    @Test
    public void rotateLeftString() {
        Assert.assertEquals("cdefab", stringType.rotateLeftString("abcdef", 2));
        Assert.assertEquals("abcdef", stringType.rotateLeftString("abcdef", 6));
        Assert.assertEquals("abcdef", stringType.rotateLeftString("abcdef", 0));
    }

    @Test
    public void stringContain() {
        Assert.assertTrue(stringType.stringContain("ABCD", "BAD"));
        Assert.assertFalse(stringType.stringContain("ABCD", "BCE"));
        Assert.assertTrue(stringType.stringContain("ABCD", "BAA"));
    }

    @Test
    public void stringToIndex() {
        Assert.assertEquals(0, stringType.getIndexFromString("a"));
        Assert.assertEquals(1, stringType.getIndexFromString("aa"));
        Assert.assertEquals(2, stringType.getIndexFromString("aaa"));
        Assert.assertEquals(25 * 25 * 25 + 25 * 25 + 25 + 1, stringType.getIndexFromString("b"));
    }

    @Test
    public void indexToString() {
        Assert.assertEquals("a", stringType.getStringFromIndex(0));
        Assert.assertEquals("aa", stringType.getStringFromIndex(1));
        Assert.assertEquals("aaa", stringType.getStringFromIndex(2));
        Assert.assertEquals("b", stringType.getStringFromIndex(25 * 25 * 25 + 25 * 25 + 25 + 1));
    }
}

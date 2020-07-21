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
}

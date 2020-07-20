import org.junit.Assert;
import org.junit.Test;

public class StringTypeTest {


    @Test
    public void rotateLeftString() {
        StringType stringType = new StringType();
        Assert.assertEquals("cdefab", stringType.rotateLeftString("abcdef", 2));
        Assert.assertEquals("abcdef", stringType.rotateLeftString("abcdef", 6));
        Assert.assertEquals("abcdef", stringType.rotateLeftString("abcdef", 0));
    }
}

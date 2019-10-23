import org.junit.Assert;
import org.junit.Test;

public class BitTypeTest {

    @Test
    public void NumberOf1(){
        BitType bitType = new BitType();

        Assert.assertEquals(2, bitType.NumberOf1(9));
        Assert.assertEquals(3, bitType.NumberOf1(7));
        Assert.assertEquals(1, bitType.NumberOf1(-2147483648));


        System.out.println(Integer.toBinaryString(-2147483648));
    }
}

import org.junit.Assert;
import org.junit.Test;

public class OtherTypeTest {

    @Test
    public void power() {
        OtherType otherType = new OtherType();
//        System.out.println(Double.compare(1.0, 1.00));
        Assert.assertEquals(8.0, otherType.Power(2.0, 3), 0.001);
        Assert.assertEquals(0.0, otherType.Power(0.0, 3), 0.001);
        Assert.assertEquals(0.125, otherType.Power(2.0, -3), 0.001);
    }
}

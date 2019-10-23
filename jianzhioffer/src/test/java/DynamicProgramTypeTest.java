import org.junit.Assert;
import org.junit.Test;

public class DynamicProgramTypeTest {


    @Test
    public void cutRope() {
        DynamicProgramType dynamicProgramType = new DynamicProgramType();

        Assert.assertEquals(18, dynamicProgramType.cutRope(8));
    }

    @Test
    public void NumberOf1(){
        DynamicProgramType dynamicProgramType = new DynamicProgramType();

        Assert.assertEquals(2, dynamicProgramType.NumberOf1(9));
        Assert.assertEquals(3, dynamicProgramType.NumberOf1(7));
        Assert.assertEquals(1, dynamicProgramType.NumberOf1(-2147483648));


        System.out.println(Integer.toBinaryString(-2147483648));
    }
}

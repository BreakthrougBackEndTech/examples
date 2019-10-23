import org.junit.Assert;
import org.junit.Test;

public class DynamicProgramTypeTest {

    @Test
    public void cutRope() {
        DynamicProgramType dynamicProgramType = new DynamicProgramType();

        Assert.assertEquals(18, dynamicProgramType.cutRope(8));
    }

}

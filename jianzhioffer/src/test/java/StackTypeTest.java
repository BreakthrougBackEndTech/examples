import org.junit.Assert;
import org.junit.Test;

public class StackTypeTest {

    @Test
    public void validateStackSequences(){
        StackType stackType = new StackType();

        int[] pushed = new int[]{1,2,3,4,5};
        int[] popped = new int[]{4,5,3,2,1};

        Assert.assertTrue(stackType.validateStackSequences(pushed, popped));
    }

}
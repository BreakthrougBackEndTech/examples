package feature18.inter;


import org.junit.Assert;
import org.junit.Test;

public class FormulaTest {

    @Test
    public void testInterface() {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        Assert.assertEquals(Double.valueOf(100.0), (Double) formula.calculate(100));     // 100.0
        Assert.assertEquals(Double.valueOf(4.0), (Double) formula.sqrt(16));           // 4.0
    }

}

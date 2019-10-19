import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreedTypeTest {

    @Test
    public void bagOfTokensScore() {
        GreedType greedType = new GreedType();

        int[] tokens = new int[]{100};
        int P = 50;
        Assert.assertEquals(0, greedType.bagOfTokensScore(tokens, P));

        tokens = new int[]{100, 200};
        P = 150;
        Assert.assertEquals(1, greedType.bagOfTokensScore(tokens, P));

        tokens = new int[]{100, 200, 300, 400};
        P = 200;
        Assert.assertEquals(2, greedType.bagOfTokensScore(tokens, P));
    }
}
import org.junit.Assert;
import org.junit.Test;

public class DisjointSetTypeTest {
    @Test
    public void removeStones() {
        DisjointSetType gridType = new DisjointSetType();

        int[][] stones = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        Assert.assertEquals(5, gridType.removeStones(stones));

        stones = new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};
        Assert.assertEquals(3, gridType.removeStones(stones));

        stones = new int[][]{{0, 0}};
        Assert.assertEquals(0, gridType.removeStones(stones));
    }

}
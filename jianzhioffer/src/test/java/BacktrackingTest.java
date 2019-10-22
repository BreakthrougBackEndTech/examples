import org.junit.Assert;
import org.junit.Test;

public class BacktrackingTest {

    @Test
    public void hasPath(){
        Backtracking backtracking = new Backtracking();
        char[] matrix = "a b c e s f c s a d e e".replaceAll(" ", "").toCharArray();

        char[] str = "bcced".toCharArray();
        Assert.assertTrue(backtracking.hasPath(matrix, 3, 4, str));

        str =  "abcb".toCharArray();
        Assert.assertFalse(backtracking.hasPath(matrix, 3, 4, str));

    }
}

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ArrayTypeTest {

    @Test
    public void findDuplicates() {
        ArrayType arrayType = new ArrayType();

        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};

        List<Integer> resultList;

  /*      resultList = arrayType.findDuplicates(nums);

        Assert.assertEquals(2, resultList.size());
        Assert.assertTrue(resultList.contains(2));
        Assert.assertTrue(resultList.contains(3));

        resultList = arrayType.findDuplicates(new int[]{2, 2});
        Assert.assertEquals(1, resultList.size());
        Assert.assertTrue(resultList.contains(2));
*/
        resultList = arrayType.findDuplicates(new int[]{10,2,5,10,9,1,1,4,3,7});
        Assert.assertEquals(2, resultList.size());
        Assert.assertTrue(resultList.contains(10));
        Assert.assertTrue(resultList.contains(1));


    }


}

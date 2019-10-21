import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        resultList = arrayType.findDuplicates(new int[]{10, 2, 5, 10, 9, 1, 1, 4, 3, 7});
        List<Integer> list = Stream.of(1, 10).collect(Collectors.toList());

        Assert.assertEquals(list, resultList);


    }


    @Test
    public void findKthLargest() {
        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};

        ArrayType arrayType = new ArrayType();
        Assert.assertEquals(4, arrayType.findKthLargest(nums, 4));
    }


    /**
     * 在刷leeCode的时候 发现lambda 比for ： 遍历set慢很多， 自己写个代码证明一下
     * 果然单纯的遍历 for： 比  发现lambda 快
     */
    @Test
    public void comapreLambdaToNormal() {

        Random random = new Random();

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            set.add(random.nextInt(1000000));
        }

        long t0 = System.nanoTime();

        List<Integer> list = new ArrayList<>();
        for (Integer i : set) {
            list.add(i + 1);
        }
        long t1 = System.nanoTime();

        List<Integer> list1 = set.stream()
                .map(x -> x + 1)
                .collect(Collectors.toList());

        long t2 = System.nanoTime();


        List<Integer> list2 = new ArrayList<>();
        set.forEach(a->list2.add(a+1));

        long t3 = System.nanoTime();

        List<Integer> list3 = new ArrayList<>();
        set.iterator().forEachRemaining(a->list3.add(a+1));

        long t4 = System.nanoTime();

        System.out.println("for :  cost " + (t1-t0) );
        System.out.println("lambda cost " + (t2-t1) );
        System.out.println("feach  cost " + (t3-t2) );
        System.out.println("feach1 cost " + (t4-t3) );
    }


    @Test
    public void minIncrementForUnique(){
        int[] A = new int[]{1,2,2};

        ArrayType arrayType = new ArrayType();
        Assert.assertEquals(1, arrayType.minIncrementForUnique(A));

    }


    @Test
    public void findMin(){
        int[] nums = new int[]{4,5,6,7,0,1,2};

        ArrayType arrayType = new ArrayType();
        Assert.assertEquals(0, arrayType.findMin(nums));

        nums = new int[]{3,1,2};
        Assert.assertEquals(1, arrayType.findMin(nums));
    }


    @Test
    public void hasPath(){
        ArrayType arrayType = new ArrayType();
        char[] matrix = "a b c e s f c s a d e e".replaceAll(" ", "").toCharArray();

        char[] str = "bcced".toCharArray();
        Assert.assertTrue(arrayType.hasPath(matrix, 3, 4, str));

        str =  "abcb".toCharArray();
        Assert.assertFalse(arrayType.hasPath(matrix, 3, 4, str));

    }

}

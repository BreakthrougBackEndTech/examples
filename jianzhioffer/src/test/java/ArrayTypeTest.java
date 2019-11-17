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
    public void GetLeastNumbers_Solution(){
        int[] input = new int[]{4,5,1,6,2,7,3,8};

        ArrayType arrayType = new ArrayType();

        List expectedList = new ArrayList();

        Assert.assertEquals(expectedList, arrayType.GetLeastNumbers_Solution(input, 10));

        input = new int[]{4,5,1,6,2,7,3,8};
        expectedList.add(1);
        expectedList.add(2);
        expectedList.add(3);
        expectedList.add(4);
        Assert.assertEquals(expectedList, arrayType.GetLeastNumbers_Solution(input, 4));
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
    public void reOrderArray(){
        int[] nums = new int[]{4, 5, 6, 7, 1, 2};
        int[] expectedNums = new int[]{5, 7, 1, 4, 6, 2};

        ArrayType arrayType = new ArrayType();
        arrayType.reOrderArray(nums);


        Assert.assertTrue(Arrays.equals(expectedNums, nums));
    }

    @Test
    public void printMatrix(){
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};

        ArrayType arrayType = new ArrayType();
        ArrayList<Integer> list;
      list = arrayType.printMatrix(matrix);

        Assert.assertEquals(16, list.size());

        Assert.assertEquals(1, list.get(0).intValue());
        Assert.assertEquals(2, list.get(1).intValue());
        Assert.assertEquals(3, list.get(2).intValue());
        Assert.assertEquals(4, list.get(3).intValue());
        Assert.assertEquals(8, list.get(4).intValue());
        Assert.assertEquals(12, list.get(5).intValue());
        Assert.assertEquals(16, list.get(6).intValue());
        Assert.assertEquals(15, list.get(7).intValue());
        Assert.assertEquals(14, list.get(8).intValue());
        Assert.assertEquals(13, list.get(9).intValue());
        Assert.assertEquals(9, list.get(10).intValue());
        Assert.assertEquals(5, list.get(11).intValue());
        Assert.assertEquals(6, list.get(12).intValue());
        Assert.assertEquals(7, list.get(13).intValue());
        Assert.assertEquals(11, list.get(14).intValue());
        Assert.assertEquals(10, list.get(15).intValue());
        //1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.

        matrix = new int[][]{
                {1},
                {2},
                {3},
                {4},
                {5}};

        list = arrayType.printMatrix(matrix);
        Assert.assertEquals(5, list.size());

        Assert.assertEquals(1, list.get(0).intValue());
        Assert.assertEquals(2, list.get(1).intValue());
        Assert.assertEquals(3, list.get(2).intValue());
        Assert.assertEquals(4, list.get(3).intValue());
        Assert.assertEquals(5, list.get(4).intValue());

        matrix = new int[][]{
                {1,2},
                {3, 4},
                {5, 6},
                {7, 8},
                {9, 10}};

        list = arrayType.printMatrix(matrix);
        Assert.assertEquals(10, list.size());

        Assert.assertEquals(1, list.get(0).intValue());
        Assert.assertEquals(2, list.get(1).intValue());
        Assert.assertEquals(4, list.get(2).intValue());
        Assert.assertEquals(6, list.get(3).intValue());
        Assert.assertEquals(8, list.get(4).intValue());
        Assert.assertEquals(10, list.get(5).intValue());
        Assert.assertEquals(9, list.get(6).intValue());
        Assert.assertEquals(7, list.get(7).intValue());
        Assert.assertEquals(5, list.get(8).intValue());
        Assert.assertEquals(3, list.get(9).intValue());
    }

    @Test
    public void MoreThanHalfNum_Solution() {
        ArrayType arrayType = new ArrayType();
        int[] array = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        Assert.assertEquals(2, arrayType.MoreThanHalfNum_Solution(array));

        array = new int[]{1,2,3,2,4,2,5,2,3};
        Assert.assertEquals(0, arrayType.MoreThanHalfNum_Solution(array));
    }

    @Test
    public void InsertAndGetMedian(){
//        [5,2,3,4,1,6,7,0,8]

        ArrayType arrayType = new ArrayType();

        arrayType.Insert(5);
        Assert.assertEquals(5, arrayType.GetMedian(), 0);

        arrayType.Insert(2);
        Assert.assertEquals(3.5, arrayType.GetMedian(), 0);

        arrayType.Insert(3);
        Assert.assertEquals(3, arrayType.GetMedian(), 0);
        arrayType.Insert(4);
        Assert.assertEquals(3.5, arrayType.GetMedian(), 0);


    }

}

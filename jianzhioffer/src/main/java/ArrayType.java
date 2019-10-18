import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-08-16 14:47
 **/
public class ArrayType {
    /** 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。

     找到所有出现两次的元素。

     你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？

     示例：

     输入:
     [4,3,2,7,8,2,3,1]

     输出:
     [2,3]

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/


    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            nums[i] -= 1;
        }

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    set.add(nums[i]);
                    break;
                }
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }

        List<Integer> list = new ArrayList<>();

//        return set.stream().map(x->x+1).collect(Collectors.toList());

        for (Integer i : set) {
            list.add(i + 1);
        }

        return list;

         //最高效的算法
        /*List<Integer> ret = new ArrayList<>();
        int n = nums.length;
        for (int i=0; i<n; i++) {
            int a = (nums[i]-1) % n;
            nums[a] += n;
        }
        for (int i=0; i<n; i++) {
            if (nums[i] > 2*n) ret.add(i+1);
        }
        return ret;*/

        //使用数组比使用包装类更快
        /*int[] burket = new int[nums.length+1];
        for(int i=0; i<nums.length; i++) {
            burket[nums[i]]++;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<nums.length+1; i++) {
            if(burket[i] == 2) list.add(i);
        }
        return list;*/
    }

    /**
    给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

    示例 1:

    输入: [1,2,0]
    输出: 3
    示例 2:

    输入: [3,4,-1,1]
    输出: 2
    示例 3:

    输入: [7,8,9,11,12]
    输出: 1

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/first-missing-positive
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    */
     public int firstMissingPositive(int[] nums) {
        int[] array = new int[nums.length+1];

        for(int i=0; i<nums.length; i++){

            if(nums[i] <0 || nums[i] > nums.length)
                continue;

            array[nums[i]] = 1;
        }


        for(int i=1; i< array.length; i++){

            if(array[i] == 0){
                return i;
            }
        }

        return array.length;
    }

   /**
    在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

    示例 1:

    输入: [3,2,1,5,6,4] 和 k = 2
    输出: 5
    示例 2:

    输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
    输出: 4
    说明:

    你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/


   public int findKthLargest(int[] nums, int k) {
      /*  PriorityQueue<Integer> pq = new PriorityQueue();
        for(int i : nums){
            pq.add(i);

            if(pq.size() > k){
                pq.poll();
            }
        }
        return pq.peek();*/

       return findK(nums, k - 1, 0, nums.length - 1);
   }

    public int findK(int[] nums, int k, int start, int end) {
        int low = start;
        int high = end;
        int pivot = nums[(low + high) / 2];
        while (low <= high) {
            while (nums[high] < pivot) {
                high--;
            }

            while (nums[low] > pivot) {
                low++;
            }

            if (low <= high) {
                int temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;

                low++;
                high--;
            }
        }

        if (start < high && k <= high)
            return findK(nums, k, start, high);
        // 这时如果左指针还小于等于k，说明kth在右半边
        if (low < end && k >= low)
            return findK(nums, k, low, end);
        return nums[k];
    }


    /**
     * 快速排序
     * @param nums
     * @param left
     * @param right
     */
    public void quickSort(int[] nums, int left, int right) {
        int i, j, t, temp;
        if (left > right)
            return;
        temp = nums[left]; //temp中存的就是基准数
        i = left;
        j = right;
        while (i != j) { //顺序很重要，要先从右边开始找
            while (nums[j] >= temp && i < j)
                j--;
            while (nums[i] <= temp && i < j)//再找右边的
                i++;
            if (i < j)//交换两个数在数组中的位置
            {
                t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        //最终将基准数归位
        nums[left] = nums[i];
        nums[i] = temp;
        quickSort(nums, left, i - 1);//继续处理左边的，这里是一个递归的过程
        quickSort(nums, i + 1, right);//继续处理右边的 ，这里是一个递归的过程
    }

  /**
    给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。

    返回使 A 中的每个值都是唯一的最少操作次数。

    示例 1:

    输入：[1,2,2]
    输出：1
    解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
    示例 2:

    输入：[3,2,1,2,1,7]
    输出：6
    解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
    可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
    提示：

            0 <= A.length <= 40000
            0 <= A[i] < 40000

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
  public int minIncrementForUnique(int[] A) {
      Arrays.sort(A);

      int start = -1;
      int count = 0;
      for (int i = 0; i < A.length; i++) {
          if (A[i] <= start) {
              count = count + start + 1 - A[i];
              start++;
          } else {
              start = A[i];
          }
      }

      return count;
  }

}


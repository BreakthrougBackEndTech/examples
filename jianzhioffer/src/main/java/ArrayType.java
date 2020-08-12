import java.util.*;

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
       /* Set<Integer> set = new HashSet<>();

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


        return set.stream().map(x->x+1).collect(Collectors.toList());*/


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
        int[] burket = new int[nums.length + 1];
        for(int i=0; i<nums.length; i++) {
            burket[nums[i]]++;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<nums.length+1; i++) {
            if(burket[i] == 2) list.add(i);
        }
        return list;
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
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();

        if(k > input.length)
            return list;

        Arrays.sort(input);

        for(int i=0; i<k; i++){
            list.add(input[i]);
        }

       /* PriorityQueue<Integer> pq = new PriorityQueue();
        for(int i=0; i< input.length; i++){
            pq.add(input[i]);
        }

        while (!pq.isEmpty() && k >0){
            list.add(pq.poll());
            k--;
        }*/

        return list;
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
            while (nums[i] <= temp && i < j)//再找左边的
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

    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

     ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

     请找出其中最小的元素。

     你可以假设数组中不存在重复元素。

     示例 1:

     输入: [3,4,5,1,2]
     输出: 1
     示例 2:

     输入: [4,5,6,7,0,1,2]
     输出: 0

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int findMin(int[] nums) {
        int min = Math.min(nums[0], nums[nums.length -1]);
        int left = 0, right = nums.length-1;
        while(left < right) {

            int middle = (left + right+1) / 2;

            if (nums[middle] < min) {
                min = nums[middle];
                right = middle;
            } else {
                left = middle;
            }
        }

        return min;
    }


    /**
     题目描述
     把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     */
    public int minNumberInRotateArray(int [] array) {
        if(array.length == 0)
            return 0;

        int left = 0, rigth = array.length-1;
        int middle = left;

        while(array[left] >= array[rigth]){

            if(rigth - left == 1){
                middle = rigth;
                break;
            }

            middle = (left + rigth)/2;

            if(array[left] == array[rigth] && array[middle] == array[left]){
                for(int i = left; i< rigth; i++){
                    if(array[i] < array[middle]){
                        middle = i;
                    }
                }
            }else if(array[rigth] >= array[middle]){
                rigth = middle;
            }else if(array[left] <= array[middle]){
                left = middle;
            }

        }

        return array[middle];
    }


    /**

     题目描述
     输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于
     数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数
     之间的相对位置不变。

     */
    public void reOrderArray(int [] array) {
        int oddNum = 0;
        int[] arrayEven = new int[array.length];
        int evenNum = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                array[oddNum++] = array[i];
            } else {
                arrayEven[evenNum++] = array[i];
            }
        }


        for (int i = oddNum; i < array.length; i++) {
            array[i] = arrayEven[i - oddNum];
        }
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
     * 例如，如果输入如下4 X 4矩阵：
     * 1  2  3  4
     * 5  6  7  8
     * 9  10 11 12
     * 13 14 15 16
     *
     * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     */

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        int left = 0, right = matrix[0].length - 1;
        int up = 0, down = matrix.length - 1;

        while ((left <= right && up <= down)) {
            //left -> right
            for(int i=left; i<= right; i++){
                list.add(matrix[up][i]);
            }

            //up -> down
            for(int i=up+1; i<=down; i++){
                list.add(matrix[i][right]);
            }

            //right -> left
            if(up != down) {
                for (int i = right - 1; i >= left; i--) {
                    list.add(matrix[down][i]);
                }
            }

            //down -> up
            if(left != right) {
                for (int i = down - 1; i > up; i--) {
                    list.add(matrix[i][left]);
                }
            }

            left++;
            up++;
            right--;
            down--;
        }

        return list;
    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
     * 超过数组长度的一半，因此输出2。如果不存在则输出0
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int times = 1;
        int number = array[0];

        for (int i = 1; i < array.length; i++) {
            if (times == 0) {
                number = array[i];
                times = 1;
            } else if (number == array[i]) {
                times++;
            } else {
                times--;
            }
        }


        if (times == 0) {
            return 0;
        } else {
            times = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == number) {
                    times++;
                }
            }

            if (2 * times > array.length) {
                return number;
            } else {
                return 0;
            }
        }

    }



    /**
     * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
     * 那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
     * 那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，
     * 使用GetMedian()方法获取当前读取数据的中位数。
     * @param num
     */
    ArrayList<Integer> list = new ArrayList<>();

    PriorityQueue<Integer> small = new PriorityQueue(Comparator.reverseOrder());
    PriorityQueue<Integer> big = new PriorityQueue();

    public void Insert(Integer num) {
        if (small.isEmpty()) {
            small.add(num);
        } else if (big.isEmpty()) {
            insertBig(num);
        } else if (small.size() == big.size()) {
            insertSmall(num);
        } else {
            insertBig(num);
        }
    }

    private void insertBig(Integer num) {
        if (small.peek() <= num) {
            big.add(num);
        } else {
            big.add(small.poll());
            small.add(num);
        }
    }

    private void insertSmall(Integer num) {
        if (big.peek() >= num) {
            small.add(num);
        } else {
            small.add(big.poll());
            big.add(num);
        }
    }

    public Double GetMedian() {

        if(small.size() == big.size()){
            return (small.peek() + big.peek())/2.0;
        }else{
            return Double.valueOf(small.peek());
        }

    /*    Collections.sort(list);

        if (list.size() % 2 == 1) {
            return Double.valueOf(list.get(list.size() / 2));
        } else {

            return (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2.0;
        }*/

    }

    /**
     * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:
     * 在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
     * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
     * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
     * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int maxRet = array[0];

        int maxTemp = array[0];

        for (int i = 1; i < array.length; i++) {
            maxTemp = Math.max(Math.min(0, maxRet), Math.max(array[i], maxTemp + array[i]));
            maxRet = Math.max(maxRet, maxTemp);
        }

        return maxRet;

    }

    /**
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
     * 打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     */
    public String PrintMinNumber(int [] numbers) {
        ArrayList<String> list = new ArrayList();
        for(int i=0; i< numbers.length; i++){

            list.add(String.valueOf(numbers[i]));
        }

        Collections.sort(list, (a, b) ->
                (a + b).compareTo(b + a)
        );

        StringBuilder sb = new StringBuilder();
        for(String str: list){
            sb.append(str);
        }

        return sb.toString();

    }

    /**
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，
     * 则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
     * 并将P对1000000007取模的结果输出。 即输出P%1000000007
     * @param array
     * @return
     */
    public int InversePairs(int[] array){
        if(array == null || array.length == 0)return 0;

        //用来存放复制的数组
        int[] copy = new int[array.length];

        int count = InversePairsCore(array, copy, 0, array.length-1);
        return count;
    }

    //构造递归函数
    private int InversePairsCore(int[] array, int[] copy, int low, int high) {
        //递归结束条件
        if(low == high)return 0;

        int mid = (low + high) >> 1;

        //分治算法，将数组分为两部分
        int leftCount = InversePairsCore(array, copy, low, mid) % 1000000007;
        int rightCount = InversePairsCore(array, copy, mid+1, high) % 1000000007;

        int count = 0;
        int i = mid;
        int j = high;
        int copyIndex = high;

        //将子数组合并、排序、计算逆序数
        while(i >= low && j > mid){

            //合并时前半部分的array[i]>array[j],
            //即array[j]前面的数字都会比array[i]小，以此来计算逆序数
            if(array[i] > array[j]){
                count = count + j-mid;
                copy[copyIndex--] = array[i--];

                //如果count过大，即对count进行取余处理
                if(count >= 1000000007)
                    count = count % 1000000007;
            }
            else{
                copy[copyIndex--] = array[j--];
            }
        }
        //将数组中剩余元素复制到copy数组中，排好序
        for(; i >= low; i--){
            copy[copyIndex--] = array[i];
        }
        for(; j > mid; j--){
            copy[copyIndex--] = array[j];
        }
        //将排好序的数组服回给原数组，进行下一步的合并
        for(int s=low;s <= high;s++){
            array[s] = copy[s];
        }
        return (count + leftCount + rightCount) % 1000000007;
    }
}


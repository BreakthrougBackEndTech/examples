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
    
}


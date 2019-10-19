/**
 * @author: zhegong
 **/
public class GridType {
    /**
     * 在二维平面上，我们将石头放置在一些整数坐标点上。每个坐标点上最多只能有一块石头。
     * <p>
     * 现在，move 操作将会移除与网格上的某一块石头共享一列或一行的一块石头。
     * <p>
     * 我们最多能执行多少次 move 操作？
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
     * 输出：5
     * 示例 2：
     * <p>
     * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
     * 输出：3
     * 示例 3：
     * <p>
     * 输入：stones = [[0,0]]
     * 输出：0
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= stones.length <= 1000
     * 0 <= stones[i][j] < 10000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int removeStones(int[][] stones) {
        int m = stones.length;
        int[] parents = new int[m];

        /**
         * 不需要用rank来优化压缩路径,
         */
//        int[] rank = new int[m];
        for (int i = 0; i < m; i++) {
            parents[i] = i;
//            rank[i] = 0;
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (stones[j][0] == stones[i][0] || stones[j][1] == stones[i][1]) {
                    int p1 = find(parents, i);
                    int p2 = find(parents, j);

                    if (p1 != p2) {
             /*           if(rank[p1] > rank[p2]){
                            parents[p2] = p1;
                        }else{
                            parents[p1] = p2;
                            if(rank[p1] == rank[p2]){
                                rank[p2]++;
                            }
                        }*/
                        parents[p2] = p1;
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private int find(int[] parents, int i) {
        if (parents[i] == i)
            return i;
        else
            return parents[i] = find(parents, parents[i]);
    }
}

import java.util.Arrays;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-10-22 10:04
 **/
public class Backtracking {
    /**
     * 题目描述
     请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
     路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
     如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
     例如 a b c e s f c s a d e e 矩阵中包含一条字符串"bcced"的路径，
     但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，
     路径不能再次进入该格子
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        boolean[] status = new boolean[matrix.length];
        Arrays.fill(status, true);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (hasPathCore(matrix, rows, cols, str, status, row, col, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasPathCore(char[] matrix, int rows, int cols, char[] str, boolean[] status, int row, int col, int strIndex){
        if(strIndex == str.length){
            return true;
        }

        boolean hasPath = false;
        if(row >=0 && col >=0 && row < rows && col < cols && matrix[row*cols + col] == str[strIndex] && status[row*cols + col]){
            status[row*cols + col] = false;
            strIndex++;

            hasPath = hasPathCore(matrix, rows, cols, str, status, row-1, col, strIndex)||
                    hasPathCore(matrix, rows, cols, str, status, row, col-1, strIndex)||
                    hasPathCore(matrix, rows, cols, str, status, row+1, col, strIndex)||
                    hasPathCore(matrix, rows, cols, str, status, row, col+1, strIndex);

            if(!hasPath){
                status[row*cols + col] = true;
            }
        }

        return hasPath;
    }
}


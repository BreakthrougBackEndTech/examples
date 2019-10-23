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


    /**
     地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
     但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
     但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     */
    public int movingCount(int threshold, int rows, int cols) {
        boolean[] visited = new boolean[rows * cols];
        return movingCountCore(threshold, rows, cols, visited, 0, 0);
    }

    private int movingCountCore(int threshold, int rows, int cols, boolean[] visited, int row, int col){
        int count =0;

        if(row >=0 && row< rows && col>=0 && col < cols && !visited[row*cols + col]
                && notGreaterThanThreshold(row, col, threshold)){
            visited[row*cols + col] = true;

            count=1+ movingCountCore(threshold, rows, cols, visited, row-1, col)
                    + movingCountCore(threshold, rows, cols, visited, row+1, col)
                    + movingCountCore(threshold, rows, cols, visited, row, col-1)
                    + movingCountCore(threshold, rows, cols, visited, row, col+1);
        }

        return count;
    }

    private boolean notGreaterThanThreshold(int row, int col, int threshold){
        int sum =0;

        while (row > 0) {
            sum += row % 10;
            row /= 10;
        }

        while (col > 0) {
            sum += col % 10;
            col /= 10;
        }

        if(sum > threshold){
            return false;
        }
        return true;
    }
}


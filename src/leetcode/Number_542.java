package leetcode;

/**
 * 542.01矩阵
 * 定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 *
 * 示例 1:
 * 输入:
 *
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 *
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/01-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_542 {
    //初始矩阵值为MAX，MAX=长+宽+1
    //该方法多循环几次就可以AC，应该是有一些特殊情况，大部分位置都是对的，存在bug
    public int[][] updateMatrix(int[][] matrix) {
        int len=matrix.length;
        int wid=matrix[0].length;
        int[][] matrix2=new int[len][wid];
        int max=10000;
        //先对matrix2都取最大值
        for (int[] i:matrix2){
            for (int j:i){
                j=max;
            }
        }
        for (int i=0;i<matrix.length;i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix2[i][j] = 0;
                    //顺序更新周围
                    updateNeighbor(matrix, matrix2, i, j, max);
                } else {
                    updateNeighbor(matrix, matrix2, i, j, max);
                }
            }
        }
        for (int i=matrix.length-1;i>-1;i--){
            for (int j=matrix[0].length-1;j>-1;j--){
                if (matrix[i][j]==0){
                    matrix2[i][j]=0;
                    //逆序更新周围
                    updateNeighbor(matrix,matrix2,i,j,max);
                }else {
                    updateNeighbor(matrix,matrix2,i,j,max);
                }
            }
        }
        for (int i=0;i<matrix.length;i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix2[i][j] = 0;
                    //顺序更新周围
                    updateNeighbor(matrix, matrix2, i, j, max);
                } else {
                    updateNeighbor(matrix, matrix2, i, j, max);
                }
            }
        }
        for (int i=matrix.length-1;i>-1;i--){
            for (int j=matrix[0].length-1;j>-1;j--){
                if (matrix[i][j]==0){
                    matrix2[i][j]=0;
                    //逆序更新周围
                    updateNeighbor(matrix,matrix2,i,j,max);
                }else {
                    updateNeighbor(matrix,matrix2,i,j,max);
                }
            }
        }
        for (int i=matrix.length-1;i>-1;i--){
            for (int j=matrix[0].length-1;j>-1;j--){
                matrix2[i][j]=updateBasedNeighbor(matrix,matrix2,i,j,max);
            }
        }
        return matrix2;
    }
    //从它周围4个数的最小值+1
    public int updateBasedNeighbor(int[][] matrix,int[][] matrix2,int i,int j,int max){
            if (matrix[i][j]==0)
                return 0;
            int tmp1,tmp2,tmp3,tmp4;
            if (i-1<0) tmp1=max;
            else tmp1=matrix[i-1][j]==0?matrix[i-1][j]:matrix2[i-1][j];
            if (j-1<0) tmp2=max;
            else tmp2=matrix[i][j-1]==0?matrix[i][j-1]:matrix2[i][j-1];
            if (j+1>matrix[0].length-1) tmp3=max;
            else tmp3=matrix[i][j+1]==0?matrix[i][j+1]:matrix2[i][j+1];
            if (i+1>matrix.length-1) tmp4=max;
            else tmp4=matrix[i+1][j]==0?matrix[i+1][j]:matrix2[i+1][j];
            return Math.min(Math.min(tmp1,tmp2),Math.min(tmp3,tmp4))+1;
    }
    public void updateNeighbor(int[][] matrix,int[][] matrix2,int i,int j,int max){
        if (i-1>=0) matrix2[i-1][j]=updateBasedNeighbor(matrix,matrix2,i-1,j,max);
        if (j-1>=0) matrix2[i][j-1]=updateBasedNeighbor(matrix,matrix2,i,j-1,max);
        if (j+1<=matrix[0].length-1) matrix2[i][j+1]=updateBasedNeighbor(matrix,matrix2,i,j+1,max);
        if (i+1<=matrix.length-1) matrix2[i+1][j]=updateBasedNeighbor(matrix,matrix2,i+1,j,max);
    }


    /*-----------------dp方法--------------*/
    private int row;
    private int col;

    /**
     * DP（两次遍历，可 AC）
     */
    public int[][] updateMatrix_1(int[][] matrix) {
        row = matrix.length;
        col = matrix[0].length;
        int[][] result=new int[row][col];
        // 第一次遍历，正向遍历，根据相邻左元素和上元素得出当前元素的对应结果
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    result[i][j] = row + col;
                }
                if (i > 0) {
                    result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
                }
                if (j > 0) {
                    result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
                }
            }
        }
        // 第二次遍历，反向遍历，根据相邻右元素和下元素及当前元素的结果得出最终结果
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (i < row - 1) {
                    result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
                }
                if (j < col - 1) {
                    result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
                }
            }
        }
        return matrix;
    }
}

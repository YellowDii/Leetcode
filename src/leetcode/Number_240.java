package leetcode;

public class Number_240 {
    /**
     * 240. 搜索二维矩阵 II(search-a-2d-matrix-ii)
     *
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     *
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * 示例:
     *
     * 现有矩阵 matrix 如下：
     *
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     *
     * 给定 target = 20，返回 false。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //好像也是剑指offer的题
    //从右上角往左下角搜
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix==null||matrix.length<1||matrix[0].length<1){
            return false;
        }
        int rows=matrix.length;
        int cols=matrix[0].length;
        int i=0,j=cols-1;
        while (true){
            while (j>=0&&target<matrix[i][j]){
                j--;
            }
            if (j<0){
                return false;
            }
            if (target==matrix[i][j]){
                return true;
            }
            while (i<rows&&target>matrix[i][j]){
                i++;
            }
            if (i>=rows){
                return false;
            }
            if (target==matrix[i][j]){
                return true;
            }
        }
    }
}

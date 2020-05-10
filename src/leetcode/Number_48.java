package leetcode;

public class Number_48 {
    /**
     * 48.旋转图像（rotate-image）
     * 给定一个 n × n 的二维矩阵表示一个图像。
     *
     * 将图像顺时针旋转 90 度。
     *
     * 说明：
     *
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     *
     * 示例 1:
     *
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     * 示例 2:
     *
     * 给定 matrix =
     * [
     *   [ 5, 1, 9,11],
     *   [ 2, 4, 8,10],
     *   [13, 3, 6, 7],
     *   [15,14,12,16]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [15,13, 2, 5],
     *   [14, 3, 4, 1],
     *   [12, 6, 8, 9],
     *   [16, 7,10,11]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-image
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //找与对角线对称（右上到左下）的 进行交换 然后对水平线进行交换
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        for (int i=0;i<n;i++){
            for (int j=0;j+i<n-1;j++){
                //对角线以上
                int[] pos=new int[]{i,j};
                int[] swap_pos=symmetry(pos,n);
                swap(pos,swap_pos,matrix);
            }
        }
        for (int i=0;i<n/2;i++){
            for (int j=0;j<n;j++){
                int[] pos=new int[]{i,j};
                int[] swap_pos=new int[]{n-1-i,j};
                swap(pos,swap_pos,matrix);
            }
        }
    }

    private void swap(int[] pos, int[] swap_pos, int[][] matrix) {
        int tmp=matrix[pos[0]][pos[1]];
        matrix[pos[0]][pos[1]]=matrix[swap_pos[0]][swap_pos[1]];
        matrix[swap_pos[0]][swap_pos[1]]=tmp;
    }

    private int[] symmetry(int[] pos,int matrix_len){
        int[] new_pos=new int[2];
        new_pos[0]=matrix_len-1-pos[0];
        new_pos[1]=matrix_len-1-pos[1];
        int tmp=new_pos[0];
        new_pos[0]=new_pos[1];
        new_pos[1]=tmp;
        return new_pos;
    }
}

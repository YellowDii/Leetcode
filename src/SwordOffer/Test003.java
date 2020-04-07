package SwordOffer;

public class Test003 {
    /**
     * 题目：
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p/>
     *
     * 规律：首先选取数组中右上角的数字。如果该数字等于要查找的数字，查找过程结束：
     * 如果该数字大于要查找的数字，剔除这个数字所在的列：如果该数字小于要查找的数字，剔除这个数字所在的行。
     * 也就是说如果要查找的数字不在数组的右上角，则每－次都在数组的查找范围中剔除）行或者一列，这样每一步都可以缩小
     * 查找的范围，直到找到要查找的数字，或者查找范围为空。
     *
     * @param matrix 待查找的数组
     * @param number 要查找的数
     * @return 查找结果，true找到，false没有找到
     */
    public static boolean find(int[][] matrix,int number){
        if (matrix==null||matrix.length<1||matrix[0].length<1){
            return false;//输入条件判断
        }
        int rows=matrix.length;
        int cols=matrix[0].length;
        int n=cols-1;//列遍历位置
        int m=0;//行遍历位置
        while (m<rows&&n>=0&&m>=0&&n<=cols-1){
            if (matrix[m][n]==number){
                return true;
            }else if (matrix[m][n]>number){
                n--;
            }else {
                m++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix={
                {5, 6, 24, 94},
                {11, 24, 91, 132},
                {32, 37, 100, 133},
                {42, 86, 111, 215}
        };
        Test003 t=new Test003();
        System.out.println(t.find(matrix,133));
        System.out.println(t.find(matrix,111));
        System.out.println(t.find(matrix,32));
        System.out.println(t.find(matrix,45));
    }
}

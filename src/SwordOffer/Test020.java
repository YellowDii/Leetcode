package SwordOffer;

public class Test020 {
    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印每一个数字
     */
    public static void printMatrixClockWisely(int[][] numbers){
        //一圈圈的输出
        if (numbers==null){
            return;
        }
        int x=0;
        int y=0;
        while (x*2<numbers.length&&y*2<numbers[0].length){
            printMatrixInCircle(numbers,x,y);
            x++;
            y++;
        }
    }

    private static void printMatrixInCircle(int[][] numbers, int x, int y) {
        int rows=numbers.length;
        int cols=numbers[0].length;
        for (int i=y;i<=cols-y-1;i++){
            //输出最上面的一行
            System.out.print(numbers[x][i]+" ");
        }
        // 环的高度至少为3才会输出右边的一列
        if (rows-x-1>x+1){
            //rows-x-1 代表右边的最下边的列
            //这里输出到rows-x-1-1
            for (int i=x+1;i<=rows-x-2;i++){
                System.out.print(numbers[i][cols-y-1]+" ");
            }
        }
        //环的下面一行 高度至少要为2
        if (rows-x-1>x){
            for (int i=cols-y-1;i>=y;i--){
                System.out.print(numbers[rows-x-1][i]+" ");
            }
        }
        //环的左边 高度至少为3 宽度至少为2(针对只剩一列的情况)
        if (rows-x-1>x+1&&cols-y-1>y){
            for (int i=rows-x-2;i>x;i--){
                System.out.print(numbers[i][x]+" ");
            }
        }
    }

    public static void main(String[] args) {
        //测试例子
        int[][] numbers = {
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9},
        };
        printMatrixClockWisely(numbers);
        System.out.println();
        int[][] numbers2 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {22, 23, 24, 25, 26, 27, 28, 9},
                {21, 36, 37, 38, 39, 40, 29, 10},
                {20, 35, 34, 33, 32, 31, 30, 11},
                {19, 18, 17, 16, 15, 14, 13, 12},

        };
        printMatrixClockWisely(numbers2);
        System.out.println();
        int[][] numbers3 = {
                {1, 2, 3, 4, 5, 6, 7, 8}
        };
        printMatrixClockWisely(numbers3);
        System.out.println();

        int[][] numbers4 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {16, 15, 14, 13, 12, 11, 10, 9}
        };
        printMatrixClockWisely(numbers4);
        System.out.println();


        int[][] numbers5 = {
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
                {8}
        };
        printMatrixClockWisely(numbers5);
        System.out.println();

        int[][] numbers6 = {
                {0, 1},
                {15, 2},
                {14, 3},
                {13, 4},
                {12, 5},
                {11, 6},
                {10, 7},
                {9, 8}
        };
        printMatrixClockWisely(numbers6);
        System.out.println();


        int[][] numbers7 = {
                {1, 2},
                {4, 3}
        };
        printMatrixClockWisely(numbers7);
        System.out.println();

        int[][] numbers8 = {
                {1}
        };
        printMatrixClockWisely(numbers8);
        System.out.println();

        // 0个元素的数组
        printMatrixClockWisely(new int[][]{{}});
        // 空数组
        printMatrixClockWisely(null);
    }
}

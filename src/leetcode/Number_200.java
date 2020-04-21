package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Number_200 {
    /**
     * 200.岛屿问题（number-of-islands）
     * 
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格请你计算网格中岛屿的数量。
     * 
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 
     * 此外，你可以假设该网格的四条边均被水包围。
     * 
     * 示例 1:
     * 
     * 输入:
     * 11110
     * 11010
     * 11000
     * 00000
     * 输出: 1
     * 示例 2:
     * 
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     * 输出: 3
     * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
     * 
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //有bug 气死我了
    public static int numIslands(char[][] grid) {
        int max = 0;int count=0;
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return max;
        }
        int[][] mark = new int[grid.length][grid[0].length];
        Map<String,Integer> conflict=new HashMap<String,Integer>();
        for (int i = 0; i < mark.length; i++) {
            for (int j = 0; j < mark[0].length; j++) {
                if (grid[i][j] == '0') {
                    mark[i][j] = 0;
                } else {
                    int up = 0;
                    int left = 0;
                    if (i >= 1) {
                        up = mark[i - 1][j];
                    }
                    if (j >= 1) {
                        left = mark[i][j - 1];
                    }
                    if (up == 0 && left == 0) {
                        mark[i][j] = ++max;
                    } else if (up == 0 || left == 0) {
                        mark[i][j] = (up == 0 ? left : up);
                    } else {
                        if (up==left){
                            mark[i][j]=up;
                        }else {
                            int m1=Math.min(up,left);
                            int m2=Math.max(up,left);
                            mark[i][j]=m1;
                            String key=m1+""+m2;
                            if (conflict.get(key)==null){
                                conflict.put(key,1);

                                count++;
                            }
                        }
                    }
                }
            }
        }
        return max-count;
    }

    //dfs
    void dfs(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }


}

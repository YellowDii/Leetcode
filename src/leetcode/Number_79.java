package leetcode;

public class Number_79 {
    /**
     * 79.单词搜索（word-search）
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     * 示例:
     *
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     *  
     *
     * 提示：
     *
     *  1. board 和 word 中只包含大写和小写英文字母。
     *  2. 1 <= board.length <= 200
     *  3. 1 <= board[i].length <= 200
     *  4. 1 <= word.length <= 10^3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-search
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //感觉能用dfs  或者说回溯
    private static int[][] dires = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int row, col;
    private boolean hasFind;
    private boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        row = board.length;
        col = board[0].length;
        hasFind = false;
        if(row * col < word.length())
            return false;
        visited = new boolean[row][col];
        char[] chars = word.toCharArray();
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(board[i][j] == chars[0]){
                    backTrack(board, chars, 1, i, j);
                    if(hasFind)
                        return true;
                }
            }
        }
        return false;
    }

    private void backTrack(char[][] board, char[] word, int curIndex, int x, int y){
        if(hasFind)
            return;
        if(curIndex == word.length){
            hasFind = true;
            return;
        }
        visited[x][y] = true;
        for(int[] dire : dires){
            int newX = x + dire[0], newY = y + dire[1];
            if(isIn(newX, newY) && !visited[newX][newY] && board[newX][newY] == word[curIndex])
                backTrack(board, word, curIndex + 1, newX, newY);
        }
        visited[x][y] = false;
    }

    private boolean isIn(int x, int y){
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}

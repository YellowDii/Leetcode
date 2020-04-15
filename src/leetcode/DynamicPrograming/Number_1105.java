package leetcode.DynamicPrograming;
/*
1105. 填充书架(filling-bookcase-shelves)

附近的家居城促销，你买回了一直心仪的可调节书架，打算把自己的书都整理到新的书架上。

你把要摆放的书 books 都整理好，叠成一摞：从上往下，第 i 本书的厚度为 books[i][0]，高度为 books[i][1]。

按顺序 将这些书摆放到总宽度为 shelf_width 的书架上。

先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelf_width），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。

需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。

每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。

以这种方式布置书架，返回书架整体可能的最小高度。

 

示例：
    看图片更清晰
    注释插不了图片 〒_〒

输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
输出：6
解释：
3 层书架的高度和为 1 + 3 + 2 = 6 。
第 2 本书不必放在第一层书架上。
 

提示：

    1. 1 <= books.length <= 1000
    2. 1 <= books[i][0] <= shelf_width <= 1000
    3. 1 <= books[i][1] <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/filling-bookcase-shelves
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_1105 {
    String filling_bookcase_shelves="1105. 填充书架(filling-bookcase-shelves)";
    public int minHeightShelves(int[][] books, int shelf_width) {
        int[] dp = new int[books.length];
        return f(books, shelf_width, 0, dp);
    }
    // 逐层遍历
    // 用dp减少遍历计算量
    //相当于求dp[i]并返回
    private int f(int[][] books, int shelf_width, int i, int[] dp){
        if(i >= books.length){
            return 0;
        }
        if(dp[i] != 0){
            return dp[i];
        }
        int highest = 0;
        int curWidth = shelf_width;
        int res = 1000*1000;
        for(int k = i; k < books.length; k++){
            curWidth -= books[k][0]; // 用来处理每一层
            if(curWidth < 0){
                break;
            }
            //求每层最高长度
            highest = Math.max(highest, books[k][1]);
            res = Math.min(res, f(books, shelf_width, k+1, dp) + highest);
        }
        return dp[i] = res;
    }
}

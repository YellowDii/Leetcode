package leetcode.ByteDance;

public class Number_135 {
    /**
     * 135.分发糖果（candy）
     *
     * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     *
     * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
     *
     * 每个孩子至少分配到 1 个糖果。
     * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
     * 那么这样下来，老师至少需要准备多少颗糖果呢？
     *
     * 示例 1:
     *
     * 输入: [1,0,2]
     * 输出: 5
     * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
     * 示例 2:
     *
     * 输入: [1,2,2]
     * 输出: 4
     * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
     *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/candy
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //这个有点暴力 但是能ac。。。  时间要1s...
    public int candy(int[] ratings) {
        if (ratings==null||ratings.length<1){
            return 0;
        }
        int ans=0;
        int[] candy=new int[ratings.length];
        candy[0]=1;
        for (int i=1;i<ratings.length;i++){
            if (ratings[i-1]<ratings[i]){
                candy[i]=candy[i-1]+1;
            }else {
                candy[i]=1;
                int j=i;
                while (!isOk(candy,ratings,j-1,j)){
                    j--;
                }
            }
        }
        for (int c:candy){
            ans+=c;
        }
        return ans;
    }

    //判断i i+1 两个位置是否满足条件
    private boolean isOk(int[] candy,int[] ratings,int left, int right) {
        if (left<0){
            return true;
        }
        if (ratings[left]>ratings[right]&&candy[left]<=candy[right]){
            candy[left]=candy[right]+1;
            return false;
        }else {
            return true;
        }
    }

    public int count(int n) {
        return (n * (n + 1)) / 2;
    }
    //山峰 slope确定走向 分配时总是山底到山顶 1 +2 +。。 +n 如果是平坡就是 1+1.。
    public int candy2(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }
        int candies = 0;
        int up = 0;
        int down = 0;
        int old_slope = 0;
        for (int i = 1; i < ratings.length; i++) {
            //1表示上坡 0表示平坡 -1表示下坡
            int new_slope = (ratings[i-1]<ratings[i]) ? 1 : (ratings[i-1] > ratings[i] ? -1 : 0);
            //与前面的角度不一致 出现起伏时 计算并重置
            if ((old_slope > 0 && new_slope == 0) || (old_slope < 0 && new_slope >= 0)) {
                candies += count(up) + count(down) + Math.max(up, down);//前面的路 + 山顶
                up = 0;
                down = 0;
            }
            if (new_slope > 0)
                up++;
            if (new_slope < 0)
                down++;

            //平坡直接+1
            if (new_slope == 0)
                candies++;

            old_slope = new_slope;
        }
        candies += count(up) + count(down) + Math.max(up, down) + 1;
        return candies;
    }

}

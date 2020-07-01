package SwordOffer;

import java.util.ArrayList;
import java.util.List;

public class Leet060 {
    /**
     * 060.60. n个骰子的点数
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。就是和为s
     * 输入n，打印出s的所有可能的值出现的概率。
     *
     *  
     *
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: 1
     * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
     * 示例 2:
     *
     * 输入: 2
     * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
     *  
     *
     * 限制：
     *
     * 1 <= n <= 11
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //dp[i][j]表示 投掷完i次骰子后，点数j的出现次数 点数指的是和
    public double[] twoSum(int n) {
        int[][] dp=new int[n][n*6+1];
        List<Double> ans=new ArrayList<>();
        for (int i=1;i<=6;i++){
            dp[0][i]=1;
        }
        for (int i=1;i<n;i++){
            for (int j=i+1;j<=(i+1)*6;j++){
                for(int cur=1;cur<=6;cur++){
                    if (j-cur>0){
                        dp[i][j]+=dp[i-1][j-cur];
                    }else {
                        break;
                    }
                }
            }
        }
        double all=Math.pow(6,n);
        for (int count:dp[n-1]){
            if (count!=0){
                ans.add(count/all);
            }
        }
        double[] res=ans.stream().mapToDouble(i->i).toArray();
        return res;
    }
}

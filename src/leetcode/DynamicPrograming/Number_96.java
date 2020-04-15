package leetcode.DynamicPrograming;

/**
 * 动态规划系列
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_96 {
    //根据动态规划
    //这题结构只的是树的形状，与上面的值大小无关，所以n=1 输出1 ；n=2 输出2 ；n=3 输出5
    //dp[i]表示i个数字时 共有多少种不同结构二叉搜索树
    //可以这样规划 1至n 可以将它分为 左子树{1...i} 根节点i 右子树{i+1 ...n} 将上述视为情况q（i,n）
    //当n取某一值时，这样划分出来的子集q(i,n)中的结构与q(j,n)子集中的结构必不相同，因为左右子树的个数各不相同  (j！=i&&1<j<n)
    public int numTrees(int n) {
        if(n==1)
            return 1;
        if(n==2)
            return 2;
        int[] dp=new int[n+1];
        dp[0]=0;dp[1]=1;dp[2]=2;
        for (int i=3;i<=n;i++){
            dp[i]=0;
            for (int j=1;j<=i;j++){
                int left=dp[j-1];
                int right=dp[i-j];
                if (left==0){
                    dp[i]+=1*right;
                }else if (right==0){
                    dp[i]+=1*left;
                }else {
                    dp[i]+=left*right;
                }
            }
        }
        return dp[n];
    }
    //其数量是卡特兰数
    //C（0）=1 ; C(n+1) = (2*(2n+1)/n+2)*C(n)
    public int numTrees2(int n){
        // Note: we should use long here instead of int, otherwise overflow
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;

    }
}

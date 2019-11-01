package test;

/**
 * 动态规划系列
 * 338. 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_338 {
    //此题官方给出了特别详细的解答 这边不做多余注解
    //不用动态规划
    public int[] countBits(int num) {
        int[] ans=new int[num+1];
        for(int i=0;i<num+1;i++){
            ans[i]=countBit(i);
        }
        return ans;
    }
    public int countBit(int num){
        int count=0;
        for (count=0;num!=0;count++ ){
            num=num&num-1;
        }
        return count;
    }
    //动态规划+最高有效位
    //p(x+b)=p(x)+1,b=2的m次方 b>x
    public int[] countBist2(int num){
        int[] ans=new int[num+1];
        int b=1,i=0;
        while (b<=num){
            //从[0,b）生成[b,2b)或[b,num）
            while (i<b&&i+b<=num){
                ans[i+b]=ans[i]+1;
                i++;
            }
            i=0;//reset i=0
            b<<=1;//b=2b
        }
        return ans;
    }
    //动态规划+最低有效位
    //p(x)=p(x/2)+(x mod 2)
    public int[] countBits3(int num){
        int[] ans=new int[num+1];
        for (int i=0;i<num+1;i++){
            ans[i]=ans[i>>1]+(i&1);
        }
        return ans;
    }
    //动态规划 结合最后设置位
    //p(x)=p(x&(x-1))+1
    //注意i从1开始
    public int[] countBits4(int num){
        int[] ans=new int[num+1];
        for (int i=1;i<num+1;i++){
            ans[i]=ans[i&(i-1)]+1;
        }
        return ans;
    }
}

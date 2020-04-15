package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 有 A 和 B 两种类型的汤。一开始每种类型的汤有 N 毫升。有四种分配操作：
 *
 * 提供 100ml 的汤A 和 0ml 的汤B。
 * 提供 75ml 的汤A 和 25ml 的汤B。
 * 提供 50ml 的汤A 和 50ml 的汤B。
 * 提供 25ml 的汤A 和 75ml 的汤B。
 * 当我们把汤分配给某人之后，汤就没有了。每个回合，我们将从四种概率同为0.25的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。当两种类型的汤都分配完时，停止操作。
 *
 * 注意不存在先分配100 ml汤B的操作。
 *
 * 需要返回的值： 汤A先分配完的概率 + 汤A和汤B同时分配完的概率 / 2。
 *
 * 示例:
 * 输入: N = 50
 * 输出: 0.625
 * 解释:
 * 如果我们选择前两个操作，A将首先变为空。对于第三个操作，A和B会同时变为空。对于第四个操作，B将首先变为空。
 * 所以A变为空的总概率加上A和B同时变为空的概率的一半是 0.25 *(1 + 1 + 0.5 + 0)= 0.625。
 * 注释:
 *
 * 0 <= N <= 10^9。
 * 返回值在 10^-6 的范围将被认为是正确的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/soup-servings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_808 {
    //方法1 递归+备忘录
    //超时！
    public double soupServings1(int N) {
        double result=0d;
        Map<Integer[],Double> menmory=new HashMap<>();
        if (N>4800)
            return 1;
        else
            return resolve(N,N,menmory);
    }

    private double resolve(int A, int B, Map<Integer[], Double> menmory) {
        Integer[] key=new Integer[2];
        key[0]=A;
        key[1]=B;
        double result;
        if (menmory.get(key)!=null)
            return menmory.get(key);
        else if (A<=0&&B>0)
            result= 1;
        else if (A<=0&&B<=0)
            result=0.5;
        else if (A>0&&B<=0)
            result=0;
        else
            result=0.25*(resolve(A-100,B-25,menmory)+resolve(A-75,B-25,menmory)+
                    resolve(A-50,B-50,menmory)+resolve(A-25,B-75,menmory));
        menmory.put(key,result);
        return result;
    }
    //动态规划
    public double soupServings2(int N) {
        if(N>4800)
            return 1;
        N=N%25==0?N/25:N/25+1;
        double[][] d=new double[N+1][N+1];
        d[0][0]=0.5;
        //注意后面的i都是从1开始的，因为从0开始会把d[0][0]覆盖
        for (int i=1;i<N+1;i++){
            d[i][0]=0;
            d[0][i]=1;
        }
        for (int i=1;i<N+1;i++){
            for (int j=1;j<N+1;j++){
                d[i][j]=0.25*(d[Math.max(i-4,0)][j]+d[Math.max(i-3,0)][Math.max(j-1,0)]
                +d[Math.max(i-2,0)][Math.max(j-2,0)]+d[Math.max(i-1,0)][Math.max(j-3,0)]);
            }
        }
        System.out.println(N);
        return d[N][N];
    }

}

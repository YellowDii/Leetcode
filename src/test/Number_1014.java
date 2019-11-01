package test;

/**
 * 1014.最佳观光组合
 *
 */
public class Number_1014 {
    //暴力法超时
    public int maxScoreSightseeingPair1(int[] A) {
        int[] Anew = new int[A.length];//记录A[i]+i的值
        for (int i=0;i<A.length;i++){
            Anew[i]=A[i]+i;
        }
        int max=0;
        for (int i=0;i<Anew.length;i++){
            for (int j=i+1;j<Anew.length;j++){
                int cur=Anew[i]+Anew[j]-2*j;
                if(max<cur) max=cur;
            }
        }
        return max;
    }
    //已经 想到A[i]+i为固定值，只需加上A[i]-i就得到最终值，所以我们需要记录下A[i]+i的最大值
    public int maxScoreSightseeingPair2(int[] A) {
        int[] Anew = new int[A.length];//记录A[i]+i的值
        for (int i=0;i<A.length;i++){
            Anew[i]=A[i]+i;
        }
        int max=0;
        int premax=0;
        for(int i=0;i<Anew.length;i++){
            max=Math.max(max,premax+Anew[i]-2*i);
            premax=Math.max(premax,Anew[i]);
        }
        return max;
    }
    //可以不创建Anew 内存使用更少
    public int maxScoreSightseeingPair3(int[] A) {
        int max=0;
        int premax=0;
        for(int i=0;i<A.length;i++){
            max=Math.max(max,premax+A[i]-i);
            premax=Math.max(premax,A[i]+i);
        }
        return max;
    }
}

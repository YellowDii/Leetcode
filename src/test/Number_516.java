package test;

/**
 * 516.最长回文子序列
 * 注意子序列与子字符串的区别
 * 子字符串得是连续的，子序列不需要连续，但需要顺序与原顺序相同
 *
 * 回文指倒序与顺序一致  如 “aba”
 */
public class Number_516 {
    /*
        动态规划思想，令d[i][i]=1，i= 0：s.length-1
            d[i][j]表示第j位与第i位间的最长回文子序列长度 j>=i
        j-i=1时，如果
                   d[i][j]=1或者2 取决于第i位与第j位字符是否相同
        j-i>1时
        (当第i位与第j位字符相同时) d[i][j]=d[i+1][j-1]+2
        (当第i位与第j位字符不同时) d[i][j]=max(d[i+1][j],d[i][j-1])

     */
    //方法1超时了，通过了60+个案例
    //该方法没用引入数组，第二个方法尝试空间换时间
    public int longestPalindromeSubseq1(String s) {
        return getD(0,s.length()-1,s);
    }
    public int getD(int i,int j,String s){
        if (j==i) return 1;
        if(j-i==1){
            if (s.charAt(i)==s.charAt(j)) return 2;
            else return 1;
        } else {
            if(s.charAt(i)==s.charAt(j)) return getD(i+1,j-1,s)+2;
            else return Math.max(getD(i+1,j,s),getD(i,j-1,s));
        }
    }
    //该方法通过，但用时与内存消耗只击败了10%
    public int longestPalindromeSubSeq2(String s){
        int len=s.length();
        int[][] d=new int[len][len];
        //p表示对角线位移长度，先开始计算[i][i]，然后慢慢向右上角移动，最终计算[0][s.length-1]。
        for (int p=0;p<len;p++){
            for (int i=0;i<len&&i+p<len;i++){
                if(p==0) d[i][i+p]=1;
                else if(p==1) {
                    if(s.charAt(i)==s.charAt(i+p))
                        d[i][i+p]=2;
                    else d[i][i+p]=1;
                }else {
                    if (s.charAt(i)==s.charAt(i+p)){
                        d[i][i+p]=d[i+1][i+p-1]+2;
                    }else d[i][i+p]=Math.max(d[i+1][i+p],d[i][i+p-1]);
                }
            }
        }
        return d[0][len-1];
    }
    //评论中推荐方法，可以将空间缩小到O(n)
    public int longestPalindromeSubSeq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] pre = new int[n];
        int[] cur = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            cur[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    cur[j] = pre[j - 1] + 2;
                } else {
                    cur[j] = Math.max(pre[j], cur[j - 1]);
                }
            }
            int[] temp = pre;
            pre = cur;
            cur = temp;
        }
        return pre[n - 1];
    }


}

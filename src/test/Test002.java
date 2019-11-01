package test;

/**
 * 面试题2
 * 给定一个字符串s,我们定义一个“循环节”是这个字符串中最短的重复出现的片段，循环节的长度记为k  字符串首尾两端的循环节可以“不完整” 即只需满足s[i]=s[i%k];
 * 举例：
 * “ABAABAA”的循环节是“ABA” 长度为3
 * “AAAA”的循环节是“A”  长度为1
 * “AABBB”的循环节是“AABBB”  长度为5
 * 假设s由a个字母A和b个字母B组成 问s可能形成的“循环节长度k”有多少种可能（注意是k的取值 而非循环节本身的可能）
 *
 * 输入描述  输入仅有一行  为两个正整数  用空格隔开  分别代表a和b(1<=a<=10^9,1<=b<=10^9)
 *
 *     输出描述  一个整数  代表循环节长度k的可能取值有多少种
 *
 *     实例 ：输入
 *     2 4
 *
 *    输出
 *    4
 *    说明：
 *    k=3 ”BBABBA"
 *     K=4 “BBAABB”
 *     K=5 "BBBAAB"
 *     K=6 "AABBBB"
 */
public class Test002 {
    //暴力法 强行遍历每种可能 超时啦
    public  int solution1(int a, int b){
        if (a==0||b==0)
            return a==0?b:a;
        int ans=0;
        for (int i=1;i<=a+b;i++){
            if (isOk(i,a,b)){
                ans++;
            }
        }
        return ans;
    }
    public  boolean isOk(int i,int a,int b){
        int n=(a+b)/i;
        for (int anums=0;anums<=i;anums++){
            int bnums=i-anums;
            int a1=n*anums;
            int b1=n*bnums;
            if (a>=a1&&b>=b1&&a-a1<=anums&&b-b1<=bnums){
                return true;
            }
        }
        return false;
    }
}

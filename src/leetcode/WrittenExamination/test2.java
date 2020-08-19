package leetcode.WrittenExamination;

import java.util.Scanner;

public class test2 {
    static int n;//面粉
    static int k;//粽子馅料种类
    static int c0;//只花面粉数量
    static int d0;//出售价值
    static int[] ai;//馅料存量
    static int[] bi;//消耗馅料数量
    static int[] ci;//消耗面粉数量
    static int[] di;//获得收益
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n=in.nextInt();
        k=in.nextInt();
        c0=in.nextInt();
        d0=in.nextInt();
        for (int i=0;i<k;i++){
            ai[i]=in.nextInt();
            bi[i]=in.nextInt();
            ci[i]=in.nextInt();
            di[i]=in.nextInt();
        }
        solve();
    }

    private static void solve() {

    }
}

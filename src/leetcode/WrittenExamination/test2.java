package leetcode.WrittenExamination;

import java.util.Scanner;

public class test2 {
    static int n;//���
    static int k;//������������
    static int c0;//ֻ���������
    static int d0;//���ۼ�ֵ
    static int[] ai;//���ϴ���
    static int[] bi;//������������
    static int[] ci;//�����������
    static int[] di;//�������
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

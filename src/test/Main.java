package test;

import DynamicPrograming.*;

import java.util.*;
//    public static void main(String[] args) {
//        Scanner scanner=new Scanner(System.in);
//        int a=scanner.nextInt();
//        int b=scanner.nextInt();
//        System.out.println(solution(a,b));
//    }
//    public static int solution(int a, int b){
//        if (a==0||b==0)
//            return a==0?b:a;
//        int min=a>b?b:a;
//        int ans=0;
//        for (int i=1;i<=a+b;i++){
//            if (isOk(i,a,b)){
//                ans++;
//            }
//        }
//        return ans;
//    }
//    public static boolean isOk(int i,int a,int b){
//        int n=(a+b)/i;
//        int tail=(a+b)%i;
//        for (int anums=0;anums<=i;anums++){
//            int bnums=i-anums;
//            int a1=n*anums;
//            int b1=n*bnums;
//            if (a>=a1&&b>=b1&&a-a1<=anums&&b-b1<=bnums){
//                return true;
//            }
//        }
//        return false;
//    }

/**
 * 一共有序号1~n的n道题目  小明从序号1的题目开始做  按序号递增的顺序开始解答  每道题如果解答需要消耗时间a[i](如果剩余的时间不足a[i]则无法解答 如果跳过则不消耗时间
 * 小明想知道对于每一道题最少需要放弃前面几道题才能完成解答
 *
 * 输入 第一行是样例个数
 * 每一个样例的第一行是n,m 表示n道题目和练习时长m秒（1<=n<=1000;1<=m<=1000000)
 *     第二行是n个数字a[i] 分别表示n道题的解答耗时为a[i]秒 题目保证a[i]<=m
 *
 *     输出 每个样例输出一行 一行表示n个数字  分别个题目如果完成需要放弃前面的最少题目数
 *
 *     实例
 *     输入
 *     2
 *     5 5
 *     1 2 3 4 5
 *     4 4
 *     4 3 2 1
 *
 *     输出
 *     0 0 1 2 4
 *     0 1 2 2
 */
public class Main {
//    public static void main(String[] args) {
////        boolean[][] a=new  boolean[2][2];
////        System.out.println(a[1][1]);
////        List l=new LinkedList();
////        int[] test=new int[10];
////        int t=1;
////        int b=3;
//        List list=new ArrayList<>();
//        Test003 test003=new Test003();
//        test003.change(list);
//        System.out.println(list.get(0)+" "+list.size());
//        test003.change(list);
//        System.out.println(list.get(0)+" "+list.size());
//        test003.setNull(list);
//        System.out.println(list==null);
//        test003.changeAll(list);
//        System.out.println(list.size());
//        list=null;
//        System.out.println(list);
//        String s="123";
//        test003.changeString(s);
//        System.out.println(s);
//
//
//    }
    static void add(int[] a){
        a[2]=3;
    }
    static void add(int a,int b){
        a=a+b;
    }
    public static void main(String[] args) {
        Number_1130 a=new Number_1130();
        a.mctFromLeafValues(new int[]{6,2,4});

    }

    public static void change(String a) {
        char data[] = {'D', 'E', 'F'};
        String str = new String(data);
        a = str;
    }
}

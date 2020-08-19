package leetcode.WrittenExamination;

import java.util.Arrays;
import java.util.Scanner;

public class ByteDanceTest2 {
    //广告插入
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int len=sc.nextInt();
        int m=sc.nextInt();
        int[] items=new int[len];
        for (int i=0;i<len;i++){
            items[i]=sc.nextInt();
        }
        solve1(items,m);
    }
    //求 sum(L_i/K)>=M-N k最大值 i（0：N）
    public static void solve1(int[] items,int m){
        int n=items.length;
        int target=m-n;
        Arrays.sort(items);
        if (target<=n){
            for (int i=n-1;i>=0;i--){
                if (calc(items[i],target,items)){
                    System.out.println(items[i]);
                    return;
                }
            }
        }else {
            int left=0,right=items[0];
            while (left<right){
                int mid=left+(right-left)/2;
                if (calc(mid,target,items)){
                    left=mid;
                }else {
                    right=mid-1;
                }
            }
            System.out.println(left);
            return;
        }
    }
    public static boolean calc(int k,int target,int[] items){
        int sum=0;
        for (int i:items){
            sum+=i/k;
        }
        if (sum>=target){
            return true;
        }else {
            return false;
        }
    }
}

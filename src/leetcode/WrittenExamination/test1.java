package leetcode.WrittenExamination;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int[] nums=new int[a];
        for (int i=0;i<a;i++){
            nums[i]=in.nextInt();
        }
        solve(nums);
    }

    private static void solve(int[] nums) {
        for (int i=0;i<nums.length;i++){
            subSolve(nums[i]);
        }
    }
    //Ѱ��num��2^32-1 ����� 1�ĸ���
    //��num��0�ĸ���
    private static void subSolve(int num) {
        long ans=2;
        if (num==0){
            System.out.println(ans);
            return;
        }
        //a=b�����
        if (num==Integer.MAX_VALUE){
            ans=Integer.MAX_VALUE;
            System.out.println(ans+1);
            return;
        }
        while (num!=0){
            if ((num&1)==1){
                ans*=2;
            }
            num>>=1;
        }
        System.out.println(ans);
    }
}

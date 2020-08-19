package leetcode.WrittenExamination;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ByteDanceTest1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int len=sc.nextInt();
        int m=sc.nextInt();
        int[] nums=new int[len];
        for (int i=0;i<len;i++){
            nums[i]=sc.nextInt();
        }
        solve1(nums,m);
    }

    private static int ans=0;
    /**
     *
     * 求k个数和 sum%m最大值
     * @param nums 长度为n的数组
     * @param m m
     */
    public static void solve1(int[] nums,int m){
        Set<Integer> set=new HashSet<Integer>();
        for (int i=1;i<=nums.length;i++){
            int cur=nums[i-1];
            int size=set.size();
            if (size>=1){
                Set<Integer> set2=new HashSet<Integer>();
                int p=0;
                for (Integer k:set){
                    int cur_sum= (int) (((long)(k+cur))%m);
                    if (!set2.contains(cur_sum)) set2.add(cur_sum);
                }
                for (Integer sum:set2){
                    if (!set.contains(sum)) set.add(sum);
                }
            }
            set.add(cur%m);
        }
        for (Integer sum:set){
            ans=Math.max(ans,sum);
        }
        System.out.println(ans);;
    }

}

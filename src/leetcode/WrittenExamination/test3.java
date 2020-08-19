package leetcode.WrittenExamination;

import java.util.ArrayList;
import java.util.List;

public class test3 {
    public static void main(String[] args) {
        int[] nums={1,2,4,5,7,7,8,8,8,9,9,6,4,4,3,3,2,2,1,1,-1,-2,-3};
        solve(nums);
    }
    //山形数组
    public static void solve(int[] nums){
        if (nums.length<0){
            return;
        }
        int left=0,right=nums.length-1;
        List<Integer> res=new ArrayList<>();
        while (left<=right){
            int left_int=nums[left];
            int right_int=nums[right];
            if (left_int<right_int){
                if (res.size()==0){
                    res.add(left_int);
                }else if (left_int!=res.get(res.size()-1)){
                    res.add(left_int);
                }
                left++;
                continue;
            }else if (left_int>right_int){
                if (res.size()==0){
                    res.add(right_int);
                }else if (right_int!=res.get(res.size()-1)){
                    res.add(right_int);
                }
                right--;
                continue;
            }else {
                if (res.size()==0){
                    res.add(right_int);
                }else if (right_int!=res.get(res.size()-1)){
                    res.add(right_int);
                }
                left++;
                right--;
                continue;
            }
        }
        if (res.size()>0){
            for (Integer i:res){
                System.out.print(i+" ");
            }
        }
    }
}

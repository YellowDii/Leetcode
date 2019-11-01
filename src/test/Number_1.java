package test;

/**
 * 1.两数之和
 * 方法：暴力法
 */
public class Number_1 {
    public int[] twoSum(int[] nums, int target) {
        int[] resulet = new int[2];
        int pos;
        for (int i = 0; i < nums.length; i++) {
            pos=find(nums,i,target-nums[i]);
            if (pos!=0){
                    resulet[0]=i;
                    resulet[1]=pos;
            }
        }
        return resulet;
    }

    public int find(int[] nums, int start, int target) {
        boolean flag=false;
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] == target) {
                flag = true;
                return i;
            }
        }
        return 0;
    }
}

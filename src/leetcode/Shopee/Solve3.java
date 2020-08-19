package leetcode.Shopee;

public class Solve3 {
    //给定一个树的前序遍历 输出它的所有叶子结点
    public static void main(String[] args) {
        int[] i1=new int[]{9,8,7,10};
        int[] i2=new int[]{5,2,1,0,4,8,7,9};
        solve3(i1);
        solve3(i2);
    }
    public static void solve3(int[] nums){
        if (nums==null){
            return;
        }
        int len=nums.length;
        for (int i=0;i<len-1;i++){
            if (nums[i]<nums[i+1]){
                System.out.print(nums[i]+" ");
            }
        }
        System.out.println(nums[len-1]);
    }
}

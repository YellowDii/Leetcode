package leetcode;
/*
303.区域和检索-数组不可变

给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

示例：

给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
说明:

你可以假设数组不可变。
会多次调用 sumRange 方法。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/range-sum-query-immutable
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


/**
 *  Your NumArray object will be instantiated and called as such:
 *  NumArray obj = new NumArray(nums);
 *  int param_1 = obj.sumRange(i,j);
 */
public class Number_303 {
    private  int[] data;
   // private Map<Pair<Integer,Integer>,Integer> map=new HashMap<>();
    private int[] sum;
    public Number_303(int[] nums) {
        //方法1：
        // data=nums;

        //方法2：
//        for (int i=0;i<nums.length;i++){
//            int sum=0;
//            for (int j=i;j<nums.length;j++){
//                sum+=nums[j];
//                map.put(Pair.create(i,j),sum);
//            }
//        }

        //方法3
        sum=new int[nums.length];
        for (int i=0;i<nums.length;i++){
            if (i>0)
                sum[i]=sum[i-1]+nums[i];
            else
                sum[0]=nums[0];
        }
    }
    //暴力法
    public int sumRange1(int i, int j) {
        int res=0;
        for (int p=i;p<=j;p++){
            res+=data[p];
        }
        return res;
    }
    //利用哈希表做缓存
//    public int sumRange2(int i,int j){
//        return map.get(Pair.create(i,j));
//    }
    //利用一个数组做缓存
    public int sumRange3(int i,int j){
        if (i==0)
            return sum[j];
        else
            return sum[j]-sum[i-1];
    }
}

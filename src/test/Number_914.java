package test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 914.卡牌分组
 * 给定一副牌，每张牌上都写着一个整数。
 *
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 *
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 * 示例 1：
 *
 * 输入：[1,2,3,4,4,3,2,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 * 示例 2：
 *
 * 输入：[1,1,1,2,2,2,3,3]
 * 输出：false
 * 解释：没有满足要求的分组。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_914 {
    //这种方法在idea能够编译，在LeetCode上不行，可能是jdk版本不同
    public boolean hasGroupsSizeX(int[] deck) {
        if(deck.length<=1)
            return false;
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<deck.length;i++){
            if (map.get(deck[i])==null){
                map.put(deck[i],1);
            }else map.put(deck[i],map.get(deck[i])+1);
        }
        //下面这句在LeetCode会报错
        Collection<Integer> values=(Collection<Integer>)map.values();
        Integer[] integers=new Integer[map.size()];
        integers=values.toArray(integers);
        int nums=integers[0];
        //这边第一次考虑欠妥，只考虑倍数
        for (int i=1;i<integers.length;i++){
            if (integers[i]%nums==0||nums%integers[i]==0)
                return false;
        }
        return true;
    }
    //上面的稍微修改一下
    //特殊情况[1,1,2,2,2,2],这时候个数不相等，呈倍数
    //特殊情况[1,1,1,1，2,2,2,2,2,2]这时候个数不呈倍数，具有公约数
    //需要单独写一个判断最大公约数方法
    public boolean hasGroupsSizeX2(int[] deck) {
        if(deck.length<=1)
            return false;
        Map<Integer,Integer> map=new HashMap<>();
        //记录每个数的个数
        for (int i=0;i<deck.length;i++){
            if (map.get(deck[i])==null){
                map.put(deck[i],1);
            }else map.put(deck[i],map.get(deck[i])+1);
        }
        int nums=maxDivisor(map.get(deck[0]),map.get(deck[1]));
        for (int i=1;i<deck.length;i++){
            int curD=maxDivisor(nums,map.get(deck[i]));
            if(curD==1) return false;
            nums=nums<curD?nums:curD;
        }
        return true;
    }
    //这边写一个稍微快点的方法
    public boolean hasGroupsSizeX3(int[] deck) {
        if(deck.length<=1)
            return false;
        // 记录长度
        int[] nums=new int[10000];
        for (int i:deck){
            nums[i]++;
        }
        int maxD=nums[deck[0]];
        for (int i:nums){
            if (i!=0){
                maxD=maxDivisor(maxD,i);
            }
        }
        if (maxD<2)
        return false;

        return true;
    }
    public int maxDivisor(int a,int b){
        int i=b;
        while (b!=0){
            int r=a%b;
            a=b;
            b=r;
        }
        return a;
    }
}

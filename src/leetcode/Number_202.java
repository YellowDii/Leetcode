package leetcode;

import java.util.HashSet;
import java.util.Set;

public class Number_202 {
    /**
     * 202.快乐数(happy-number)
     *
     * 编写一个算法来判断一个数 n 是不是快乐数。
     *
     * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
     *
     * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
     * 
     *
     * 示例：
     *
     * 输入：19
     * 输出：true
     * 解释：
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/happy-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //题目虽说是无限循环 但实际上这个计算过程是有限的
    //拿9 为例子 做多得到162=9*9+9*9
    //拿99 为例子 最大得到243=9*9+9*9+9*9 实际上比这要低一些
    //用HashSet 避免重复计算 即环出现 一直循环即可
    public boolean isHappy(int n) {
        int cur=n;
        Set<Integer> set=new HashSet<>();
        while (cur!=1&&!set.contains(cur)){
            set.add(cur);
            cur=getNext(cur);
        }
        return cur==1;
    }

    private int getNext(int cur) {
        int totalSum=0;
        while (cur!=0){
            int i=cur%10;
            cur/=10;
            totalSum+=i*i;
        }
        return totalSum;
    }
    //这个也可以用龟兔赛跑 快慢指针做 找环
    public boolean isHappy2(int n) {
        int slow = n, fast = getNext(n);
        while (slow != fast){
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        };
        return slow == 1;
    }

}

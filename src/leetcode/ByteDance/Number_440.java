package leetcode.ByteDance;

public class Number_440 {
    /**
     * 440.字典序的第K小数字
     *
     * 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
     *
     * 注意：1 ≤ k ≤ n ≤ 109。
     *
     * 示例 :
     *
     * 输入:
     * n: 13   k: 2
     *
     * 输出:
     * 10
     *
     * 解释:
     * 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //一开始想到桶排序 然后发现这样只能过滤一次
    //后来看解题才想到十叉树
    public int findKthNumber(int n, int k) {
        int cur = 1;
        k = k - 1;//扣除掉第一个0节点
        while(k>0){
            int num = getNode(n,cur,cur+1);
            if(num<=k){//第k个数不在以cur为根节点的树上
                cur+=1;//cur在字典序数组中从左往右移动
                k-=num;
            }else{//在子树中
                cur*=10;//cur在字典序数组中从上往下移动
                k-=1;//刨除根节点
            }
        }
        return cur;
    }

    //得出该节点下的个数
    public int getNode(int n, long first, long last){
        int num = 0;
        while(first <= n){
            num += Math.min(n+1,last) - first;//比如n是195的情况195到100有96个数
            first *= 10;
            last *= 10;
        }
        return num;
    }

}

package test;

/**
 * 771.宝石与石头
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 *
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 *
 * 示例 1:
 *
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 * 示例 2:
 *
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 * 注意:
 *
 * S 和 J 最多含有50个字母。
 *  J 中的字符不重复。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jewels-and-stones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_771 {
    //因为都是字母，可以单独抽出int[52]记录
    //这样效率会较高
    public int numJewelsInStones(String J, String S) {
        int[] diamond=new int[52];
        int[] stone=new int[52];
        for (char c:J.toCharArray()){
            if(c-'A'>=0&&c-'Z'<=0)
                diamond[c-'A']=1;
            else if (c-'a'>=0&&c-'z'<=0)
                diamond[c-'a'+26]=1;
        }
        for (char c:S.toCharArray()){
            if(c-'A'>=0&&c-'Z'<=0)
                stone[c-'A']++;
            else if (c-'a'>=0&&c-'z'<=0)
                stone[c-'a'+26]++;
        }
        int result=0;
        for (int i=0;i<52;i++){
            if (diamond[i]==1)
                result+=stone[i];
        }
        return result;
    }
}
package leetcode;

import java.util.*;

public class Mian17_26 {
    /**
     * 17.26 稀疏模糊度
     *
     * 两个(具有不同单词的)文档的交集(intersection)中元素的个数除以并集(union)中元素的个数，就是这两个文档的相似度。例如，{1, 5, 3} 和 {1, 7, 2, 3} 的相似度是 0.4，其中，交集的元素有 2 个，并集的元素有 5 个。给定一系列的长篇文档，每个文档元素各不相同，并与一个 ID 相关联。它们的相似度非常“稀疏”，也就是说任选 2 个文档，相似度都很接近 0。请设计一个算法返回每对文档的 ID 及其相似度。只需输出相似度大于 0 的组合。请忽略空文档。为简单起见，可以假定每个文档由一个含有不同整数的数组表示。
     *
     * 输入为一个二维数组 docs，docs[i] 表示 id 为 i 的文档。返回一个数组，其中每个元素是一个字符串，代表每对相似度大于 0 的文档，其格式为 {id1},{id2}: {similarity}，其中 id1 为两个文档中较小的 id，similarity 为相似度，精确到小数点后 4 位。以任意顺序返回数组均可。
     *
     * 示例:
     *
     * 输入:
     * [
     *   [14, 15, 100, 9, 3],
     *   [32, 1, 9, 3, 5],
     *   [15, 29, 2, 6, 8, 7],
     *   [7, 10]
     * ]
     * 输出:
     * [
     *   "0,1: 0.2500",
     *   "0,2: 0.1000",
     *   "2,3: 0.1429"
     * ]
     * 提示：
     *
     * docs.length <= 500
     * docs[i].length <= 500
     * 相似度大于 0 的文档对数不会超过 1000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sparse-similarity-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    //思维转变一下，不是把两个数组比较，看有没有重复的。而是每次的数字出现，去寻找在哪个数组里出现的。

    public List<String> computeSimilarities(int[][] docs) {
        List<String> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        //ans[i][j]表示 i文档与j文档之间交集数
        int[][] ans = new int[docs.length][docs.length];
        for (int i = 0; i < docs.length; i++) {
            for (int j = 0; j < docs[i].length; j++) {
                List<Integer> list = map.get(docs[i][j]);
                if (list == null) {
                    list = new LinkedList<>();
                    map.put(docs[i][j], list);
                } else {
                    for (Integer k : list) {
                        ans[i][k]++;
                    }
                }
                list.add(i);
            }

            for (int k = 0; k < docs.length; k++) {
                if (ans[i][k] > 0) {
                    res.add(k + "," + i + ": " + String.format("%.4f", (double) ans[i][k] / (docs[i].length + docs[k].length - ans[i][k])));
                }
            }
        }
        return res;
    }
    /*---------------------------优化后的----------------------------*/
    //用自定义类Node 代替List<Integer>
    static char[] temp = new char[32];

    static int length(int x) {
        return x < 100 ? (x < 10 ? 1 : 2) : (x < 1000 ? 3 : 4);
    }

    static int writeInt(char[] cs, int ix, int val) {
        return writeInt(cs, ix, val, length(val));
    }

    static int writeInt(char[] cs, int ix, int val, int len) {
        ix += len;
        for (int i = 1; i <= len; i++, val /= 10)
            cs[ix - i] = (char) ('0' + val % 10);
        return ix;
    }

    static String result(int i, int j, int sames, int total) {
        char[] cs = temp;
        int ix = 0;
        ix = writeInt(cs, ix, i);
        cs[ix++] = ',';
        ix = writeInt(cs, ix, j);
        cs[ix++] = ':';
        cs[ix++] = ' ';
        if (sames == total) {
            cs[ix++] = '1';
            sames = 0;
        } else {
            cs[ix++] = '0';
            sames = (sames * 10000 + (total >>> 1)) / total;
        }
        cs[ix++] = '.';
        ix = writeInt(cs, ix, sames, 4);
        return new String(cs, 0, ix);
    }

    static class Node
    {
        int index;
        Node next;
        Node(int index, Node next) {
            this.index = index;
            this.next = next;
        }
    }
    public static List<String> computeSimilarities2(int[][] docs) {
        final int N = docs.length;
        List<String> res = new ArrayList<>();
        int cap = 0;
        for(int[] doc : docs) cap += doc.length;
        Map<Integer, Node> map = new HashMap<Integer, Node>(cap*2);
        for(int i=0; i<N;i++) {
            for(int v : docs[i]) {
                map.put(v, new Node(i, map.get(v)));
            }
        }
        int[] sames = new int[N];
        int[] idxs = new int[N];
        for(int i=0; i<N-1;i++) {
            int size = 0;
            for(int v : docs[i]) {
                for(Node node = map.get(v); node!=null; node = node.next) {
                    int ix;
                    if((ix = node.index) > i) {
                        if(sames[ix] ++ == 0) {
                            idxs[size++] = ix;
                        }
                    }
                }
            }
            for(int j=0; j<size; j++) {
                int ix = idxs[j];
                int s = sames[ix];
                sames[ix] = 0;
                res.add(result(i,ix,s, docs[i].length+docs[ix].length-s));
            }
        }
        return res;
    }
}

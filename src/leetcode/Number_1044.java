package leetcode;

import java.util.*;

public class Number_1044 {
    /**
     * 1044. 最长重复子串(longest-duplicate-substring)
     *
     * 给出一个字符串?S，考虑其所有重复子串（S 的连续子串，出现两次或多次，可能会有重叠）。
     *
     * 返回任何具有最长可能长度的重复子串。（如果 S?不含重复子串，那么答案为?""。）
     *
     * ?
     *
     * 示例 1：
     *
     * 输入："banana"
     * 输出："ana"
     * 示例 2：
     *
     * 输入："abcd"
     * 输出：""
     * ?
     *
     * 提示：
     *
     * 2 <= S.length <= 10^5
     * S 由小写英文字母组成。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-duplicate-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /*
    Rabin-Karp with polynomial rolling hash.
        Search a substring of given length
        that occurs at least 2 times.
        Return start position if the substring exits and -1 otherwise.
        */
    public int search(int L, int a, long modulus, int n, int[] nums) {
        // compute the hash of string S[:L]
        long h = 0;
        for (int i = 0; i < L; ++i) h = (h * a + nums[i]) % modulus;

        // already seen hashes of strings of length L
        HashSet<Long> seen = new HashSet();
        seen.add(h);
        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;

        for (int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
            h = (h + nums[start + L - 1]) % modulus;
            if (seen.contains(h)) return start;
            seen.add(h);
        }
        return -1;
    }

    public String longestDupSubstring(String S) {
        int n = S.length();
        // convert string to array of integers
        // to implement constant time slice
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) nums[i] = (int)S.charAt(i) - (int)'a';
        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long)Math.pow(2, 32);

        // binary search, L = repeating string length
        int left = 1, right = n;
        int L;
        while (left != right) {
            L = left + (right - left) / 2;
            if (search(L, a, modulus, n, nums) != -1) left = L + 1;
            else right = L;
        }

        int start = search(left - 1, a, modulus, n, nums);
        return start != -1 ? S.substring(start, start + left - 1) : "";
    }
    // 另一种写法
    private static final long P = 805306457;
    private static final long MOD = (int) (1e9+7);
    private long[] hash;
    private long[] power;
    private int[] ansPos;

    private void calcHashAndPower(char[] arr) {
        hash[0] = arr[0];
        power[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            hash[i] = (hash[i-1] * P + arr[i]) % MOD;
            power[i] = (power[i-1] * P) % MOD;
        }
    }

    private long getSubStrHash(int l, int r) {
        if (l == 0) {
            return hash[r];
        }
        return ((hash[r] - power[r-l+1] * hash[l-1]) % MOD + MOD) % MOD;
    }

    private boolean hasTrueExist(char[] arr, int l1, int l2, int subLen) {
        for (int i = 0; i < subLen; i++) {
            if (arr[l1 + i] != arr[l2 + i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断是否存在指定长度的重复子串
     * 由于不同子串可能存在相同的hash值，因此需要解决hash冲突
     */
    private boolean hasDuplicatedStr(char[] arr, int subLen) {
        Map<Long, List<Integer>> map = new HashMap<>();
        for (int l = 0; l <= arr.length - subLen; l++) {
            long strHash = getSubStrHash(l, l + subLen - 1);
            if (map.containsKey(strHash)) {
                List<Integer> oldPosList = map.get(strHash);
                for (Integer oldPos : oldPosList) {
                    if (hasTrueExist(arr, oldPos, l, subLen)) {
                        ansPos[0] = l;
                        ansPos[1] = l + subLen;
                        return true;
                    }
                }

                oldPosList.add(l);
            } else {
                List<Integer> posList = new ArrayList<>();
                posList.add(l);
                map.put(strHash, posList);
            }
        }

        return false;
    }

    public String longestDupSubstring2(String str) {
        char[] arr = str.toCharArray();
        int len = arr.length;
        hash = new long[len];
        power = new long[len];
        ansPos = new int[2];

        calcHashAndPower(arr);

        // 二分+滑动窗口
        // 其中二分确定长度，因为当一个长串出现重复串，那么它的子串必定出现重复串。因此可以用二分去猜最大长度
        int low = 1;
        int high = len;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            boolean hasDuplicated = hasDuplicatedStr(arr, mid);
            if (hasDuplicated) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (ansPos[0] == ansPos[1]) {
            return "";
        }

        return str.substring(ansPos[0], ansPos[1]);
    }

}

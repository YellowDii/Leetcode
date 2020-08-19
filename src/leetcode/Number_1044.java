package leetcode;

import java.util.*;

public class Number_1044 {
    /**
     * 1044. ��ظ��Ӵ�(longest-duplicate-substring)
     *
     * ����һ���ַ���?S�������������ظ��Ӵ���S �������Ӵ����������λ��Σ����ܻ����ص�����
     *
     * �����κξ�������ܳ��ȵ��ظ��Ӵ�������� S?�����ظ��Ӵ�����ô��Ϊ?""����
     *
     * ?
     *
     * ʾ�� 1��
     *
     * ���룺"banana"
     * �����"ana"
     * ʾ�� 2��
     *
     * ���룺"abcd"
     * �����""
     * ?
     *
     * ��ʾ��
     *
     * 2 <= S.length <= 10^5
     * S ��СдӢ����ĸ��ɡ�
     *
     * ��Դ�����ۣ�LeetCode��
     * ���ӣ�https://leetcode-cn.com/problems/longest-duplicate-substring
     * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
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
    // ��һ��д��
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
     * �ж��Ƿ����ָ�����ȵ��ظ��Ӵ�
     * ���ڲ�ͬ�Ӵ����ܴ�����ͬ��hashֵ�������Ҫ���hash��ͻ
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

        // ����+��������
        // ���ж���ȷ�����ȣ���Ϊ��һ�����������ظ�������ô�����Ӵ��ض������ظ�������˿����ö���ȥ����󳤶�
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

package leetcode;

public class Number_67 {
    /**
     * 67.二进制求和（add-binary）
     *
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     *
     * 输入为 非空 字符串且只包含数字 1 和 0。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: a = "11", b = "1"
     * 输出: "100"
     * 示例 2:
     *
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     *  
     *
     * 提示：
     *
     * 每个字符串仅由字符 '0' 或 '1' 组成。
     * 1 <= a.length, b.length <= 10^4
     * 字符串如果不是 "0" ，就都不含前导零。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-binary
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String addBinary(String a, String b) {
        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = c1.length - 1;
        int j = c2.length - 1;
        int k = 0;//进位
        while (i >= 0 && j >= 0) {
            if (c1[i] == '0' && c2[j] == '0') {
                if (k == 0) {
                    sb.insert(0, '0');
                } else {
                    sb.insert(0, '1');
                    k = 0;
                }
            } else if (c1[i] == '1' && c2[j] == '1') {
                if (k == 0) {
                    sb.insert(0, '0');
                    k = 1;
                } else {
                    sb.insert(0, '1');
                }
            } else {
                if (k == 0) {
                    sb.insert(0, '1');
                } else {
                    sb.insert(0, '0');
                    k = 1;
                }
            }
            i--;
            j--;
        }
        if (i >= 0) {
            while (i >= 0) {
                if (c1[i] == '0') {
                    if (k == 1) {
                        sb.insert(0, '1');
                        k = 0;
                    } else {
                        sb.insert(0, '0');
                    }
                } else {
                    if (k == 1) {
                        sb.insert(0, '0');
                    } else {
                        sb.insert(0, '1');
                    }
                }
                i--;
            }
        }
        if (j >= 0) {
            while (j >= 0) {
                if (c2[j] == '0') {
                    if (k == 1) {
                        sb.insert(0, '1');
                        k = 0;
                    } else {
                        sb.insert(0, '0');
                    }
                } else {
                    if (k == 1) {
                        sb.insert(0, '0');
                    } else {
                        sb.insert(0, '1');
                    }
                }
                j--;
            }
        }
        if (k == 1) {
            sb.insert(0, '1');
        }
        return sb.toString();
    }

    //上面这个用的insert 可以变成apeend 然后reverse
    public String addBinary4(String a, String b) {
        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = c1.length - 1;
        int j = c2.length - 1;
        int k = 0;//进位
        while (i >= 0 && j >= 0) {
            if (c1[i] == '0' && c2[j] == '0') {
                if (k == 0) {
                    sb.append('0');
                } else {
                    sb.append('1');
                    k = 0;
                }
            } else if (c1[i] == '1' && c2[j] == '1') {
                if (k == 0) {
                    sb.append('0');
                    k = 1;
                } else {
                    sb.append('1');
                }
            } else {
                if (k == 0) {
                    sb.append('1');
                } else {
                    sb.append('0');
                    k = 1;
                }
            }
            i--;
            j--;
        }
        if (i >= 0) {
            while (i >= 0) {
                if (c1[i] == '0') {
                    if (k == 1) {
                        sb.append('1');
                        k = 0;
                    } else {
                        sb.append('0');
                    }
                } else {
                    if (k == 1) {
                        sb.append('0');
                    } else {
                        sb.append('1');
                    }
                }
                i--;
            }
        }
        if (j >= 0) {
            while (j >= 0) {
                if (c2[j] == '0') {
                    if (k == 1) {
                        sb.append('1');
                        k = 0;
                    } else {
                        sb.append('0');
                    }
                } else {
                    if (k == 1) {
                        sb.append('0');
                    } else {
                        sb.append('1');
                    }
                }
                j--;
            }
        }
        if (k == 1) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }

    //更方便

    /**
     * 如果字符串超过 3333 位，不能转化为 Integer
     * 如果字符串超过 6565 位，不能转化为 Long
     * 如果字符串超过 500000001500000001 位，不能转化为 BigInteger
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/add-binary/solution/er-jin-zhi-qiu-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     */
    public String addBinary2(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }

    //更美观
    public String addBinary3(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }

}

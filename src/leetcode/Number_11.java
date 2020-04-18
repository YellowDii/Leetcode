package leetcode;

public class Number_11 {
    /**
     * 题目：
     * 11.盛最多水的容器（container-with-most-water）
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * <p>
     * 图参考链接...
     * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 通过次数188,186提交次数299,055
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //遍历 不过稍微有点技巧
    //比粗暴的直接遍历好点  要300ms  直接粗暴遍历会超时
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int max = 0;
        int len = height.length;
        int h1 = height[0];//表示当前遍历容器的左边高度
        for (int i = 0; i < len; i++) {
            int j = len - 1;
            if (height[i] < h1 && i > 0) {
                continue;
            }
            int h2 = height[j];//表示当前遍历的容器的右边高度
            max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            while (j > i) {
                if (height[j] <= h2) {
                    j--;
                    continue;
                }
                int k = (j - i) * Math.min(height[i], height[j]);
                if (k > max) {
                    max = k;
                    h2 = height[j];
                }
                j--;
            }
        }
        return max;
    }

    //其实要找的容器要满足尽量远和尽量高 可以从两边向中心找
    //这个只要4ms左右 远远小于前者
    public int maxArea2(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int max = 0;
        int len = height.length;
        int left = 0, right = len - 1;
        while (right > left) {
            max = Math.max(max, (right - left) * Math.min(height[right], height[left]));
            if (height[right] >= height[left]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    //上面还可以小小的优化
    public int maxArea3(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r) {
            int min = height[l] < height[r] ? height[l] : height[r];
            max = Math.max(max, min * (r - l));
            while (l < r && height[l] <= min) {
                l++;
            }
            while (l < r && height[r] <= min) {
                r--;
            }

        }
        return max;
    }
}

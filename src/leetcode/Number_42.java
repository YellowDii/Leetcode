package leetcode;

public class Number_42 {
    /**
     * 42.接雨水（trapping-rain-water）
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，
     * 在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
     *
     * 示例:
     *
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //最直观的 每次找左边最高的和右边最高的 然后当前格子能存储 min(max_left,max_right)-hight[i]
    public int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        //最左边和最右边存不了水 所以是1~size-1
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) {
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) {
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }

    //上面的可以优化
    public int trap2(int[] height) {
        int ans = 0;
        if (height == null) {
            return 0;
        }
        int size = height.length;
        if (size < 1) {
            return 0;
        }
        int[] max_left = new int[size];
        int[] max_right = new int[size];
        max_left[0] = height[0];
        for (int i = 1; i < size; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i]);
        }
        max_right[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(max_left[i], max_right[i]) - height[i];
        }
        return ans;
    }

    //用双指针 空间复杂度变为O(1)
    public int trap3(int[] height) {
        int res = 0;
        int max_left = 0, max_right = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= max_left) {
                    max_left = height[left];
                } else {
                    res += max_left - height[left];
                }
                left++;
            } else {
                if (height[right] >= max_right) {
                    max_right = height[right];
                } else {
                    res += max_right - height[right];
                }
                right--;
            }
        }
        return res;
    }

}

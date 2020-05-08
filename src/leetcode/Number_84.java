package leetcode;

import java.util.Stack;

public class Number_84 {
    /**
     * 84.柱状图中最大的矩形（largest-rectangle-in-histogram）
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     *
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     *        _
     *      _| |
     *     | | |
     *     | | |  _
     *  _  | | |_| |
     * | |_| | | | |
     * |_|_|_|_|_|_|
     *
     * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
     *
     *
     * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //从两边向中间找 不太行 因为中间有比两边低的 会有空缺
    public int largestRectangleArea(int[] heights) {
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minheight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minheight = Math.min(minheight, heights[j]);
                maxarea = Math.max(maxarea, minheight * (j - i + 1));
            }
        }
        return maxarea;
    }
    //分治 先找中间最小柱子i 比较 height[i]*len、左边最大面积、右边最大面积
    public int largestRectangleArea2(int[] heights){
        int left=0,right=heights.length-1;
        return calculateArea(heights,left,right);
    }

    private int calculateArea(int[] heights, int left, int right) {
        if (left>right){
            return 0;
        }
        int minHeightIndex=left;
        for (int i=left;i<=right;i++){
            if (heights[i]<heights[minHeightIndex]){
                minHeightIndex=i;
            }
        }
        return Math.max(heights[minHeightIndex]*(right-left+1),
                Math.max(calculateArea(heights,minHeightIndex+1,right),calculateArea(heights,left,minHeightIndex-1)));
    }

    //栈
    public int largestRectangleArea3(int[] heights){
        Stack<Integer> stack=new Stack<>();//存储比当前高度小的下标
        int maxArea=0;
        stack.push(-1);
        int minHeight=0;
        for (int i=0;i<heights.length;i++){
            while (stack.peek()!=-1&&heights[stack.peek()]>=heights[i]){
                maxArea=Math.max(maxArea,heights[stack.pop()]*(i-stack.peek()-1));
            }
            stack.push(i);
        }
        while (stack.peek()!=-1){
            maxArea=Math.max(maxArea,heights[stack.pop()]*(heights.length-stack.peek()-1));
        }
        return maxArea;
    }
}

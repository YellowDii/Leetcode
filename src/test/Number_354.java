package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 354. 俄罗斯套娃信封问题
 * 该题是300 最长上升子序列的扩展题目
 *
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 说明:
 * 不允许旋转信封。
 *
 * 示例:
 *
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_354 {
    //该方法思路有瑕疵，没有考虑全面，不采用
    public int maxEnvelopes(int[][] envelopes) {
        EnvelopSpace envelopSpace = new EnvelopSpace();
        for (int[] i : envelopes) {
            envelopSpace.add(i);
        }
        return envelopSpace.size;
    }

    class EnvelopSpace {
        int size;
        List<int[]> list;
        int[] max = {0x80000000, 0x80000000};
        int[] min = {0x7fffffff, 0x7fffffff};

        EnvelopSpace() {
            size = 0;
            list = new ArrayList<>();
        }

        void add(int[] envelop) {
            if (size == 0) {
                list.add(envelop);
                max = envelop;
                min = envelop;
                size++;
                return;
            } else if (envelop[0] > max[0] && envelop[1] > max[1]) {
                max = envelop;
                list.add(max);
                size++;
                return;
            } else if (envelop[0] < min[0] && envelop[1] < min[1]) {
                min = envelop;
                list.add(0, min);
                size++;
                return;
            } else {
                for (int i = size - 1; i >= 0; i--) {
                    int[] cur = list.get(i);
                    if (envelop[0] > cur[0] && envelop[1] > cur[1]) {
                        list.add(i + 1, envelop);
                        size++;
                    }
                    else continue;
                }
                return;
            }
        }

    }
    //官方参考答案，先对宽度进行升序排列，宽度相同时进行降序排列
    //最终答案相当于对宽度进行最长上升子序列的计算，然后可以参考第300题的思路
    public int maxEnvelopes2(int[][] envelopes){
        int len = envelopes.length;
        if (len < 2) {
            return len;
        }

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] envelope1, int[] envelope2) {
                if (envelope1[0] != envelope2[0]) {
                    return envelope1[0] - envelope2[0];
                }
                return envelope2[1] - envelope1[1];
            }
        });


        int[] tail = new int[len];
        tail[0] = envelopes[0][1];

        // end 表示有序数组 tail 的最后一个已经赋值元素的索引
        int end = 0;

        for (int i = 1; i < len; i++) {
            int target = envelopes[i][1];

            if (target > tail[end]) {
                end++;
                tail[end] = target;
            } else {
                int left = 0;
                int right = end;

                while (left < right) {
                    int mid = (left + right) >>> 1;
                    if (tail[mid] < target) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                tail[left] = target;
            }
        }
        return end + 1;

    }
}

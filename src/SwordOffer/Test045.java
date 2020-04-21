package SwordOffer;

import java.util.LinkedList;
import java.util.List;

public class Test045 {
    /**
     * 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
     * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,
     * 并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,并得到最终礼品
     * 请你试着想下,哪个小朋友会得到这份最终礼品呢？(注：小朋友的编号是从0到n-1)
     * <p>
     * 如果没有小朋友，请返回-1
     */
    public static int lastRemaining(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        // 要删除元素的位置 也是删除后开始报数的位置
        int idx = 0;

        while (list.size() > 1) {
            // 只要移动m-1次就可以移动到下一个要删除的元素上
            for (int i = 1; i < m; i++) {
                idx = (idx + 1) % list.size(); //
            }

            list.remove(idx);
        }

        return list.get(0);
    }

    public static int lastRemaining2(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }

        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }

        return last;
    }
}

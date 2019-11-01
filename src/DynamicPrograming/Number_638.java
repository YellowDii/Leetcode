package DynamicPrograming;

import sun.nio.cs.ext.MacArabic;

import java.util.ArrayList;
import java.util.List;

/*
638.大礼包 （ shopping-offers ）
在LeetCode商店中， 有许多在售的物品。

然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。

现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出确切完成待购清单的最低花费。

每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，其他数字分别表示内含的其他种类物品的数量。

任意大礼包可无限次购买。

示例 1:

输入: [2,5], [[3,0,5],[1,2,10]], [3,2]
输出: 14
解释:
有A和B两种物品，价格分别为¥2和¥5。
大礼包1，你可以以¥5的价格购买3A和0B。
大礼包2， 你可以以¥10的价格购买1A和2B。
你需要购买3个A和2个B， 所以你付了¥10购买了1A和2B（大礼包2），以及¥4购买2A。
示例 2:

输入: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
输出: 11
解释:
A，B，C的价格分别为¥2，¥3，¥4.
你可以用¥4购买1A和1B，也可以用¥9购买2A，2B和1C。
你需要买1A，2B和1C，所以你付了¥4买了1A和1B（大礼包1），以及¥3购买1B， ¥4购买1C。
你不可以购买超出待购清单的物品，尽管购买大礼包2更加便宜。
说明:

最多6种物品， 100种大礼包。
每种物品，你最多只需要购买6个。
你不可以购买超出待购清单的物品，即使更便宜。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shopping-offers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_638 {
    int min = 0;
    int order = 0;
    List<List<Integer>> offers;
    List<Integer> price;

    //dfs+剪枝
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        //被过滤掉的礼包
        List<List<Integer>> offers = new ArrayList<>();
        //商品种类
        int kind = price.size();
        //过滤掉过多商品的礼包和不划算的礼包
        for (List<Integer> list : special) {
            int sum = 0, flag = 0;
            for (int i = 0; i < kind; i++) {
                if (list.get(i) > needs.get(i)) {
                    flag = 1;
                    break;
                }
                sum += list.get(i) * price.get(i);
            }
            if (sum < list.get(kind))
                flag = 1;
            if (flag == 0) {
                offers.add(list);
            }
        }
        this.price=price;
        this.offers=offers;
        for (int i=0;i<needs.size();i++){
            min+=price.get(i)*needs.get(i);
        }
        dfs(0,needs);
        return min;
    }

    //min代表目前花的最少钱，res表示当前方案已经花掉的钱，needs表示当前方案还要买的商品清单
    // order表示从当前大礼包开始遍历, offers表示所有礼包 price是零售价格
    void dfs(int res, List<Integer> needs) {
        if (needs.isEmpty()) {
            min = Math.min(res, min);
            return;
        }
        if (order == offers.size()) {
            for (int i = 0; i < needs.size(); i++) {
                res += needs.get(i) * price.get(i);
            }
            min = Math.min(res, min);
            return;
        }
        //判断是否能买该礼包
        boolean flag = true;
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i) < offers.get(order).get(i)) {
                flag = false;
                break;
            }
        }
        //在购物前存储当前花钱数及购物清单
        int oldRes = res;
        List<Integer> oldNeeds = new ArrayList<>();
        for (Integer i : needs) {
            oldNeeds.add(i);
        }
        //能买
        if (flag) {
            res += offers.get(order).get(needs.size());
            //剪枝
            if (res < min) {
                for (int i = 0; i < needs.size(); i++) {
                    needs.set(i, needs.get(i) - offers.get(order).get(i));
                }
                dfs(res,needs);
            }
        }
        order++;
        //不买的情况
        dfs(oldRes,oldNeeds);
        order--;
    }
}

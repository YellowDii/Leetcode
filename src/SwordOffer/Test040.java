package SwordOffer;

public class Test040 {
    /**
     * 题目：
     * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
     */
    //异或法 其实哈希法也可以
    public static int[] findNumbersAppearanceOnce(int[] data) {
        int[] result = {0, 0};

        if (data == null || data.length < 2) {
            return result;
        }

        int xor = 0;
        //第一遍异或得到的结果为 ans1~=ans2
        for (int i : data) {
            xor ^= i;
        }
        //标记数 找到ans1^ans2中 从右到左第一个1
        int indexOf1 = findFirstBit1(xor);
        //第二次遍历 根据indexOf1求ans1 ans2 其他的值都会被异或抵消
        //0^a=a a^a=0
        for (int i : data) {
            if (isBit1(i, indexOf1)) {
                result[0] ^= i;
            } else {
                result[1] ^= i;
            }
        }

        return result;
    }

    private static int findFirstBit1(int num) {
        int index = 0;
        while ((num & 1) == 0 && index < 32) {
            num >>>= 1;
            index++;
        }

        return index;
    }

    private static boolean isBit1(int num, int indexBit) {
        num >>>= indexBit;
        return (num & 1) == 1;
    }
}

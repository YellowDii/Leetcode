package leetcode;

public class Number_122 {
    /**
     * 122. ������Ʊ�����ʱ�� II
     * <p>
     * ����һ�����飬���ĵ�?i ��Ԫ����һ֧������Ʊ�� i ��ļ۸�
     * <p>
     * ���һ���㷨�����������ܻ�ȡ�������������Ծ����ܵ���ɸ���Ľ��ף��������һ֧��Ʊ����
     * <p>
     * ע�⣺�㲻��ͬʱ�����ʽ��ף���������ٴι���ǰ���۵�֮ǰ�Ĺ�Ʊ����
     * <p>
     * ?
     * <p>
     * ʾ�� 1:
     * <p>
     * ����: [7,1,5,3,6,4]
     * ���: 7
     * ����: �ڵ� 2 �죨��Ʊ�۸� = 1����ʱ�����룬�ڵ� 3 �죨��Ʊ�۸� = 5����ʱ������, ��ʽ������ܻ������ = 5-1 = 4 ��
     * ?    ����ڵ� 4 �죨��Ʊ�۸� = 3����ʱ�����룬�ڵ� 5 �죨��Ʊ�۸� = 6����ʱ������, ��ʽ������ܻ������ = 6-3 = 3 ��
     * ʾ�� 2:
     * <p>
     * ����: [1,2,3,4,5]
     * ���: 4
     * ����: �ڵ� 1 �죨��Ʊ�۸� = 1����ʱ�����룬�ڵ� 5 �� ����Ʊ�۸� = 5����ʱ������, ��ʽ������ܻ������ = 5-1 = 4 ��
     * ?    ע���㲻���ڵ� 1 ��͵� 2 ����������Ʊ��֮���ٽ�����������
     * ?    ��Ϊ��������ͬʱ�����˶�ʽ��ף���������ٴι���ǰ���۵�֮ǰ�Ĺ�Ʊ��
     * ʾ��?3:
     * <p>
     * ����: [7,6,4,3,1]
     * ���: 0
     * ����: �����������, û�н������, �����������Ϊ 0��
     * ?
     * <p>
     * ��ʾ��
     * <p>
     * 1 <= prices.length <= 3 * 10 ^ 4
     * 0 <= prices[i]?<= 10 ^ 4
     * <p>
     * ��Դ�����ۣ�LeetCode��
     * ���ӣ�https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
     * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        // 0�������ֽ�
        // 1�����й�Ʊ
        // ״̬ת�ƣ�0 �� 1 �� 0 �� 1 �� 0 �� 1 �� 0
        int[][] dp = new int[len][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            // �����е���˳��Ҳ�ǿ��Ե�
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }

    //ɽ�� �� �ȵ�
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int len = prices.length;
        int valley = prices[0];
        int peak = prices[0];
        int maxProfit=0;
        for (int i = 0; i < len; i++) {
            while (i<len-1&&prices[i]>=prices[i+1]){
                i++;
            }
            valley=prices[i];
            while (i<len-1&&prices[i]<=prices[i+1]){
                i++;
            }
            peak=prices[i];
            maxProfit+=peak-valley;
        }
        return maxProfit;
    }

    //�����Ż�
    public int maxProfit3(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

}

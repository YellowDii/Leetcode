package leetcode;

public class Number_1246 {
    /**
     * 1246. ɾ������������(palindrome-removal)
     * ����һ����������?arr��ÿһ�β����㶼����ѡ��ɾ������һ�� ���� ������?arr[i], arr[i+1], ..., arr[j]�� i <= j����
     *
     * ע�⣬ÿ����ɾ����һ�������飬�Ҳ�Ԫ�ض���������ǰ�ƶ����λ��
     *
     * ������㲢���ش�������ɾ������������������ٲ���������
     *
     * ?
     *
     * ʾ�� 1��
     *
     * ���룺arr = [1,2]
     * �����2
     * ʾ�� 2��
     *
     * ���룺arr = [1,3,4,1,5]
     * �����3
     * ���ͣ���ɾ�� [4]��Ȼ��ɾ�� [1,3,1]�������ɾ�� [5]��
     * ?
     *
     * ��ʾ��
     *
     * 1 <= arr.length <= 100
     * 1 <= arr[i] <= 20
     *
     * ��Դ�����ۣ�LeetCode��
     * ���ӣ�https://leetcode-cn.com/problems/palindrome-removal
     * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
     */

    /**
     * ���������dp��������f(i,j)f(i,j)����ɾ������[i,j][i,j]����Сֵ������Сɾ������������ô�ɵ����µ��ƹ�ʽ��
     * 1. ���arr[i]==arr[j] :
     *      f(i,j)=min(f(i+1,j-1),f(i,k)+f(k+1,j)) i<=k<=j
     * 2. ���arr[i]!=arr[j] :
     *      f(i,j)=min(f(i,k)+f(k+1,j))  i<=k<=j
     */
    public int minimumMoves(int[] arr) {
        if (arr==null||arr.length<1){
            return 0;
        }
        int len=arr.length;
        int[][] dp=new int[len][len];
        for (int i=0;i<len;i++){
            dp[i][i]=1;
        }
        //ע�����˳��
        for (int j=1;j<len;j++){
            for (int i=j-1;i>=0;i--){
                if (i==j-1){
                    dp[i][j]=arr[i]==arr[j]?1:2;
                    continue;
                }
                int mink=Integer.MAX_VALUE;
                for (int k=i;k<=j;k++){
                    mink=Math.min(mink,dp[i][k]+dp[k+1][j]);
                }
                if (arr[i]==arr[j]){
                    dp[i][j]=Math.min(dp[i+1][j-1],mink);
                }else {
                    dp[i][j]=mink;
                }
            }
        }
        return dp[0][len-1];
    }

    //˼·һ�� �õݹ�д  ���仯+����
    private int[] arr;
    private int[][] memo;

    private int backTrack(int i, int j) {
        if (i == j) {
            return 1;
        }

        if (i == j - 1) {
            // ��������
            return arr[i] == arr[j] ? 1 : 2;
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int min = Integer.MAX_VALUE;
        if (arr[i] == arr[j]) {
            min = backTrack(i + 1, j - 1);
        }

        for (int k = i; k < j; k++) {
            min = Math.min(min, backTrack(i, k) + backTrack(k + 1, j));
        }

        memo[i][j] = min;
        return min;
    }

    public int minimumMoves2(int[] arr) {
        this.arr = arr;
        int len = arr.length;
        memo = new int[len][len];
        return backTrack(0, len - 1);
    }


}

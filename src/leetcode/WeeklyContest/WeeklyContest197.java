package leetcode.WeeklyContest;

import java.util.*;

public class WeeklyContest197 {
    /**
     * 5460. 好数对的数目 显示英文描述
     *
     * 给你一个整数数组 nums 。
     *
     * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
     *
     * 返回好数对的数目。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3,1,1,3]
     * 输出：4
     * 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
     * 示例 2：
     *
     * 输入：nums = [1,1,1,1]
     * 输出：6
     * 解释：数组中的每组数字都是好数对
     * 示例 3：
     *
     * 输入：nums = [1,2,3]
     * 输出：0
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    public int numIdenticalPairs(int[] nums) {
        if (nums==null||nums.length<1){
            return 0;
        }
        Map<Integer, List<Integer>> map=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])){
                map.get(nums[i]).add(i);
            }else {
                List<Integer> array=new ArrayList<>();
                array.add(i);
                map.put(nums[i],array);
            }
        }
        int res=0;
        for (List<Integer> arr:map.values()){
            res+=(arr.size()*(arr.size()-1))/2;
        }
        return res;
    }

    /**
     * 5461. 仅含 1 的子串数
     * 给你一个二进制字符串 s（仅由 '0' 和 '1' 组成的字符串）。
     *
     * 返回所有字符都为 1 的子字符串的数目。
     *
     * 由于答案可能很大，请你将它对 10^9 + 7 取模后返回。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "0110111"
     * 输出：9
     * 解释：共有 9 个子字符串仅由 '1' 组成
     * "1" -> 5 次
     * "11" -> 3 次
     * "111" -> 1 次
     * 示例 2：
     *
     * 输入：s = "101"
     * 输出：2
     * 解释：子字符串 "1" 在 s 中共出现 2 次
     * 示例 3：
     *
     * 输入：s = "111111"
     * 输出：21
     * 解释：每个子字符串都仅由 '1' 组成
     * 示例 4：
     *
     * 输入：s = "000"
     * 输出：0
     *
     *
     * 提示：
     *
     * s[i] == '0' 或 s[i] == '1'
     * 1 <= s.length <= 10^5
     */
    static final int MOD_2=1000000007;
    public int numSub(String s) {
        if (s==null||s.length()<1){
            return 0;
        }
        int res=0;
        char[] chars=s.toCharArray();
        for (int i=0;i<chars.length;i++){
            long k=0;
            if (chars[i]=='0'){
                continue;
            }
            while (i<chars.length&&chars[i]=='1'){
                k++;
                i++;
            }
            res+=(k*(k+1)/2)%MOD_2;
            res%=MOD_2;
        }
        return res;
    }

    /**
     * 给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 edges[i] = [a, b] 表示连接节点 a 和 b 的一条无向边，且该边遍历成功的概率为 succProb[i] 。
     *
     * 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。
     *
     * 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
     * 输出：0.25000
     * 解释：从起点到终点有两条路径，其中一条的成功概率为 0.2 ，而另一条为 0.5 * 0.5 = 0.25
     * 示例 2：
     *
     *
     *
     * 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
     * 输出：0.30000
     * 示例 3：
     *
     *
     *
     * 输入：n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
     * 输出：0.00000
     * 解释：节点 0 和 节点 2 之间不存在路径
     *
     *
     * 提示：
     *
     * 2 <= n <= 10^4
     * 0 <= start, end < n
     * start != end
     * 0 <= a, b < n
     * a != b
     * 0 <= succProb.length == edges.length <= 2*10^4
     * 0 <= succProb[i] <= 1
     * 每两个节点之间最多有一条边
     */
    //dfs+剪枝
    static double prob=-1;
    public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Graphic graphic=new Graphic(n);
        for (int i=0;i<edges.length;i++){
            graphic.set(succProb[i],edges[i][0],edges[i][1]);
        }
        dfs(n,graphic,start,end,1d);
        if (prob==-1.0){
            return 0.0;
        }
        return prob;
    }

    private static void  dfs(int n, Graphic graphic, int start, int end, double p) {
        if (p<prob){
            return;
        }
        if (start==end){
            prob=Math.max(prob,p);
            return;
        }
        graphic.setVisit(true,start);
        double[] path=graphic.getPath(start);
        for (int i=0;i<path.length;i++){
            if (path[i]!=0&&i!=start&&!graphic.isVisit(i)){
                graphic.setVisit(true,i);
                dfs(n,graphic,i,end,p*path[i]);
                graphic.setVisit(false,i);
            }
        }
        graphic.setVisit(false,start);
    }
    static class Graphic{
        double[][] p;
        boolean[] visit;
        Graphic(int size){
            this.p=new double[size][size];
            visit=new boolean[size];
        }
        void set(double pro_e,int a, int b){
            p[a][b]=pro_e;
            p[b][a]=pro_e;
        }
        void setVisit(boolean visit_e,int n){
            visit[n]=visit_e;
        }
        double[] getPath(int start){
            return p[start];
        }
        boolean isVisit(int n){
            return visit[n];
        }
    }

    //BFS + 优先队列
    TreeMap<Integer, Double>[] adj;
    boolean[] visited;
    private class Node{
        int node;
        double p;
        public Node(int node, double p){
            this.node = node;
            this.p = p;
        }
    }
    public double maxProbability2(int n, int[][] edges, double[] succProb, int start, int end) {
        visited = new boolean[n];
        adj = new TreeMap[n];
        double res = 0;
        for(int i = 0; i < n; i ++){
            adj[i] = new TreeMap<>();
        }
        //邻接表
        for(int i = 0; i < succProb.length; i ++){
            adj[edges[i][0]].put(edges[i][1], succProb[i]);
            adj[edges[i][1]].put(edges[i][0], succProb[i]);
        }
        Comparator<Node> cmp = new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2){
                if(o2.p - o1.p > 0)
                    return 1;
                else
                    return -1;
            }
        };
        PriorityQueue<Node> pq = new PriorityQueue<Node>(cmp);
        pq.add(new Node(start, 1.0));
        int key;
        double val;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            visited[cur.node] = true;
            //开始bfs
            for(Map.Entry<Integer, Double> entry : adj[cur.node].entrySet()){
                key = entry.getKey();
                val = entry.getValue();
                double tmp = cur.p * val;
                if(!visited[key]){
                    pq.add(new Node(key, tmp));
                }
                if(key == end){
                    if(tmp > res)
                        res = tmp;
                }
            }
        }
        return res;
    }

    //比较快的
    public double maxProbability3(int n, int[][] edges, double[] succProb, int start, int end) {
        double[] dp = new double[n];
        dp[start] = 1;
        int len = edges.length;
        while(true){
            boolean k = false;
            for(int i=0;i<len;i++){
                if(dp[edges[i][0]]*succProb[i] > dp[edges[i][1]]){
                    dp[edges[i][1]] = dp[edges[i][0]]*succProb[i];
                    k = true;
                }
                if(dp[edges[i][1]]*succProb[i] > dp[edges[i][0]]){
                    dp[edges[i][0]] = dp[edges[i][1]]*succProb[i];
                    k = true;
                }
            }

            if(!k)break;
        }
        return dp[end];
    }

    /**
     * 一家快递公司希望在新城市建立新的服务中心。公司统计了该城市所有客户在二维地图上的坐标，并希望能够以此为依据为新的服务中心选址：使服务中心 到所有客户的欧几里得距离的总和最小 。
     *
     * 给你一个数组 positions ，其中 positions[i] = [xi, yi] 表示第 i 个客户在二维地图上的位置，返回到所有客户的 欧几里得距离的最小总和 。
     *
     * 换句话说，请你为服务中心选址，该位置的坐标 [xcentre, ycentre] 需要使下面的公式取到最小值：
     *
     *
     *
     * 与真实值误差在 10^-5 之内的答案将被视作正确答案。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：positions = [[0,1],[1,0],[1,2],[2,1]]
     * 输出：4.00000
     * 解释：如图所示，你可以选 [xcentre, ycentre] = [1, 1] 作为新中心的位置，这样一来到每个客户的距离就都是 1，所有距离之和为 4 ，这也是可以找到的最小值。
     * 示例 2：
     *
     *
     *
     * 输入：positions = [[1,1],[3,3]]
     * 输出：2.82843
     * 解释：欧几里得距离可能的最小总和为 sqrt(2) + sqrt(2) = 2.82843
     * 示例 3：
     *
     * 输入：positions = [[1,1]]
     * 输出：0.00000
     * 示例 4：
     *
     * 输入：positions = [[1,1],[0,0],[2,0]]
     * 输出：2.73205
     * 解释：乍一看，你可能会将中心定在 [1, 0] 并期待能够得到最小总和，但是如果选址在 [1, 0] 距离总和为 3
     * 如果将位置选在 [1.0, 0.5773502711] ，距离总和将会变为 2.73205
     * 当心精度问题！
     * 示例 5：
     *
     * 输入：positions = [[0,1],[3,2],[4,5],[7,6],[8,9],[11,1],[2,12]]
     * 输出：32.94036
     * 解释：你可以用 [4.3460852395, 4.9813795505] 作为新中心的位置
     *
     *
     * 提示：
     *
     * 1 <= positions.length <= 50
     * positions[i].length == 2
     * 0 <= positions[i][0], positions[i][1] <= 100
     */
    public double getMinDistSum(int[][] positions) {
        int[][] dx={{1,0},{0,1},{-1,0},{0,-1}};
        double eps=1E-8;
        double t=0.98;
        double x=0,y=0;
        double ret=1E18;
        double step=100;
        while (step>eps){
            int flag=1;
            while (flag==1) {
                flag = 0;
                for (int i = 0; i < 4; i++) {
                    double nx = x + dx[i][0] * step;
                    double ny = y + dx[i][1] * step;
                    double tmp = .0;
                    for (int j = 0; j < positions.length; j++) {
                        tmp += dis((double) positions[j][0], (double) positions[j][1], nx, ny);
                    }
                    if (tmp < ret) {
                        ret = tmp;
                        x = nx;
                        y = ny;
                        flag = 1;
                    }
                }
            }
            step*=t;
        }
        return ret;
    }

    double dis(double x1,double y1,double x2,double y2){
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }

}

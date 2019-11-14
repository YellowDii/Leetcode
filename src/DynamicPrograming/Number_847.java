package DynamicPrograming;

import java.util.LinkedList;
import java.util.Queue;

/*
847.访问所有节点的最短路径（shortest-path-visiting-all-nodes）
给出 graph 为有 N 个节点（编号为 0, 1, 2, ..., N-1）的无向连通图。 

graph.length = N，且只有节点 i 和 j 连通时，j != i 在列表 graph[i] 中恰好出现一次。

返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。

 

示例 1：

输入：[[1,2,3],[0],[0],[0]]
输出：4
解释：一个可能的路径为 [1,0,2,0,3]
示例 2：

输入：[[1],[0,2,4],[1,3,4],[2],[1,2]]
输出：4
解释：一个可能的路径为 [0,1,4,2,3]
 

提示：

1.  1 <= graph.length <= 12
2.  0 <= graph[i].length < graph.length

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Number_847 {
    String shortest_path_visiting_all_nodes = "847.访问所有节点的最短路径（shortest-path-visiting-all-nodes）\n";
    //想了半天不知道怎么用动态规划
    //bfs+用二进制数的每一位 标记点与点的状态
    public int shortestPathLength(int[][] graph) {
        int len = graph.length;//长度
        if (graph == null || graph.length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[len][1 << len];//用来标记访问状态， 需要注意的是1<<len代表了一种状态
        //例如len=4时，1<<len=10000,则visited[i][j]  0000<=j<=1111 每个位代表了该点是否被访问过
        int finishedstate = (1 << len) - 1; // 用二进制表示为 len个1 即所有点都被访问过 所以是finished状态
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            //遍历起点  4个点的情况：  [0,0001] [1,0010]  [2,0100] [3,1000]
            //其中第一位表示点的位置，第二位表示访问该点时的状态 初始时这些点都只代表该点被访问
            queue.offer(new int[]{i, 1 << i});
        }
        int step = 0;
        while (!queue.isEmpty()) {
            //每次遍历一 "层"
            for (int i = queue.size(); i > 0; i--) {
                int[] node = queue.poll();
                if (finishedstate == node[1]) {
                    //判断是不是访问完了
                    return step;
                }
                //开始正式bfs
                for (int next : graph[node[0]]) {
                    int nextstate = node[1] | (1 << next); //用来记录访问该点后的状态 例如0001|0010=0011 用来表示0访问了1后的状态
                    if (visited[next][nextstate]) {
                        continue;
                    }
                    visited[next][nextstate] = true;
                    queue.offer(new int[]{next, nextstate});//bfs 访问一个点后 向队列后加入该点
                }
            }
            step++; //遍历一层后再加步长
        }
        return step;//其实while循环中就已经返回了
    }
}

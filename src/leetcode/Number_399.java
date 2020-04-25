package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Number_399 {
    /**
     * 399.除法求值（evaluate-division）
     *
     * 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。
     *
     * 示例 :
     * 给定 a / b = 2.0, b / c = 3.0
     * 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
     * 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
     *
     * 输入为: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。
     *
     * 基于上述例子，输入如下：
     *
     * equations(方程式) = [ ["a", "b"], ["b", "c"] ],
     * values(方程式结果) = [2.0, 3.0],
     * queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
     * 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/evaluate-division
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<List<String>> equations;
    double[] values;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        this.equations = equations;
        this.values = values;
        double[] result = new double[queries.size()];
        int i = 0;
        for(List<String> query: queries){
            result[i] = findValue(query.get(0),query.get(1), 1.0, new HashSet<String>());
            i++;
        }
        return result;
    }

    private double findValue(String start, String end, double value, HashSet visited){
        if(visited.contains(start)){
            // if start is visited
            return -1.0;
        }
        visited.add(start);
        for(int i=0; i<equations.size(); i++){
            if(start.equals(equations.get(i).get(0)) && end.equals(equations.get(i).get(1))){
                // find target equation
                return value * values[i];
            }
            if(start.equals(equations.get(i).get(1)) && end.equals(equations.get(i).get(0))){
                // find inverse target equation
                return value * (1/values[i]);
            }
            if(start.equals(end) && (start.equals(equations.get(i).get(0))|| start.equals(equations.get(i).get(1)))){
                // start = end && start and end occur in equation
                return value;
            }
            if(start.equals(equations.get(i).get(0))){
                double res = findValue(equations.get(i).get(1), end, value * values[i], visited);
                if(res!=-1.0) return res;
            }
            if(start.equals(equations.get(i).get(1))){
                double res =  findValue(equations.get(i).get(0), end, value * (1/values[i]), visited);
                if(res!=-1.0) return res;
            }
        }
        return -1.0;
    }


    //并查集
    /**
     * key : 当前节点
     * value : 其父节点
     */
    private Map<String, String> parents = new HashMap<>();
    /**
     * key : 当前节点
     * value : 父节点/当前节点
     */
    private Map<String, Double> results = new HashMap<>();

    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        for (int i = 0; i < equations.size(); i++) {
            union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String e = queries.get(i).get(0);
            String q = queries.get(i).get(1);
            if (!(parents.containsKey(e) && parents.containsKey(q))) {
                result[i] = -1;
                continue;
            }
            if (e.equals(q)) {
                result[i] = 1;
                continue;
            }
            String r1 = root(e);
            String r2 = root(q);
            if (!r1.equals(r2)) {
                // 如果两者不相等，说明两个节点是不连通的
                result[i] = -1;
                continue;
            }
            result[i] = pm(q)/pm(e);
        }
        return result;
    }

    /**
     * 已知
     * a / fa = val[a]
     * b / fb = val[b]
     * 现在我们要合并a，b且 a / b=value
     * 所以我们需要设置 parent[fa]=fb
     * 由于fa父节点发生了变化所以它的值也需要变化,也就是要求 fa/fb的值
     * val[fa] = fa/fb = a/b * b/fb * fa/a = value * (val[b] / val[a])
     *
     */
    private void union(String parent, String child, double value) {
        add(parent);
        add(child);
        String r1 = root(parent);
        String r2 = root(child);
        if (!r1.equals(r2)) {
            parents.put(r2, r1);
            results.put(r2, value * (pm(parent)/pm(child)));
        }
    }
    private void add(String x) {
        if (!parents.containsKey(x)) {
            parents.put(x, x);
            results.put(x, 1.0);
        }
    }



    /**
     * 找到x的根节点
     */
    private String root(String x) {
        while (!parents.get(x).equals(x)) {
            x = parents.get(x);
        }
        return x;
    }
    /**
     * 循环的pm函数
     */
    private double pm(String x) {
        double v = 1;
        while (!parents.get(x).equals(x)) {
            v*= results.get(x);
            x = parents.get(x);
        }
        return v;
    }




}

package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Number_297 {
    /**
     * 297.二叉树的序列化与反序列化（serialize-and-deserialize-binary-tree）
     *
     * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
     * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
     *
     * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     *
     * 示例: 
     *
     * 你可以将以下二叉树：
     *
     *     1
     *    / \
     *   2   3
     *      / \
     *     4   5
     *
     * 序列化为 "[1,2,3,null,null,4,5]"
     * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
     *
     * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
       TreeNode() { }
    }
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize2(TreeNode root) {
            if (root==null){
                return "N";
            }
            Queue<TreeNode> queue=new LinkedList<>();
            StringBuilder sb=new StringBuilder();
            queue.add(root);
            while (!queue.isEmpty()){
                TreeNode tmp=queue.poll();
                if (tmp==null){
                    sb.append("N");
                }else {
                    sb.append(tmp.val);
                    queue.add(tmp.left);
                    queue.add(tmp.right);
                }
            }
            return sb.toString();
        }
        //测试后发现没法应对负数 而且我把它想象成个位数了 也没法应对多位数 我好蠢 。。。。。。。。。啊啊啊啊啊啊啊！！！！！
        // Decodes your encoded data to tree.
        public TreeNode deserialize2(String data) {
            if (data==null||data.length()<0||data.charAt(0)=='N'){
                return null;
            }
            TreeNode root=new TreeNode(Integer.valueOf(data.charAt(0)+""));
            Queue<TreeNode> queue=new LinkedList<>();
            queue.add(root);
            int len=data.length();
            for (int i=0;i<len&&!queue.isEmpty();i++){
                TreeNode tmp=queue.poll();
                if (tmp!=null){
                    int left=i*2+1;
                    int right=i*2+2;
                    if (left<len){
                        if (data.charAt(left)=='N'){
                            tmp.left=null;
                        }else {
                            tmp.left=new TreeNode(Integer.valueOf(data.charAt(left)+""));
                        }
                        queue.add(tmp.left);
                    }
                    if (right<len){
                        if (data.charAt(right)=='N'){
                            tmp.right=null;
                        }else {
                            tmp.right=new TreeNode(Integer.valueOf(data.charAt(right)+""));
                        }
                        queue.add(tmp.right);
                    }
                }
            }
            return root;
        }
        //官方解法
        //重新开始:用逗号分割....
        public String rserialize(TreeNode root, String str) {
            // Recursive serialization.
            if (root == null) {
                str += "null,";
            } else {
                str += str.valueOf(root.val) + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        public TreeNode rdeserialize(List<String> l) {
            // Recursive deserialization.
            if (l.get(0).equals("null")) {
                l.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
            l.remove(0);
            root.left = rdeserialize(l);
            root.right = rdeserialize(l);

            return root;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] data_array = data.split(",");
            List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
            return rdeserialize(data_list);
        }

        //比较快的
        // Encodes a tree to a single string.
        public String serialize3(TreeNode root) {
            StringBuilder string = new StringBuilder();
            getResultS(root,string);
            return string.toString();
        }

        private void getResultS(TreeNode root, StringBuilder string) {
            if (root == null) {
                string.append("~,");

            } else {
                string.append(root.val);
                string.append(",");
                getResultS(root.left, string);
                getResultS(root.right, string);
            }

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize3(String data) {
            char[] cs = data.toCharArray();
            int[] pos = new int[] {0};
            return getResultD(cs,pos);
        }
        //pos 记录下标
        private TreeNode getResultD(char[] cs,int[] pos) {
            if (cs[pos[0]] == '~') {
                pos[0] += 2;
                return null;
            }
            boolean flag = false;
            if (cs[pos[0]] == '-') {
                flag = true;
                pos[0]++;
            }
            int num = 0;
            while (cs[pos[0]] != ',') {
                num = num * 10 + cs[pos[0]] - '0';
                pos[0]++;
            }
            pos[0]++;
            if (flag) {
                num = -num;
            }
            TreeNode node = new TreeNode();
            node.val = num;
            node.left = getResultD(cs, pos);
            node.right = getResultD(cs, pos);
            return node;
        }
    }
}

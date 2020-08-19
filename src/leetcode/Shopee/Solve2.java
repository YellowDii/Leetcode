package leetcode.Shopee;

import com.sun.org.apache.regexp.internal.RE;

public class Solve2 {
    public static void main(String[] args) {
        System.out.println(solve("103"));
        System.out.println(solve("23"));
        System.out.println(solve("998877665544332211"));
        System.out.println(solve("211552578248897482482"));
        System.out.println(solve("16464842532121488787"));
        System.out.println(solve("21499126101221111110022"));
    }

    public static String solve(String in){
        char[] chars=in.toCharArray();
        //记录每个数字出现的次数
        int[] nums=new int[10];
        String s=new String(in);
        for (int i=chars.length-1;i>=1;i--){
            while (i>=1&&chars[i]==chars[i-1]){
                i--;
            }
            for (int j=i-1;j>=0;j--){
                if (chars[i]<chars[j]){
                    swap(chars,i,j);
                    //另外还要把j后面的换成最大情况
                    String cur=new String(chars);
                    swap(chars,i,j);
                    cur=change(cur,j);
                    if (s.equals(in)){
                        //初始状态
                        s=cur;
                    }else {
                        if (s.compareTo(cur)<0){
                            s=cur;
                            break;
                        }
                    }
                }
            }
        }
        if (s.equals(in)||s.charAt(0)=='0'){
            return "0";
        }
        return s;
    }

    private static String change(String cur, int j) {
        String s=cur.substring(0,j+1);
        int[] nums=new int[10];
        for (int i=j+1;i<cur.length();i++){
            nums[cur.charAt(i)-'0']++;
        }
        for (int i=9;i>=0;i--){
            while (nums[i]>0){
                s+=(char)(i+'0');
                nums[i]--;
            }
        }
        return s;
    }

    private static void swap(char[] chars, int i, int j) {
        char c=chars[i];
        chars[i]=chars[j];
        chars[j]=c;
    }
}

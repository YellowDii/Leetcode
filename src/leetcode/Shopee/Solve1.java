package leetcode.Shopee;

public class Solve1 {
    private static String solve(String in){
        StringBuilder sb=new StringBuilder();
        boolean isHead=false;
        String word="";
        for (int i=0;i<in.length();i++){
            if ((Character.isDigit(in.charAt(i))||Character.isLowerCase(in.charAt(i))||Character.isUpperCase(in.charAt(i)))){
                if (isHead){
                    //头部全部大写
                    char c=in.charAt(i);
                    if (c>='a'&&c<='z'){
                        c=Character.toUpperCase(c);
                    }
                    word+=c;
                    isHead=false;
                }else {
                    //非头部 全部小写
                    char c=in.charAt(i);
                    if (c>='A'&&c<='Z'){
                        c=Character.toLowerCase(c);
                    }
                    word+=c;
                }
            }else {
                sb.append(word);
                isHead=true;
                word="";
            }
        }
        sb.append(word);
        String res=sb.toString();
        if (res.length()>0&&res.charAt(0)>='A'&&res.charAt(0)<='Z'){
            char c=res.charAt(0);
            char c_new=Character.toLowerCase(c);
            return c_new+res.substring(1,res.length());
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(solve("__UPPER__CASE__"));
        System.out.println(solve("This is a Demo!"));
        System.out.println(solve("CapitalizedWords"));
    }

}

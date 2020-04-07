package SwordOffer;

public class Test004 {
    /**
     * 构造一个函数，使字符串中的每个空格替换成"%20" 例如：we are happy => we%20are%20happy
     * @param s 要转换的字符数组
     * @param usedLength 字符数组已使用的长度
     * @return  转换后的字符长度,-1表示转换失败
     */
    public static int replaceBlank(char[] s,int usedLength){
        //判断输入条件
        if (s==null||s.length<usedLength){
            return -1;
        }
        int whiteCount=0;
        for (int i=0;i<usedLength;i++){
            if (s[i]==' '){
                whiteCount++;
            }
        }
        int transferLength=whiteCount*2+usedLength;
        int res=transferLength;
        //转换后长度>数组长度 则转换失败
        if (transferLength>s.length){
            return -1;
        }
        if (whiteCount==0){
            return usedLength;
        }
        transferLength--;
        usedLength--;
        while (whiteCount>0&&transferLength>=usedLength){
            if (s[usedLength]==' '){
                s[transferLength--]='0';
                s[transferLength--]='2';
                s[transferLength--]='%';
            }else {
                s[transferLength--]=s[usedLength];
            }
            usedLength--;
        }
        return res;
    }
}

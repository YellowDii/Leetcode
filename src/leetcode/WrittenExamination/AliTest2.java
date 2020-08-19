package leetcode.WrittenExamination;

import java.util.*;

public class AliTest2 {
    public static void main(String[] args) {
        String s1=new String("aaabb");
        String[] s2=new String[2];
        s2[0]="ab";
        s2[1]="bb";
        solve2(s1,s2);
        String s3=new String("alibaba");
        String[] s4=new String[2];
        s4[0]="a";
        s4[1]="ba";
        solve2(s3,s4);
    }
    private long ans=0;
    public static void solve1(int n){
        //��dp����
    }



    public static void solve2(String s1,String[] s2){
        //�ȴ洢S Ȼ���ж�ÿ�����ַ����ǲ����Ӵ�
        Map<Character,Integer> map=new HashMap<>();
        for (char c:s1.toCharArray()){
            Integer i=map.get(c);
            if (i==null){
                map.put(c,1);
            }else {
                map.put(c,i+1);
            }
        }
        boolean flag=true;
        while (flag){
            flag=false;
            for (String s:s2){
                //���Ӵ�
                if (isSubString(map,s)){
                    flag=true;
                }
            }
        }
        //���ƴ��
        StringBuilder s=new StringBuilder();
        for (char c:s1.toCharArray()){
            if (map.get(c)>0){
                s.append(c);
                map.put(c,map.get(c)-1);
            }
        }
        System.out.println(s.toString());
    }

    private static boolean isSubString(Map<Character, Integer> map, String s) {
        boolean flag=true;
        Map<Character,Integer> submap=new HashMap<>();
        for (char c:s.toCharArray()){
            Integer i=submap.get(c);
            if (i==null){
                submap.put(c,1);
            }else {
                submap.put(c,i+1);
            }
        }
        //���ж��ܲ��ܳ�Ϊ�Ӵ�
        for (Map.Entry<Character,Integer> e:submap.entrySet()){
            if (map.get(e.getKey())>=e.getValue()){
                continue;
            }else {
                flag=false;
                return flag;
            }
        }
        //ɾ������
        if (flag){
            for (Map.Entry<Character,Integer> e:submap.entrySet()){
                map.put(e.getKey(),map.get(e.getKey())-e.getValue());
            }
        }
        return flag;
    }
}

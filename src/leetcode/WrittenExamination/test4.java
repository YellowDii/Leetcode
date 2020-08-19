package leetcode.WrittenExamination;

import java.util.ArrayList;
import java.util.List;

public class test4 {
    //���� ��������� ������ͬ�İ�ԭ����˳�� ����ķŵ�ĩβ
    //�ռ�O(n) �����Ż�
    public static List<Integer> sort(int[] nums){
        List<Integer> positive=new ArrayList<>();
        List<Integer> nagative=new ArrayList<>();
        for (int num:nums){
            if (num>0){
                positive.add(num);
            }else if (num<0){
                nagative.add(num);
            }
        }
        List<Integer> ans=new ArrayList<>();
        int i=0,j=0;
        while (i<positive.size()&&j<nagative.size()){
            ans.add(positive.get(i));
            ans.add(nagative.get(i));
            i++;
            j++;
        }
        if (i<positive.size()){
            while (i<positive.size()){
                ans.add(positive.get(i));
                i++;
            }
        }
        if (j<nagative.size()){
            while (j<nagative.size()){
                ans.add(nagative.get(i));
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] array=new int[]{1,2,3,4,5,6,-3,-4,-5};
        List<Integer> ans=sort(array);
        for (Integer i:ans){
            System.out.print(i+" ");
        }
    }

}

package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Test003 {
    public void change(List a){
        a.add("1");
    }

    public void setNull(List a){
        a=null;
    }
    public void changeAll(List a){
        a=new ArrayList<Integer>(){};
    }
    public void changeString(String s){
        s="13214";
    }


}

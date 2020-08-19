package leetcode.WrittenExamination;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestYuanfudao {
    static class Course implements Comparable {
        int start;
        int end;

        Course(int start,int end){
            this.start=start;
            this.end=end;
        }
        @Override
        public int compareTo(Object o) {
            Course c=(Course)o;
            if (c.start==this.start){
                return c.end-this.end;
            }else {
                return this.start-c.start;
            }
        }
    }
    public static int solve(Course[] courses){
        if (courses.length<=1){
            return 1;
        }
        Arrays.sort(courses);
        Map<Integer,Integer> map=new HashMap<>();
        int res=1;
        for (int i=1;i<courses.length;i++){
            for (int j=i-1;j>=0;j--){
                if (courses[i].start<courses[j].end){
                    Integer k=map.get(j);
                    if (k==null){
                        map.put(i,2);
                        res=Math.max(res,2);
                    }else {
                        map.put(i,k+1);
                        res=Math.max(res,k+1);
                    }
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Course[] courses=new Course[9];
        courses[0]=new Course(1,10);
        courses[1]=new Course(2,3);
        courses[2]=new Course(2,4);
        courses[3]=new Course(3,4);
        courses[4]=new Course(2,8);
        courses[5]=new Course(4,8);
        courses[6]=new Course(5,8);
        courses[7]=new Course(6,8);
        courses[8]=new Course(7,11);
        System.out.println(solve(courses));

        Course[] courses1=new Course[4];
        courses1[0]=new Course(1,4);
        courses1[1]=new Course(1,2);
        courses1[2]=new Course(2,3);
        courses1[3]=new Course(3,4);
        System.out.println(solve(courses1));
    }
}

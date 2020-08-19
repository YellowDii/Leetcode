package leetcode.WrittenExamination;

import java.util.*;

public class PddTest {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int[][] nums=new int[n][6];
        for (int i=0;i<n;i++){
            nums[i][0]=s.nextInt();
            nums[i][1]=s.nextInt();
            nums[i][2]=s.nextInt();
            nums[i][3]=s.nextInt();
            nums[i][4]=s.nextInt();
            nums[i][5]=s.nextInt();
        }
        List<Integer> ans=solve1(nums);
        System.out.println(ans.size());
        for (int i=0;i<ans.size()-1;i++){
            System.out.print(ans.get(i)+" ");
        }
        System.out.print(ans.get(ans.size()-1));
    }
    //骰子 总共有30种情况 首先一对对的总共15种 然后置反*2
    public static List<Integer> solve1(int[][] nums){
        int len=nums.length;
        Map<Dice,Integer> map=new HashMap<>();
        for (int i=0;i<len;i++){
            Dice dice=new Dice(nums[i][0],nums[i][1],nums[i][2],nums[i][3],nums[i][4],nums[i][5]);
            Integer n=map.get(dice);
            if (n==null){
                map.put(dice,1);
            }else {
                map.put(dice,n+1);
            }
        }
        List<Integer> ans=new ArrayList<>();
        for (Integer i:map.values()){
            ans.add(i);
        }
        Collections.sort(ans, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        return ans;
    }

    public static class Dice{
        int front;
        int back;
        int left;
        int right;
        int up;
        int down;
        Dice(int up,int down,int left,int right,int front,int back){
            this.up=up;
            this.down=down;
            this.left=left;
            this.right=right;
            this.front=front;
            this.back=back;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Dice dice = (Dice) o;
            //先比较对应的数
            int face1=(this.up+6)*(this.down+6);
            int face2=(this.left+6)*(this.right+6);
            int face3=(this.front+6)*(this.back+6);
            int face4=(dice.up+6)*(dice.down+6);
            int face5=(dice.left+6)*(dice.right+6);
            int face6=(dice.front+6)*(dice.back+6);
            if (face1+face2+face3!=face4+face5+face6){
                return false;
            }else {
                //先保证上下对位 然后不停左旋
                if (face1==face4){
                    if (this.up!=dice.up){
                        dice=turnUp(dice);
                        dice=turnUp(dice);
                    }
                    int i=0;
                    while (i<3){
                        dice=turnLeft(dice);
                        if (this.front==dice.front&&this.right==dice.right){
                            return true;
                        }
                        i++;
                    }
                }else if (face1==face5){
                    dice=turnLeft(dice);
                    dice=turnUp(dice);
                    if (this.up!=dice.up){
                        dice=turnUp(dice);
                        dice=turnUp(dice);
                    }
                    int i=0;
                    while (i<3){
                        dice=turnLeft(dice);
                        if (this.front==dice.front&&this.right==dice.right){
                            return true;
                        }
                        i++;
                    }
                }else if (face1==face6){
                    dice=turnUp(dice);
                    if (this.up!=dice.up){
                        dice=turnUp(dice);
                        dice=turnUp(dice);
                    }
                    int i=0;
                    while (i<3){
                        dice=turnLeft(dice);
                        if (this.front==dice.front&&this.right==dice.right){
                            return true;
                        }
                        i++;
                    }
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = front;
            result = 31 * result + back;
            result = 31 * result + left;
            result = 31 * result + right;
            result = 31 * result + up;
            result = 31 * result + down;
            return result;
        }

        Dice turnLeft(Dice target){
            return new Dice(target.up,target.down,target.front,target.back,target.right,target.left);
        }
        Dice turnUp(Dice target){
            return new Dice(target.front,target.back,target.left,target.right,target.down,target.up);
        }
    }
}

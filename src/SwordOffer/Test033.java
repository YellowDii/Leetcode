package SwordOffer;

import java.util.Comparator;

public class Test033 {
    /**
     * 题目：输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
     * 打印能拼接出的所有数字中最小的一个。
     */
    public static String printMinNumber(String[] array) {

        if (array == null || array.length < 1) {
            throw new IllegalArgumentException("Array must contain value");
        }

        MComparator comparator = new MComparator();
        quickSort(array, 0, array.length - 1, comparator);
        //StringBuilder比StringBuffer效率高点 没用synchronized
        StringBuilder builder = new StringBuilder(256);
        for (String s : array) {
            builder.append(s);
        }

        return builder.toString();
    }

    private static void quickSort(String[] array, int start, int end, Comparator<String> comparator) {
        if (start<end){
            String pivot=array[start];
            int left=start;
            int right=end;
            while (left<right){
                while (left<right&&comparator.compare(array[right],pivot)>=0){
                    right--;
                }
                array[left]=array[right];
                while (left<right&&comparator.compare(array[left],pivot)<=0){
                    left++;
                }
                array[right]=array[left];
            }
            array[left]=pivot;
            quickSort(array,start,left-1,comparator);
            quickSort(array,left+1,end,comparator);
        }
    }

    //根据需求自定义的比较器
    private static class MComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1==null||o2==null){
                throw new IllegalArgumentException("Arg should not be null");
            }
            String s1=o1+o2;
            String s2=o2+o1;
            return s1.compareTo(s2);
        }
    }
    public static void main(String[] args) {

        String[] data = {"3", "5", "1", "4", "2"};
        System.out.println(printMinNumber(data));

        String[] data2 = {"3", "32", "321"};
        System.out.println(printMinNumber(data2));

        String[] data3 = {"3", "323", "32123"};
        System.out.println(printMinNumber(data3));

        String[] data4 = {"1", "11", "111"};
        System.out.println(printMinNumber(data4));

        String[] data5 = {"321"};
        System.out.println(printMinNumber(data5));

    }
}

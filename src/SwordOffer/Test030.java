package SwordOffer;


import java.util.ArrayList;
import java.util.List;

public class Test030 {
    /**
     * Top K 问题
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
     *
     * 法1：先对数组排序，然后取出前k个
     * 法2：利用最大堆保存这k个数，每次只和堆顶比，如果比堆顶小，删除堆顶，新数入堆。
     */
    /**
     * 最大堆
     */
    private final static class MaxHeap<T extends Comparable<T>>{
        //堆中集合
        private List<T> items;
        //用于计数
        private int cursor;
        /**
         * 构造一个椎，初始大小是32
         */
        public MaxHeap(){
            this(32);
        }
        /**
         * 造诣一个指定初始大小的堆
         *
         * @param size 初始大小
         */
        public MaxHeap(int size) {
            items = new ArrayList<>(size);
            cursor = -1;
        }

        /**
         * 向上调整堆
         * @param index 被上移元素的初始位置
         */
        private void siftUp(int index){
            T intent=items.get(index);//获取需要调整的元素

            while (index>0){//确定不是根元素
                int parentIndex=(index-1)/2;
                T parent = items.get(parentIndex);
                if (intent.compareTo(parent)>0){
                    items.set(index,parent);
                    index=parentIndex;
                }else {
                    break;
                }
            }

            items.set(index,intent);
        }

        /**
         * 向下调整堆
         * @param index
         */
        private void siftDown(int index){
            T intent=items.get(index);
            int leftIndex=2*index+1;

            while (leftIndex<items.size()){
                T maxChild = items.get(leftIndex); // 取左子结点的元素对象，并且假定其为两个子结点中最大的
                int maxIndex = leftIndex; // 两个子节点中最大节点元素的位置，假定开始时为左子结点的位置

                int rightIndex = leftIndex + 1;  // 获取右子结点的位置
                if (rightIndex < items.size()) {  // 如果有右子结点
                    T rightChild = items.get(rightIndex);  // 获取右子结点的元素对象
                    if (rightChild.compareTo(maxChild) > 0) {  // 找出两个子节点中的最大子结点
                        maxChild = rightChild;
                        maxIndex = rightIndex;
                    }
                }

                // 如果最大子节点比父节点大，则需要向下调整
                if (maxChild.compareTo(intent) > 0) {
                    items.set(index, maxChild); // 将子节点向上移
                    index = maxIndex; // 记录上移节点的位置
                    leftIndex = index * 2 + 1; // 找到上移节点的左子节点的位置
                } else { // 最大子节点不比父节点大，说明父子路径已经按从大到小排好顺序了，不需要调整了
                    break;
                }
            }
            // index此时记录是的最后一个被上移的子节点的位置（也可能是自身），所以将最开始的调整的元素值放入index位置即可
            items.set(index, intent);
        }

        /**
         * 向堆中添加新元素
         * ArrayList 会自己扩容
         * @param item
         * @return
         */
        public void add(T item){
            items.add(item);
            siftUp(items.size()-1);
        }

        /**
         * 删除堆顶
         * @return
         */
        public T deleteTop(){
            if (items.isEmpty()){
                throw new RuntimeException("The heap is empty.");
            }
            T maxItem=items.get(0);// 获取堆顶元素
            T lastItem=items.remove(items.size()-1);// 删除最后一个元素
            if (items.isEmpty()){// 删除元素后，如果堆为空的情况，说明删除的元素也是堆顶元素
                return lastItem;
            }
            items.set(0,lastItem);//将堆底元素放入顶部
            siftDown(0);//向下调整
            return maxItem;//放回要删除的之前的堆顶
        }

        /**
         * 获取下一个元素
         * @return 下一个元素对象
         */
        public T next(){
            if (cursor>=items.size()){
                throw new RuntimeException("No more element");
            }
            return items.get(cursor);
        }
        /**
         * 判断堆中是否还有下一个元素
         *
         * @return true堆中还有下一个元素，false堆中无下五元素
         */
        public boolean hasNext() {
            cursor++;
            return cursor < items.size();
        }

        /**
         * 获取堆中的第一个元素
         *
         * @return 堆中的第一个元素
         */
        public T first() {
            if (items.size() == 0) {
                throw new RuntimeException("The heap is empty.");
            }
            return items.get(0);
        }

        /**
         * 判断堆是否为空
         *
         * @return true是，false否
         */
        public boolean isEmpty() {
            return items.isEmpty();
        }

        /**
         * 获取堆的大小
         *
         * @return 堆的大小
         */
        public int size() {
            return items.size();
        }

        /**
         * 清空堆
         */
        public void clear() {
            items.clear();
        }
    }

    public static void getLeastNumbers(int[] input,int[] output){
        if (input == null || output == null || output.length <= 0 || input.length < output.length) {
            throw new IllegalArgumentException("Invalid args");
        }
        MaxHeap<Integer> maxHeap=new MaxHeap<>(output.length);
        for (int i:input){
            if (maxHeap.size()<output.length){//确保maxHeap中只有output.length个
                maxHeap.add(i);
            }else {
                int max=maxHeap.first();
                if (max>i){
                    maxHeap.deleteTop();
                    maxHeap.add(i);
                }
            }
        }
        for (int i=0;maxHeap.hasNext();i++){
            output[i]=maxHeap.next();
        }
    }
    /**
     * 题目： 输入n个整数，找出其中最小的k个数。
     * 【第一种解法】
     * @param input  输入数组
     * @param output 输出数组
     */
    public static void getLeastNumbers2(int[] input, int[] output) {

        if (input == null || output == null || output.length <= 0 || input.length < output.length) {
            throw new IllegalArgumentException("Invalid args");
        }

        int start = 0;
        int end = input.length - 1;
        int index = partition(input, start, end);
        int target = output.length - 1;

        while (index != target) {
            if (index < target) {
                //表示输出区间在左边 右边需要调整
                start = index + 1;
            } else {
                //表示输出区间在右边 右边需要调整
                end = index - 1;
            }
            index = partition(input, start, end);
        }

        System.arraycopy(input, 0, output, 0, output.length);
    }

    /**
     * 分区算法 每次返回一个正确顺序位置的index
     * 左边都是小于它的 右边都是大于它的
     * @param input 输入数组
     * @param start 开始下标
     * @param end   结束下标
     * @return 分区位置
     */
    private static int partition(int[] input, int start, int end) {
        int tmp = input[start];

        while (start < end) {
            while (start < end && input[end] >= tmp) {
                end--;
            }
            input[start] = input[end];

            while (start < end && input[start] <= tmp) {
                start++;
            }
            input[end] = input[start];
        }

        input[start] = tmp;
        return start;
    }
    public static void main(String[] args) {
        System.out.println("第一种解法：");
        test1();
        System.out.println();


    }

    private static void test1() {
        int[] data = {4, 5, 1, 6, 2, 7, 3, 8};

        int[] output = new int[4];
        getLeastNumbers(data, output);
        for (int i : output) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] output2 = new int[8];
        getLeastNumbers(data, output2);
        for (int i : output2) {
            System.out.print(i + " ");
        }
        System.out.println();


        int[] output3 = new int[1];
        getLeastNumbers(data, output3);
        for (int i : output3) {
            System.out.print(i + " ");
        }
        System.out.println();


        int[] data2 = {4, 5, 1, 6, 2, 7, 2, 8};
        int[] output4 = new int[2];
        getLeastNumbers(data2, output4);
        for (int i : output4) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}

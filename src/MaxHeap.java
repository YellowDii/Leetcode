public class MaxHeap<E> {
    private transient Object[] queue;

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    private int size = 0;

    private int capacity;

    public MaxHeap(int initCapacity) {
        if (initCapacity < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = initCapacity;
        // 数组索引从1开始用
        this.queue = new Object[capacity];
    }

    /**
     * 根据数组构造一个堆 从最后一个非叶子节点向下调整
     * @param array 传入数组
     */
    @SuppressWarnings("unchecked")
    public MaxHeap(E[] array) {
        int len = array.length;
        if (len < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = len;
        this.queue = new Object[capacity];
        System.arraycopy(array, 0, this.queue, 0, len);
        this.size = len;
        for (int i = (len - 2) / 2; i >= 0; i--) {
            siftDown(i,(E)queue[i]);
        }
    }

    public int size() {
        return size;
    }

    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        int i = size++;
        if (i == 0) {
            queue[0] = e;
        } else if (size > capacity) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            siftUp(i, e);
        }
        return true;
    }

    /**
     * 元素上浮
     * @param i 要插入的位置
     * @param e 元素内容
     */
    @SuppressWarnings("unchecked")
    private void siftUp(int i, E e) {
        Comparable<? super E> key = (Comparable<? super E>)e;
        while (i > 0) {
            // 先计算父节点的位置
            int parent = (i - 1) >>> 1;
            // 取出父节点的数据
            Object v = queue[parent];
            // 如果比父节点要小，就结束循环(不用上浮了)
            if (key.compareTo((E)v) < 0) {
                break;
            }
            // 将父节点的值赋给子节点，父节点位置空出
            queue[i] = v;
            // 继续比较父节点的父节点
            i = parent;
        }
        // 将要插入的元素放在合适的位置
        queue[i] = key;
    }

    /**
     * 取出队头元素
     * @return 优先级最高的元素(最大)
     */
    @SuppressWarnings("unchecked")
    public E poll() {
        if (size == 0) {
            return null;
        }
        int i = --size;
        // 取出队头元素
        E e = (E)queue[0];
        // 取出队尾元素
        E x = (E)queue[i];
        queue[i] = null;
        if (i != 0) {
            siftDown(0, x);
        }
        return e;
    }

    /**
     * 元素下沉 用于删除操作
     * @param i 要下沉的位置
     * @param x 元素
     */
    @SuppressWarnings("unchecked")
    private void siftDown(int i, E x) {
        Comparable<? super E> key = (Comparable<? super E>)x;
        // 只有存在子节点时才继续
        int half = size >>> 1;
        while (i < half) {
            // 左孩子的位置
            int child = (i << 1) + 1;
            // 如果右孩子存在且右孩子大于左孩子
            if (child + 1 < size &&
                    ((Comparable<? super E>) queue[child]).
                            compareTo((E)queue[child + 1]) < 0) {
                child = child + 1;
            }
            // 如果父节点比子节点大，就直接结束循环
            if (key.compareTo((E) queue[child]) > 0) {
                break;
            }
            // 把子节点的值赋给父节点
            queue[i] = queue[child];
            // 继续向下一个父节点
            i = child;
        }
        queue[i] = x;
    }
}

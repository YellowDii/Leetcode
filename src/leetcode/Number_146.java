package leetcode;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Number_146 {
    /**
     * 146.LRU缓存机制（lru-cache）
     *
     * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
     *
     * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
     * 写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；
     * 如果密钥不存在，则插入该组「密钥/数据值」。
     * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     *
     *  
     * 进阶:
     *
     * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
     *
     * 示例:
     *
     * LRUCache cache = new LRUCache(2); 缓存容量
     *cache.put(1,1);
     *cache.put(2,2);
     *cache.get(1);       // 返回  1
     *cache.put(3,3);    // 该操作会使得密钥 2 作废
     *cache.get(2);       // 返回 -1 (未找到)
     *cache.put(4,4);    // 该操作会使得密钥 1 作废
     *cache.get(1);       // 返回 -1 (未找到)
     *cache.get(3);       // 返回  3
     *cache.get(4);       // 返回  4
     *
     *来源：力扣（LeetCode）
     *链接：https://leetcode-cn.com/problems/lru-cache
     *著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class LRUCache extends LinkedHashMap<Integer, Integer> {

        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    /**
     * 官方解答 手动实现
     */
    class LRUCache2 {

        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
        }

        private void addNode(DLinkedNode node) {
            /**
             * Always add the new node right after head.
             */
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node){
            /**
             * Remove an existing node from the linked list.
             */
            DLinkedNode prev = node.prev;
            DLinkedNode next = node.next;

            prev.next = next;
            next.prev = prev;
        }

        private void moveToHead(DLinkedNode node){
            /**
             * Move certain node in between to the head.
             */
            removeNode(node);
            addNode(node);
        }

        private DLinkedNode popTail() {
            /**
             * Pop the current tail.
             */
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

        private Hashtable<Integer, DLinkedNode> cache =
                new Hashtable<Integer, DLinkedNode>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache2(int capacity) {
            this.size = 0;
            this.capacity = capacity;

            head = new DLinkedNode();
            // head.prev = null;

            tail = new DLinkedNode();
            // tail.next = null;

            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) return -1;

            // move the accessed node to the head;
            moveToHead(node);

            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);

            if(node == null) {
                DLinkedNode newNode = new DLinkedNode();
                newNode.key = key;
                newNode.value = value;

                cache.put(key, newNode);
                addNode(newNode);

                ++size;

                if(size > capacity) {
                    // pop the tail
                    DLinkedNode tail = popTail();
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                // update the value.
                node.value = value;
                moveToHead(node);
            }
        }
    }

    class LRUCache3{
        private Map<Integer,ListNode> cache;
        private ListNode head;
        private ListNode tail;
        private int capicity;
        class ListNode{
            int key;
            int val;
            ListNode pre;
            ListNode next;
            ListNode(int key,int val){
                this.key=key;
                this.val=val;
                this.pre=null;
                this.next=null;
            }
        }

        public LRUCache3(int capacity) {
            this.capicity=capacity;
            this.cache=new HashMap<>();
            head=new ListNode(-1,-1);
            tail=new ListNode(-1,-1);
            head.next=tail;
            tail.pre=head;
        }

        public int get(int key) {
            if (!cache.containsKey(key)){
                return -1;
            }
            ListNode tmp=cache.get(key);
            moveToHead(tmp);
            return tmp.val;
        }

        private void removeNode(ListNode node){
            ListNode pre=node.pre;
            ListNode next=node.next;

            pre.next=next;
            next.pre=pre;
        }
        private void moveToHead(ListNode node){
            removeNode(node);
            addNode(node);
        }

        private void addNode(ListNode node) {
            node.pre=head;
            node.next=head.next;

            head.next.pre=node;
            head.next=node;
        }

        public void put(int key, int value) {
            ListNode tmp=cache.get(key);
            if (tmp==null){
                tmp=new ListNode(key,value);
                addNode(tmp);
                cache.put(key,tmp);

                if (capicity<cache.size()){
                    //删除最尾部节点
                    cache.remove(tail.pre.key);
                    removeNode(tail.pre);

                }
            }else {
                //直接更新
                tmp.val=value;
                moveToHead(tmp);
            }
        }
    }

}

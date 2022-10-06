package toplikedquestions;

import java.util.HashMap;

// LRU 缓存
// https://leetcode.cn/problems/lru-cache/
// 提交前将类名和构造方法名 由Problem_0146_LRUCache 换成 LRUCache
public class Problem_0146_LRUCache {
    public DoubleLinkedList cache;
    public HashMap<Integer, Node> map;
    public int capacity;

    public Problem_0146_LRUCache(int capacity) {
        this.cache = new DoubleLinkedList();
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        make(key);  // 表示最近使用
        return map.get(key).value;
    }

    // 最近使用(提升优先级, 先删掉再添加)
    private void make(int key) {
        Node node = map.get(key);
        cache.remove(node);
        cache.addLast(node);
    }

    public void put(int key, int value) {
        // (1) 如果存在, 先删除再添加(提升为最近使用)
        if (map.containsKey(key)) {
            delete(key);
            add(key, value);
            return;
        }
        // (2) 不存在, 先判断容量是否满了, 满了就删除最久未使用的, 然后添加
        if (capacity == cache.size) {
            removeLast();
        }
        add(key, value);
    }

    private void delete(int key) {
        Node node = map.get(key);
        cache.remove(node);
        map.remove(node);
    }

    // 添加为最近使用(map 和 list)
    private void add(int key, int value) {
        Node node = new Node(key, value);
        cache.addLast(node);
        map.put(key, node);
    }

    // 删除最久未使用
    private void removeLast() {
        Node node = cache.removeFirst();
        int deleteKey = node.key;
        map.remove(deleteKey);
    }

    public class Node {
        public int key;
        public int value;
        public Node pre;
        public Node next;

        public Node(int key, int val) {
            this.key = key;
            this.value = val;
        }
    }

    public class DoubleLinkedList {
        private Node head;
        private Node tail;
        private int size;

        public DoubleLinkedList() {
            this.size = 0;
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
        }

        // 尾插法
        public void addLast(Node node) {
            node.pre = tail.pre;
            node.next = tail;
            tail.pre.next = node;
            tail.pre = node;
            size++;
        }

        public void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size--;
        }

        public Node removeFirst() {
            if (head.next == tail) {  // 链表为空
                return null;
            }
            Node node = head.next;
            remove(node);
            return node;
        }
    }
}

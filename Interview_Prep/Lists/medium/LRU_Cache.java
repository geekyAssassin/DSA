package medium;

import java.util.HashMap;
import java.util.Map;

class DLL {
    int key;
    int value;
    DLL next;
    DLL prev;

    public DLL(int key, int val) {
        this.key = key;
        this.value = val;
    }
}

public class LRU_Cache {
    int capacity;
    Map<Integer, DLL> cache;
    DLL head;
    DLL tail;

    public LRU_Cache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new DLL(-1, -1);
        tail = new DLL(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int getKey(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        DLL node = cache.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            DLL oldNode = cache.get(key);
            remove(oldNode);
        }

        DLL node = new DLL(key, value);
        cache.put(key, node);
        add(node);

        if (cache.size() > capacity) {
            DLL nodeToDelete = head.next;
            remove(nodeToDelete);
            cache.remove(nodeToDelete.key);
        }
    }

    private void add(DLL node) {
        DLL previousNode = tail.prev;
        previousNode.next = node;
        node.prev = previousNode;
        node.next = tail;
        tail.prev = node;

    }

    private void remove(DLL node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

    }

    public static void main(String[] args) {

    }
}

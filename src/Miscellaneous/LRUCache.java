package Miscellaneous;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private final Map<Integer, Integer> cacheMap;
    private final LinkedList<Integer> lruList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.lruList = new LinkedList<>();
    }

    public int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
        lruList.remove(Integer.valueOf(key));
        lruList.addFirst(key);
        return cacheMap.get(key);
    }

    public void put(int key, int value) {
        if (cacheMap.containsKey(key)) {
            cacheMap.put(key, value);
            lruList.remove(Integer.valueOf(key));
        } else {
            if (cacheMap.size() >= capacity) {
                int leastUsedKey = lruList.removeLast();
                cacheMap.remove(leastUsedKey);
            }
            cacheMap.put(key, value);
        }
        lruList.addFirst(key);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println( lruCache.get(4));
    }
}

package LinkedList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LFUCache {

    private final int capacity;
    private int minFrequency;

    private final Map<Integer, Integer> cache; // key -> value
    private final Map<Integer, Integer> counts; // key -> freq count
    private final Map<Integer, LinkedList<Integer>> lists; // freq -> linked list of keys

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;
        this.cache = new HashMap<>();
        this.counts = new HashMap<>();
        this.lists = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        updateFrequency(key);
        return cache.get(key);
    }

    private void updateFrequency(int key) {
        int count = counts.get(key);
        counts.put(key, count + 1);
        lists.get(count).remove((Integer) key);
        if (count == minFrequency && lists.get(count).isEmpty()) {
            minFrequency++;
        }
        lists.computeIfAbsent(count + 1, k -> new LinkedList<>()).add(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        // Case 1: Key already exists, update value and frequency
        if (cache.containsKey(key)) {
            cache.put(key, value);
            updateFrequency(key);
            return;
        }
        // Case 2: Cache capacity is reached, evict LFU element
        if (cache.size() >= capacity) {
            int evictKey = lists.get(minFrequency).removeFirst();
            cache.remove(evictKey);
            counts.remove(evictKey);
        }
        // Case 3: Insert new element
        cache.put(key, value);
        counts.put(key, 1);
        minFrequency = 1;
        lists.computeIfAbsent(1, k -> new LinkedList<>()).add(key);
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        System.out.println(lfuCache.get(1));
        lfuCache.put(3, 3);
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(3));
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(4));
    }
}

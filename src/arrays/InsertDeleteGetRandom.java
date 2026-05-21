package arrays;

import java.util.*;

public class InsertDeleteGetRandom {

    private List<Integer> list;
    private Map<Integer, Integer> hm;
    private Random random;

    public InsertDeleteGetRandom() {
        list = new ArrayList<>();
        hm = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (hm.containsKey(val)) {
            return false;
        }

        // Map stores the value and its position (index) in the list
        hm.put(val, list.size());
        list.add(val); // Appending to the end of ArrayList takes O(1)
        return true;
    }

    public boolean remove(int val) {
        if (!hm.containsKey(val)) {
            return false;
        }
        // Get the index of the element to be removed
        int indexToRemove = hm.get(val);
        int lastElement = list.get(list.size() - 1);

        // Move the last element into the place of the element to be removed
        list.set(lastElement, indexToRemove);

        // Remove the last element from both structures
        list.remove(list.size() - 1); // Removing the last element takes O(1)
        hm.remove(val);
        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    public static void main(String[] args) {
        InsertDeleteGetRandom insertDeleteGetRandom = new InsertDeleteGetRandom();

    }
}

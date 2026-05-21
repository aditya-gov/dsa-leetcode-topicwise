package greedy;

import java.util.PriorityQueue;

public class RearrangeCharacters {

    private static final int MAX_CHAR = 26;

    static class Key {
        int freq;
        char ch;

        public Key(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }
    }

    public static void rearrangeString(String str) {
        int n = str.length();
        int[] count = new int[MAX_CHAR];
        for (int i = 0; i < n; i++) {
            count[str.charAt(i) - 'a']++;
        }

        PriorityQueue<Key> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.freq, a.freq));

        for (char c = 'a'; c <= 'z'; c++) {
            int val = c - 'a';
            if (count[val] > 0) {
                pq.add(new Key(count[val], c));
            }
        }

        StringBuilder sb = new StringBuilder();

        Key prev = new Key(-1, '#');
        while (!pq.isEmpty()) {
            Key k = pq.poll();
            sb.append(k.ch);
            if (prev.freq > 0) {
                pq.add(prev);
            }
            k.freq--;
            prev = k;
        }
        if (n != sb.length()) {
            System.out.println(" Not possible ");
        } else {
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        String str = "bbaa";
        rearrangeString(str);
    }

}

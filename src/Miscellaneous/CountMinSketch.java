package Miscellaneous;

import java.util.Random;

public class CountMinSketch {

    // The core 2D structure
    private final int depth;  // Number of hash functions (rows)
    private final int width;  // Number of buckets (columns)
    private final long[][] table; // The counters

    // Coefficients for Universal Hashing
    private final long[] hashA;
    private final long[] hashB;

    // A large prime number used for the hashing math
    private static final long PRIME = 2147483587L;

    /**
     * Initializes the Count-Min Sketch structure.
     * * @param depth The number of independent hash functions.
     * @param width The number of buckets per hash function.
     * @param seed  A random seed to ensure deterministic but varied hash generation.
     */
    public CountMinSketch(int depth, int width, int seed) {
        this.depth = depth;
        this.width = width;
        this.table = new long[depth][width];

        this.hashA = new long[depth];
        this.hashB = new long[depth];

        // Generate unique linear coefficients (a and b) for each row
        Random random = new Random(seed);
        for (int i = 0; i < depth; i++) {
            // 'a' must be strictly positive, 'b' can be zero
            hashA[i] = random.nextInt(Integer.MAX_VALUE - 1) + 1;
            hashB[i] = random.nextInt(Integer.MAX_VALUE);
        }
    }

    /**
     * The internal hashing mechanism.
     * Formula: (a * x + b) % prime % width
     */
    private int hash(String item, int row) {
        long baseHash = item.hashCode();

        long result = ((hashA[row] * baseHash + hashB[row]) % PRIME) % width;

        // Handle potential negative modulo results in Java
        if (result < 0) {
            result += width;
        }

        return (int) result;
    }

    /**
     * Inserts an item into the sketch, incrementing its count.
     */
    public void add(String item) {
        for (int i = 0; i < depth; i++) {
            int col = hash(item, i);
            table[i][col]++;
        }
    }

    /**
     * Queries the sketch for the estimated frequency of an item.
     */
    public long estimate(String item) {
        long minCount = Long.MAX_VALUE;

        for (int i = 0; i < depth; i++) {
            int col = hash(item, i);
            minCount = Math.min(minCount, table[i][col]);
        }

        return minCount;
    }

    public static void main(String[] args) {
        // Create a sketch with 5 rows (hashes) and 100 columns (buckets)
        CountMinSketch cms = new CountMinSketch(5, 100, 42);

        // Simulate a data stream inserting tokens
        System.out.println("Processing data stream...");

        // Add "apple" 10 times
        for (int i = 0; i < 10; i++) {
            cms.add("apple");
        }

        // Add "banana" 3 times
        for (int i = 0; i < 3; i++) {
            cms.add("banana");
        }

        // Add some noise (other words)
        cms.add("orange");
        cms.add("grape");
        cms.add("mango");

        // Query the estimates
        System.out.println("--- Frequency Estimates ---");
        System.out.println("apple: " + cms.estimate("apple") + " (True count: 10)");
        System.out.println("banana: " + cms.estimate("banana") + " (True count: 3)");
        System.out.println("orange: " + cms.estimate("orange") + " (True count: 1)");
        System.out.println("kiwi: " + cms.estimate("kiwi") + " (True count: 0)");
    }
}

package Trie;

public class LongestCommonPrefixLength {

    static class TrieNode {
        TrieNode[] child;
        boolean isEnd;
        public TrieNode() {
            child = new TrieNode[10];
            isEnd = false;
        }
        void put(TrieNode newNode, char ch) {
            child[ch - '0'] = newNode;
        }
        boolean containsKey(char ch) {
            return child[ch - '0'] != null;
        }
        TrieNode get(char ch) {
            return child[ch - '0'];
        }
    }

    private final TrieNode root;

    public LongestCommonPrefixLength() {
        root = new TrieNode();
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        for (int num : arr2) {
            insert(String.valueOf(num));
        }
        int[] maxLen = {0};
        for (int num : arr1) {
            lcpUtil(String.valueOf(num), maxLen);
        }
        return maxLen[0];
    }

    private void lcpUtil(String num, int[] maxLen) {
        TrieNode current = root;
        int len = 0;
        for (char ch : num.toCharArray()) {
            if (!current.containsKey(ch)) {
                return;
            }
            len++;
            maxLen[0] = Math.max(maxLen[0], len);
            current = current.get(ch);
        }
    }

    private void insert(String num) {
        TrieNode current = root;
        for (char ch : num.toCharArray()) {
            if (!current.containsKey(ch)) {
                current.put(new TrieNode(), ch);
            }
            current = current.get(ch);
        }
        current.isEnd = true;
    }

    public static void main(String[] args) {
        LongestCommonPrefixLength sol = new LongestCommonPrefixLength();
        int[] arr1 = {1, 10, 100};
        int[] arr2 = {1000};
        System.out.println(sol.longestCommonPrefix(arr1, arr2));
    }

}

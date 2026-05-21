package Trie;

public class LongestCommonPrefix {

    private final TrieNode root;

    public LongestCommonPrefix() {
        root = new TrieNode();
    }

    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if (n == 0) {
            return "";
        }
        if (n == 1) {
            return strs[0];
        }
        this.insert(strs);
        return this.longestCommonPrefixUtil();
    }

    public void insert(String[] strs) {
        for (String str : strs) {
            TrieNode current = root;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
                if (current.child[index] == null) {
                    current.child[index] = new TrieNode();
                    current.child[index].ch = str.charAt(i);
                }
                current = current.child[index];
            }
            current.isEnd = true;
        }
    }

    public String longestCommonPrefixUtil() {
        TrieNode current = root;
        StringBuilder sb = new StringBuilder();
        while (true) {
            int count = 0;
            TrieNode next = null;
            for (int i = 0; i < 26; i++) {
                if (current.child[i] != null) {
                    count++;
                    next = current.child[i];
                }
            }
            if (count != 1 || current.isEnd) {
                break;
            }
            sb.append(next.ch);
            current = next;
        }
        return sb.toString();
    }

    static class TrieNode {
        TrieNode[] child;
        boolean isEnd;
        char ch;
        TrieNode() {
            child = new TrieNode[26];
            isEnd = false;
        }
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        System.out.println("Longest common prefix: " + longestCommonPrefix.longestCommonPrefix(strs));
    }
}





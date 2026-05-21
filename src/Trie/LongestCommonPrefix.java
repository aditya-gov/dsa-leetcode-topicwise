package dp;

public class LongestCommonPrefix {

    TrieNode root;

    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if (n == 0 || strs == null) {
            return "";
        }
        if (n == 1) {
            return strs[0];
        }
        this.insert(strs);
        return this.search(strs[0], n);
    }

    public void insert(String[] strs) {
        TrieNode current = root;
        for (String str : strs) {
            for (char ch : str.toCharArray()) {
                if (!current.containsKey(ch)) {
                    current.put(ch, new TrieNode());
                }
                current.get(ch).count++;
                current = current.get(ch);
            }
            current.isEnd = true;
        }
    }

    public String search(String str, int n) {
        TrieNode current = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (current.get(ch) != null && current.get(ch).count == n) {
                current = current.get(ch);
            } else {
                return str.substring(0, i);
            }
        }
        return str;
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        System.out.println("Longest common prefix: " + longestCommonPrefix.longestCommonPrefix(strs));
    }

    class TrieNode {
        TrieNode[] child;
        boolean isEnd;
        int count = 0;

        public boolean containsKey(char ch) {
            return child[ch - 'a'] != null;
        }
        public TrieNode get(char ch) {
            return child[ch - 'a'];
        }
        public void put(char ch, TrieNode node) {
            child[ch - 'a'] = node;
        }
    }

}



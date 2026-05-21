package Trie;

public class ImplementTrie {

    static class TrieNode {
        TrieNode[] child;
        boolean isEnd;

        public TrieNode() {
            child = new TrieNode[26];
            isEnd = false;
        }
    }

    TrieNode root;

    public ImplementTrie() {
        root = new TrieNode();
    }

    public void insert(String key) {
        TrieNode curr = root;
        for (char ch : key.toCharArray()) {
            if (curr.child[ch - 'a'] == null) {
                curr.child[ch - 'a'] = new TrieNode();
            }
            curr = curr.child[ch - 'a'];
        }
        curr.isEnd = true;
    }

    public boolean search(String key) {
        TrieNode curr = root;
        for (char ch : key.toCharArray()) {
            if (curr.child[ch - 'a'] == null) {
                return false;
            }
            curr = curr.child[ch - 'a'];
        }
        return curr.isEnd;
    }

    public boolean isPrefix(String prefix) {
        TrieNode curr = root;
        for (char ch : prefix.toCharArray()) {
            if (curr.child[ch - 'a'] == null) {
                return false;
            }
            curr = curr.child[ch - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        ImplementTrie trie = new ImplementTrie();
        String[] arr = {"and", "ant", "do", "dad", "band"};
        for (String s : arr) {
            trie.insert(s);
        }
        String[] searchKeys = { "do", "gee", "bat", "and" };
        for (String key : searchKeys) {
            if (trie.search(key)) {
                System.out.println(key + " true");
            } else {
                System.out.println(key + " false");
            }
        }
        System.out.println(trie.isPrefix("ban"));
    }
}



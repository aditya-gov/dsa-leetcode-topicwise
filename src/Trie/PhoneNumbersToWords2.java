package Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumbersToWords2 {

    private static Map<Character, String> keypadMappings;
    private static List<String> knownWords;
    private static Map<Character, Character> letterToDigitMappings;

    public PhoneNumbersToWords2() {
        keypadMappings = new HashMap<>();
        knownWords = new ArrayList<>();
        letterToDigitMappings = new HashMap<>();
        keypadMappings.put('2', "abc");
        keypadMappings.put('3', "def");
        keypadMappings.put('4', "ghi");
        keypadMappings.put('5', "jkl");
        keypadMappings.put('6', "mno");
        keypadMappings.put('7', "pqrs");
        keypadMappings.put('8', "tuv");
        keypadMappings.put('9', "wxyz");
        knownWords.add("careers");
        knownWords.add("linkedin");
        knownWords.add("hiring");
        knownWords.add("interview");
        knownWords.add("linkedgo");
        for (Map.Entry<Character, String> entry : keypadMappings.entrySet()) {
            for (char letter : entry.getValue().toCharArray()) {
                letterToDigitMappings.put(letter, entry.getKey());
            }
        }
    }

    public List<String> findPossibleWords(String phoneNumber) {
        TrieNode current = buildTrie();
        for (char digit : phoneNumber.toCharArray()) {
            if (!current.children.containsKey(digit)) {
                return new ArrayList<>();
            }
            current = current.children.get(digit);
        }
        return current.words;
    }

    static class TrieNode {
        Map<Character, TrieNode> children;
        List<String> words;
        TrieNode() {
            children = new HashMap<>();
            words = new ArrayList<>();
        }
    }

    private TrieNode buildTrie() {
        TrieNode root = new TrieNode();
        for (String word : knownWords) {
            TrieNode current = root;
            for (char letter : word.toCharArray()) {
                char digit = letterToDigitMappings.get(letter);
                if (digit == '0') {
                    continue;
                }
                if (!current.children.containsKey(digit)) {
                    current.children.put(digit, new TrieNode());
                }
                current = current.children.get(digit);
            }
            current.words.add(word);
        }
        return root;
    }

    public static void main(String[] args) {
        PhoneNumbersToWords2 sol = new PhoneNumbersToWords2();
        String phoneNumber = "2273377";
        System.out.println(sol.findPossibleWords(phoneNumber));
    }
}

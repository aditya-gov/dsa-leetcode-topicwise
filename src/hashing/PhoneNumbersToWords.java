package hashing;

import java.util.*;

public class PhoneNumbersToWords {

    private static final Map<Character, Character> charDigitMap = new HashMap<>();

    static {
        charDigitMap.put('a', '2');
        charDigitMap.put('b', '2');
        charDigitMap.put('c', '2');
        charDigitMap.put('d', '3');
        charDigitMap.put('e', '3');
        charDigitMap.put('f', '3');
        charDigitMap.put('g', '4');
        charDigitMap.put('h', '4');
        charDigitMap.put('i', '4');
        charDigitMap.put('j', '5');
        charDigitMap.put('k', '5');
        charDigitMap.put('l', '5');
        charDigitMap.put('m', '6');
        charDigitMap.put('n', '6');
        charDigitMap.put('o', '6');
        charDigitMap.put('p', '7');
        charDigitMap.put('q', '7');
        charDigitMap.put('r', '7');
        charDigitMap.put('s', '7');
        charDigitMap.put('t', '8');
        charDigitMap.put('u', '8');
        charDigitMap.put('v', '8');
        charDigitMap.put('w', '9');
        charDigitMap.put('x', '9');
        charDigitMap.put('y', '9');
        charDigitMap.put('z', '9');

    }

    private static String wordToDigits(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toLowerCase().toCharArray()) { // O(word.size())
            if (charDigitMap.containsKey(c)) {
                sb.append(charDigitMap.get(c)); // O(1)
            } else {
                return "";
            }
        }
        return sb.toString();
    }

    public static List<String> getMatchingWords(String phNum, List<String> knownWords) {
        List<String> result = new ArrayList<>();
        for (String word : knownWords) {
            if (phNum.equals(wordToDigits(word))) {
                result.add(word);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> KNOWN_WORDS = Arrays.asList("careers", "linkedin", "hiring", "interview", "linkedgo");
        String phoneNumber1 = "2273377";
        System.out.println("Matching words: " + getMatchingWords(phoneNumber1, KNOWN_WORDS));

    }

}

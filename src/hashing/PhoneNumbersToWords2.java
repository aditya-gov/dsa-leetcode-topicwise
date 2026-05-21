package hashing;

import java.util.*;

public class PhoneNumbersToWords2 {

    private static Map<Character, String> keypadMappings;
    private static List<String> knownWords;

    public PhoneNumbersToWords2() {
        keypadMappings = new HashMap<>();
        knownWords = new ArrayList<>();
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
    }

    public List<String> findPossibleWords(String phoneNumber) {
        List<String> res = new ArrayList<>();
        for (String word : knownWords) {
            if (isPossible(phoneNumber, word)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean isPossible(String phoneNumber, String word) {
        for (int i = 0; i < phoneNumber.length(); i++) {
            char digit = phoneNumber.charAt(i);
            char letter = word.charAt(i);
            String mappedLetters = keypadMappings.get(digit);
            if (mappedLetters == null || mappedLetters.indexOf(letter) == -1) {
                return false;
            }
        }
        return true;
    }

    private char getDigitForLetter(char letter) {
        for (Map.Entry<Character, String> en : keypadMappings.entrySet()) {
            if (en.getValue().indexOf(letter) != -1) {
                return en.getKey();
            }
        }
        return '0';
    }

    public static void main(String[] args) {
        PhoneNumbersToWords2 sol = new PhoneNumbersToWords2();
        String phoneNumber = "2273377";
        System.out.println(sol.findPossibleWords(phoneNumber));
    }
}

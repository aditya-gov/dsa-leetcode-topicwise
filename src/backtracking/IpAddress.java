package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by
 * inserting dots into s
 *
 * Input: s = "25525511135" // 101023
 * Output: ["255.255.11.135","255.255.111.35"]
 *
 *  // 1.01.02.3 -- incorrect
 *
 *  // 0 - 255
 *
 *  // 256 -- incorrect
 *
 *  // IPv4
 *
 *  // segments to check & divide
 *
 *  // each segment should be checked with 0 as prefix -- denied
 */

public class IpAddress {

    public static List<String> generateIpAddress(String s) {
        if (s == null || s.isEmpty()) {
            return new ArrayList<>() {};
        }
        int n = s.length();
        List<String> validIps = new ArrayList<>();
        String temp = "";
        generateIpAddressUtil(s, 0, n - 1, 1, temp, validIps);
        return validIps;
    }

    // 25525511135
    private static void generateIpAddressUtil(String s, int i, int j, int pos, String temp, List<String> validIps) {
        // i = 0, j = n - 1
        // base condition with pos = 4 + 1 & dot
        if (i == j + 1 && pos == 5) {
            validIps.add(temp.substring(1));
        }

        for (int k = i; k < i + 3 && k <= j; k++) {
            String subStr = s.substring(i, k + 1); // segment
            if ((s.charAt(i) == '0' && subStr.length() > 1)
                || Integer.valueOf(subStr) > 255) { // 0 - 255
                return;
            }
            //
            generateIpAddressUtil(s, k + 1, j, pos + 1, temp + "." + subStr, validIps);
        }
    }

    public static void main(String[] args) {
        //String s = "101023"; // [1.0.10.23, 1.0.102.3, 10.1.0.23, 10.10.2.3, 101.0.2.3]
        //String s = "0000"; // [0.0.0.0]
        String s = "25525511135"; // [255.255.11.135, 255.255.111.35]
        System.out.println(generateIpAddress(s));
    }
}
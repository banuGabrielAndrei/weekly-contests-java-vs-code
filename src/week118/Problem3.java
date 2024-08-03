package week118;

import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        String longestPalindrome = "";

        for (int i = 0; i < word.length(); ++i) {
            for (int j = i + 1; j < word.length(); ++j) {
                String sub = word.substring(i, j);
                if (isPalindrome(sub) && sub.length() > longestPalindrome.length()) {
                    longestPalindrome = sub;
                }
            }
        }
        scanner.close();
        if (isPalindrome(word)) {
            System.out.println(word);
        } else {
            System.out.println(longestPalindrome);
        }

    }

    public static boolean isPalindrome(String word) {
        StringBuffer wrd = new StringBuffer(word.toLowerCase());
        return wrd.toString().equals(wrd.reverse().toString());
    }
}

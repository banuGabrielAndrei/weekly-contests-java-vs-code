package week131;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int nrWords = scanner.nextInt();
        scanner.nextLine();
        List<String> words = new ArrayList<>();
        for (int i = 0; i < nrWords; ++i) {
            String word = scanner.nextLine();
            words.add(word);
        }
        scanner.close();
        int maxLength = Integer.MIN_VALUE;
        String result = "";
        for (String wd : words) {
            if (isPalindrome(wd)) {
                if (wd.length() > maxLength ||
                        wd.length() == maxLength && wd.compareTo(result) == -1) {
                    maxLength = wd.length();
                    result = wd;
                }
            }
        }
        System.out.println(result);
    }

    public static boolean isPalindrome(String word) {
        StringBuilder palWord = new StringBuilder(word).reverse();
        return palWord.toString().equals(word);
    }
}

package week93;

import java.io.File;
import java.util.Scanner;

public class Problem3 {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();
            // System.out.println(text);
            if (text.equals("aa")) {
                break;
            }
            var newText = new StringBuilder();
            newText.append(text);
            for (int i = 0; i < newText.length(); ++i) {
                char currentChar = newText.charAt(i);
                if (!isVowel(currentChar) && Character.isLetter(currentChar)) {
                    Character.toLowerCase(currentChar);
                    char newChar = (char) ('z' - (Character.toLowerCase(currentChar) - 'a'));
                    newText.setCharAt(i, newChar);
                }
            }

            System.out.println(newText);
        }
        scanner.close();
    }

    public static boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(String.valueOf(ch));
    }

    public static char nrVowel(char ch) {
        if (ch == 'a' || ch == 'A') {
            return '1';
        } else if (ch == 'e' || ch == 'E') {
            return '2';
        } else if (ch == 'i' || ch == 'I') {
            return '3';
        } else if (ch == 'o' || ch == 'O') {
            return '4';
        } else {
            return '5';
        }
    }
}

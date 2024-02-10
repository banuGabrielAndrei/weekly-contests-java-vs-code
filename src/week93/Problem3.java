package week93;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {

    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("C:\\VS CODE  JAVA\\week 92\\src\\week93\\text.in"));
        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();
            var newText = new StringBuilder();
            newText.append(text);
            for (int i = 0; i < newText.length(); ++i) {
                if (Character.isLetter(newText.charAt(i))) {
                    if (isVowel(newText.charAt(i))) {
                        newText.setCharAt(i, nrVowel(newText.charAt(i)));
                    } else {
                        char aux;
                        if (Character.isUpperCase(newText.charAt(i))) {
                            aux = (char) ('Z' - (Character.toUpperCase(newText.charAt(i)) - 'A'));
                        } else {
                            aux = (char) ('z' - (Character.toLowerCase(newText.charAt(i)) - 'a'));
                        }
                        newText.setCharAt(i, Character.toLowerCase(aux));
                    }
                }
            }
            if (!newText.isEmpty()) {
                System.out.println(newText);
            }
        }
        scanner.close();
    }

    public static boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(String.valueOf(ch));
    }

    public static boolean isUpper(char ch) {
        return 'A' <= ch && ch <= 'Z';
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
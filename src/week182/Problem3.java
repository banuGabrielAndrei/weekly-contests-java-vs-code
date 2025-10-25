package week182;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\input.txt"));
        List<StringBuilder> result = new ArrayList<>();
        while (scanner.hasNextLine()) {
            StringBuilder line = new StringBuilder(scanner.nextLine());
            result.add(line);
        }
        scanner.close();
        result = processAllLines(result);
        for(StringBuilder line : result) {
            System.out.println(line);
        }
    }

    public static boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(String.valueOf(ch));
    }

    public static StringBuilder processVowels(StringBuilder str) {
        for(int i = 0; i < str.length(); ++i) {
            if(isVowel(str.charAt(i))) {
                str.setCharAt(i, vowelDigitValue(str.charAt(i)));
            }
        }
        return str;
    }

    public static char vowelDigitValue(char ch) {
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

    public static StringBuilder processInverseCharacters(StringBuilder str) {
        for(int i = 0; i < str.length(); ++i) {
            if (!isVowel(str.charAt(i))) {
                str.setCharAt(i, inverseCharacters(str.charAt(i)));
            }
        }
        return str;
    }

    public static char inverseCharacters(char ch) {
        if (Character.isLetter(ch)) {
            if (Character.isUpperCase(ch)) {
                return (char)('z' - (ch - 'A'));
            } else {
                return (char)('z' - (ch - 'a'));
            }
        }
        return ch;
    }

    public static List<StringBuilder> processAllLines(List<StringBuilder> lines) {
        for(StringBuilder line : lines) {
            processVowels(line);
            processInverseCharacters(line);
        }
        return lines;
    }
}
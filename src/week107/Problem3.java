package week107;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week107\\input.txt"));
        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();
            String res = processText(text);
            System.out.println(res);
        }
        scanner.close();
    }

    public static String processText(String text) {
        StringBuilder result = new StringBuilder();
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < text.length(); ++i) {
            char ch = text.charAt(i);
            if (isLetter(ch)) {
                currentWord.append(ch);
            } else {
                if (currentWord.length() > 0) {
                    result.append(ProcessWord(currentWord.toString()));
                    currentWord.setLength(0);
                }
                result.append(ch);
            }
        }
        if (currentWord.length() > 0) {
            result.append(ProcessWord(currentWord.toString()));
        }

        return result.toString();
    }

    public static String ProcessWord(String word) {
        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < sb.length() - 1; ++i) {
            if (i % 2 == 0 && isLetter(sb.charAt(i))) {
                if (isLetter(sb.charAt(i + 1))) {
                    swapLetters(sb, i, i + 1);
                } else {
                    sb.setCharAt(i, '0');
                }
            }
        }
        if (sb.length() > 0 && sb.length() % 2 == 1 && isLetter(sb.charAt(sb.length() - 1))) {
            sb.setCharAt(sb.length() - 1, '0');
        }
        return sb.toString();
    }

    public static boolean isLetter(char ch) {
        return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
    }

    public static void swapLetters(StringBuilder sb, int pos1, int pos2) {
        char aux = sb.charAt(pos1);
        sb.setCharAt(pos1, sb.charAt(pos2));
        sb.setCharAt(pos2, aux);
    }
}

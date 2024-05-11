package week96;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week96\\input.txt"));
        int sentences = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().toLowerCase();
            line += "/";
            String aux = "";
            for (int i = 0; i < line.length(); ++i) {
                if (isLetter(line.charAt(i)) && !isVowel(line.charAt(i))) {
                    aux += line.charAt(i);
                } else {
                    if (!aux.isEmpty()) {
                        System.out.print(mostFreqConsonant(aux) + " ");
                    }
                    aux = "";
                }
                if (line.charAt(i) == '.' || line.charAt(i) == '!' || line.charAt(i) == '?') {
                    System.out.println();
                    ++sentences;
                }
            }

        }
        System.out.println(sentences);
        scanner.close();
    }

    public static boolean isLetter(char ch) {
        return 'a' <= ch && ch <= 'z';
    }

    public static boolean isVowel(char ch) {
        String vowels = "aeiou";
        return vowels.contains(String.valueOf(ch));
    }

    public static String mostFreqConsonant(String text) {
        int[] lettersFreq = new int['z' + 1];
        char consonant = 'b';
        for (int i = 0; i < text.length(); ++i) {
            if (isLetter(text.charAt(i)) && !isVowel(text.charAt(i))) {
                ++lettersFreq[text.charAt(i)];
            }
            if (lettersFreq[text.charAt(i)] > lettersFreq[consonant]
                    || lettersFreq[text.charAt(i)] == lettersFreq[consonant] && text.charAt(i) < consonant) {
                consonant = text.charAt(i);
            }
        }
        return String.valueOf(consonant);
    }
}

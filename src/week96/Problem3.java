package week96;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week96\\input.txt"));
        int sentenceCount = 0;
        String line = "";
        while (scanner.hasNextLine()) {
            line += scanner.nextLine();
            line += " ";
        }
        scanner.close();
        String[] sentences = line.split("[.!?]");
        for (String sentence : sentences) {
            if (!sentence.trim().isEmpty()) {
                ++sentenceCount;
                String[] words = sentence.trim().split("\\s+");
                for (int i = 0; i < words.length; ++i) {
                    String aux = words[i];
                    System.out.print(findMostFrequentConsonant(aux) + " ");
                }
                System.out.println();
            }
        }
        System.out.println(sentenceCount);
    }

    public static char findMostFrequentConsonant(String word) {
        int[] fr = new int[26];
        char mostFreq = 'b';
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (isLetter(ch) && !isVowel(ch)) {
                ++fr[Character.toLowerCase(ch) - 'a'];
                if (fr[Character.toLowerCase(ch) - 'a'] > fr[Character.toLowerCase(mostFreq) - 'a']
                        || fr[Character.toLowerCase(ch) - 'a'] == fr[Character.toLowerCase(mostFreq) - 'a']
                                && ch < mostFreq) {
                    mostFreq = ch;
                }
            }
        }
        return mostFreq;
    }

    public static Boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(String.valueOf(ch));
    }

    public static Boolean isLetter(char ch) {
        return Character.isLetter(ch);
    }
}

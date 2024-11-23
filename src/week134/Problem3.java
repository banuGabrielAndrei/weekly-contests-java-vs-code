package week134;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\input.txt"));
        List<String> words = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            words.add(line);
        }
        scanner.close();
        List<String> longestWords = longestWords(words);
        String first = longestWords.get(0);
        String second = longestWords.get(1);
        if (onlyDistinctLetters(first) && !onlyDistinctLetters(second)) {
            System.out.println(first);
        } else if (!onlyDistinctLetters(first) && onlyDistinctLetters(second)) {
            System.out.println(second);
        } else if (!onlyDistinctLetters(first) && !onlyDistinctLetters(second)) {
            if (first.compareTo(second) <= 0) {
                System.out.println(first);
            } else {
                System.out.println(second);
            }
        } else if (onlyDistinctLetters(first) && onlyDistinctLetters(second)) {
            if (first.compareTo(second) >= 0) {
                System.out.println(first);
            } else {
                System.out.println(second);
            }
        }

    }

    public static List<String> longestWords(List<String> words) {
        String firstWord = "";
        String secondWord = "";
        for (String wrd : words) {
            if (wrd.length() > firstWord.length()) {
                secondWord = firstWord;
                firstWord = wrd;
            } else if (wrd.length() >= secondWord.length()) {
                secondWord = wrd;
            }
        }
        return Arrays.asList(firstWord, secondWord);
    }

    public static boolean onlyDistinctLetters(String word) {
        int[] fr = new int['z' + 1];
        for (int i = 0; i < word.length(); ++i) {
            ++fr[word.charAt(i)];
        }
        for (int i = 0; i < 'z' + 1; ++i) {
            if (fr[i] > 1) {
                return false;
            }
        }
        return true;
    }
}

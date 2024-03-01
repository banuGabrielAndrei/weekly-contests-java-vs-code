package week95;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("C:\\VS CODE  JAVA\\week 92\\src\\week95\\input.txt"));
        String text = "";
        while (scanner.hasNextLine()) {
            text += scanner.nextLine();
            text += " ";
        }
        scanner.close();
        String[] words = text.split(" ");
        String prefixX = words[0].toLowerCase();
        String sufix = reversedString(prefixX);
        int maxLengthWord = 0;
        int counter = 0;
        String maxWord = "";
        for (int i = 1; i < words.length; ++i) {
            if (words[i].length() >= prefixX.length() + sufix.length() - 1) {
                String word = words[i].toLowerCase();
                if (word.indexOf(prefixX) == 0 && word.lastIndexOf(sufix) == word.length() - sufix.length()) {
                    ++counter;
                    if (word.length() > maxLengthWord) {
                        maxLengthWord = word.length();
                        maxWord = words[i];
                    }
                }
            }
        }
        System.out.println(counter);
        System.out.println(maxWord);
    }

    public static String reversedString(String word) {
        var aux = new StringBuilder(word);
        aux.reverse();
        return aux.toString();
    }
}
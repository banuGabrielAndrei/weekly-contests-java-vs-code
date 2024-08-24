package week121;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src\\input.txt"));
        String aux = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line += " ";
            for (int i = 0; i < line.length(); ++i) {
                if (Character.isLetter(line.charAt(i))) {
                    aux += line.charAt(i);
                } else {
                    if (!aux.isEmpty()) {
                        if (isAlternating(aux)) {
                            System.out.print(aux + " ");
                        }
                    }
                    aux = "";
                }
            }
        }
        scanner.close();
    }

    public static boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(String.valueOf(ch));
    }

    public static boolean isAlternating(String word) {
        if (word.length() == 1) {
            return true;
        }
        for (int i = 1; i < word.length(); ++i) {
            if (isVowel(word.charAt(i)) == isVowel(word.charAt(i - 1))) {
                return false;
            }
        }
        return true;
    }
}

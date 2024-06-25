package week111;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week111\\input.txt"));
        String text = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            text += line;
        }
        text += ".";
        boolean res = false;
        String[] words = text.split("[^a-zA-Z]+");
        String result = "";
        for (String word : words) {
            if (!word.isEmpty()) {
                if (isValid(word)) {
                    result = word;
                    res = true;
                }
            }
        }
        if (!res) {
            System.out.println("Oaia a fost mancata de lup!");
        } else {
            System.out.println(result);
        }
        scanner.close();
    }

    public static boolean isValid(String word) {
        int[] fr = new int['z' + 1];
        for (int i = 0; i < word.length(); ++i) {
            if (Character.isLetter(word.charAt(i))) {
                ++fr[Character.toLowerCase(word.charAt(i))];
            }
        }
        for (int j = 0; j < 'z' + 1; ++j) {
            if (fr[j] > 1) {
                return false;
            }
        }
        return true;
    }
}

package week108;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week108\\input.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(modifiedLine(line));
        }
        scanner.close();
    }

    public static String modifiedLine(String text) {
        text += '.';
        StringBuilder line = new StringBuilder(text);
        int start = -1;
        String aux = "";
        for (int i = 0; i < line.length(); ++i) {
            if (isLetter(line.charAt(i))) {
                if (start == -1) {
                    start = i;
                }
                aux += line.charAt(i);
            } else if (start != -1) {
                if (validWord(aux)) {
                    StringBuilder rev = new StringBuilder(aux).reverse();
                    line.replace(start, i, rev.toString());
                }
                start = -1;
                aux = "";
            }
        }
        line.deleteCharAt(line.length() - 1);
        return line.toString();
    }

    public static boolean validWord(String word) {
        int[] fr = new int['z' + 1];
        for (int i = 0; i < word.length(); ++i) {
            ++fr[Character.toLowerCase(word.charAt(i))];
        }
        for (int i = 0; i < 'z' + 1; ++i) {
            if (fr[i] > 0 && fr[i] != 2) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLetter(char ch) {
        return 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z';
    }
}

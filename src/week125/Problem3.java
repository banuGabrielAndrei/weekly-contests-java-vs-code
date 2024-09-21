package week125;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\input.txt"));
        String text = "";
        while (scanner.hasNextLine()) {
            text += scanner.nextLine();
            text += "\n";
        }
        scanner.close();
        System.out.println(modifiedText(text));
    }

    public static String modifiedText(String text) {
        StringBuilder txt = new StringBuilder();
        String[] words = text.split("(?i)caps");
        boolean isUpper = false;
        for (int i = 0; i < words.length; ++i) {
            if (!isUpper) {
                txt.append(words[i].toLowerCase());
            } else {
                txt.append(words[i].toUpperCase());
            }
            if (i < words.length - 1) {
                txt.append(" ");
            }
            isUpper = !isUpper;
        }
        return txt.toString();
    }
}
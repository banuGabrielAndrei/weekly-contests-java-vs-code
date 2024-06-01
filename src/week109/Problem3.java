package week109;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week109\\input.txt"));
        String text = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            text += line;
        }
        boolean haveLetters = false;
        scanner.close();
        int[] fr = new int['z' + 1];
        for (int i = 0; i < text.length(); ++i) {
            if (Character.isLetter(text.charAt(i))) {
                ++fr[Character.toLowerCase(text.charAt(i))];
                haveLetters = true;
            }
        }
        if (haveLetters == false) {
            System.out.println("NU AVEM GROPI!");
        } else {
            boolean[] found = new boolean['z' + 1];
            for (int j = text.length() - 1; j >= 0; --j) {
                if (Character.isLetter(text.charAt(j)) && !found[Character.toLowerCase(text.charAt(j))]) {
                    System.out.print(text.charAt(j) + " ");
                    found[Character.toLowerCase(text.charAt(j))] = true;
                }
            }
        }
    }
}

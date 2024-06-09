package week110;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week110\\input.txt"));
        String digits = scanner.nextLine();
        String text = "";
        int[] frDigits = new int[10];
        int[] frText = new int[10];
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            text += line;
        }
        scanner.close();
        text += ".";
        for (int i = 0; i < digits.length(); ++i) {
            ++frDigits[digits.charAt(i) - '0'];
        }
        int max = Integer.MIN_VALUE;
        int biggestNr = 0;
        for (int j = 0; j < 10; ++j) {
            if (frDigits[j] >= max) {
                max = frDigits[j];
                biggestNr = j;
            }
        }
        int maxi = Integer.MIN_VALUE;
        int bigText = 0;

        for (int k = 0; k < text.length(); ++k) {
            if (isDigit(text.charAt(k))) {
                ++frText[text.charAt(k) - '0'];
            }
        }
        for (int l = 0; l < 10; ++l) {
            if (frText[l] >= maxi) {
                maxi = frText[l];
                bigText = l;
            }
        }
        boolean isPossibleNrX = false;
        int counter = 0;
        for (int k = 0; k < 10; ++k) {
            if (frDigits[k] > 0 && frText[k] >= frDigits[k]) {
                ++counter;
            }
            if (counter == digits.length()) {
                isPossibleNrX = true;
                break;
            }
        }
        if (isPossibleNrX) {
            StringBuilder inverse = new StringBuilder(digits);
            System.out.println(inverse.reverse().toString());
        } else {
            System.out.print(biggestNr + " " + bigText);
        }
    }

    public static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }
}

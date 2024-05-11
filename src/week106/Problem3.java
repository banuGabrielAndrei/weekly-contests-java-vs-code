package week106;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week106\\input.txt"));
        double res = 0;
        int number = 0;
        boolean divide = false;
        boolean multiply = true;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line += '.';
            for (int i = 0; i < line.length(); ++i) {
                if (isDigit(line.charAt(i))) {
                    number = number * 10 + line.charAt(i) - '0';
                } else {
                    res += number;
                    if (i + 1 < line.length() && line.charAt(i + 1) == '/') {
                        divide = true;
                    }
                    if (i + 1 < line.length() && line.charAt(i + 1) == '*') {
                        multiply = true;
                    }
                }

            }

        }
    }

    public static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }
}

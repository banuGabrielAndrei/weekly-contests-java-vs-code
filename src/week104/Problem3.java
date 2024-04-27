package week104;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week104\\input.txt"));
        int counterLines = 0;
        int number = 0;
        boolean result = false;
        int maxi = Integer.MAX_VALUE;
        int start = 0;
        int stop = 0;
        int firstPos = 0;
        int lastPos = 0;
        int line = 0;
        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();
            if (!text.isEmpty()) {
                ++counterLines;
                text += ".";
                for (int i = 0; i < text.length(); ++i) {
                    if (isDigit(text.charAt(i))) {
                        if (start == 0) {
                            start = i;
                        }
                        number = number * 10 + text.charAt(i) - '0';
                    } else {
                        if (start != 0) {
                            stop = i;
                            if (treeDigits(number) || fourDigits(number)) {
                                result = true;
                                if (number <= maxi) {
                                    maxi = number;
                                    line = counterLines;
                                    firstPos = start;
                                    lastPos = stop;
                                }
                            }
                            number = 0;
                        }
                    }
                }
            }
            System.out.println(firstPos + " " + lastPos + " " + text);
        }
        scanner.close();
        if (result == false) {
            System.out.println("NU EXISTA!");
        } else {
            System.out.println(line);
            System.out.println(firstPos + " " + lastPos);
        }
    }

    public static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    public static boolean treeDigits(int nr) {
        return 100 <= nr && nr <= 999;
    }

    public static boolean fourDigits(int nr) {
        return 1000 <= nr && nr <= 9999;
    }
}

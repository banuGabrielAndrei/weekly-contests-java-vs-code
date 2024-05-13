package week104;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week104\\input.txt"));
        int nrLines = 0;
        int startPos = -1;
        int lastPos = 0;
        int resLine = 0;
        int minStart = 0;
        int maxi = 10000;
        boolean nrExists = false;
        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();
            long nr = 0;
            if (text.length() > 0) {
                text += '.';
                ++nrLines;
                for (int i = 0; i < text.length(); ++i) {
                    if (isDigit(text.charAt(i))) {
                        if (startPos == -1) {
                            startPos = i;
                        }
                        nr = nr * 10 + text.charAt(i) - '0';
                    } else {
                        if (isThreeDigits(nr) || isFourDigits(nr)) {
                            nrExists = true;
                            if (nr <= maxi) {
                                maxi = (int) nr;
                                lastPos = i - 1;
                                resLine = nrLines;
                                minStart = startPos;
                            }

                        }
                        startPos = -1;
                        nr = 0;
                    }
                }
            }
        }
        scanner.close();
        if (!nrExists) {
            System.out.println("NU EXISTA!");
        } else {
            System.out.println(resLine);
            System.out.println(minStart + " " + lastPos);
        }
    }

    public static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    public static boolean isThreeDigits(long nr) {
        return 100 <= nr && nr <= 999;
    }

    public static boolean isFourDigits(long nr) {
        return 1000 <= nr && nr <= 9999;
    }
}

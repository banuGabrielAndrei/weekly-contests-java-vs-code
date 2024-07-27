package week117;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\input.txt"));
        String digit = "";
        boolean exists = false;
        StringBuilder res = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line += " ";
            boolean isNegative = false;
            for (int i = 0; i < line.length(); ++i) {
                if (Character.isDigit(line.charAt(i))) {
                    if (i > 0 && line.charAt(i - 1) == '-') {
                        isNegative = true;
                    }
                    digit += line.charAt(i);

                } else {
                    if (!digit.isEmpty()) {
                        if (!digit.startsWith("0")) {
                            if (isNegative) {
                                res.append('-').append(digit).append(" ");
                                exists = true;
                            } else {
                                res.append(digit).append(" ");
                                exists = true;
                            }
                        }
                        digit = "";
                        isNegative = false;
                    }
                }
            }
        }
        scanner.close();
        if (exists) {
            System.out.println(res);
        } else {
            System.out.println("NU NE JUCAM AZI!");
        }
    }
}

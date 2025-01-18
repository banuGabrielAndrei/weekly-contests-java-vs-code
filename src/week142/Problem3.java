package week142;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\input.txt"));
        int sum = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            line += ".";
            sum += lineSum(line);
        }
        scanner.close();
        System.out.println(sum);
    }

    public static int lineSum(String line) {
        boolean isNegative = false;
        int sum = 0;
        int result = 0;
        for (int i = 0; i < line.length(); ++i) {
            char c = line.charAt(i);
            if (i > 0 && line.charAt(i - 1) == '-' && Character.isDigit(c)) {
                isNegative = true;
            }
            while (Character.isDigit(c)) {
                sum = sum * 10 + c - '0';
                c = line.charAt(++i);
            }
            if (isNegative) {
                sum = -sum;
                isNegative = false;
            }
            result += sum;
            if (!Character.isDigit(c)) {
                sum = 0;
            }
        }
        return result;
    }
}

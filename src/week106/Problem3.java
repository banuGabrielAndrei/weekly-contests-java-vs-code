package week106;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week106\\input.txt"));
        Pattern pattern = Pattern.compile("(\\d+)\\s([/*])\\s(\\d+)");
        double result = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                int nr1 = Integer.parseInt(matcher.group(1));
                int nr2 = Integer.parseInt(matcher.group(3));
                char sign = matcher.group(2).charAt(0);
                result = computeResult(nr1, nr2, sign);
                System.out.println(result * 6);
            }
        }

        scanner.close();
    }

    public static double computeResult(int nr1, int nr2, char ch) {
        if (ch == '/') {
            return (double) nr1 / nr2;
        }
        return (double) nr1 * nr2;
    }
}
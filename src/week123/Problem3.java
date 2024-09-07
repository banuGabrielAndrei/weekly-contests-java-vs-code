package week123;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\input.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line += " ";
            System.out.println(changeLine(line));
        }

        scanner.close();
    }

    public static String changeLine(String line) {
        StringBuilder ln = new StringBuilder(line);
        for (int i = 1; i < ln.length(); ++i) {
            if (Character.isLetter(ln.charAt(i))) {
                if (i > 0 && ln.charAt(i - 1) == ln.charAt(i)) {
                    ln.deleteCharAt(i - 1);
                    --i;
                }
                if (i < ln.length() - 1 && ln.charAt(i + 1) == ln.charAt(i)) {
                    ln.deleteCharAt(i + 1);
                    --i;
                }
            }
        }
        return ln.toString();
    }
}

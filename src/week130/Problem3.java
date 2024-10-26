package week130;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\input.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.print(bothHalfsPresent(line));
        }
        scanner.close();
    }

    public static char bothHalfsPresent(String line) {
        char result = 'x';
        for (int i = 0; i < line.length() / 2; ++i) {
            char first = line.charAt(i);
            for (int j = line.length() / 2; j < line.length(); ++j) {
                if (line.charAt(j) == first) {
                    result = line.charAt(j);
                    break;
                }
            }
        }
        return result;
    }
}

package week98;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\VS CODE  JAVA\\week 92\\src\\week98\\input.txt"));
        int pos = 1;
        int maxi = 0;
        int result = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                ++pos;
            } else if (line.length() > 0) {
                String[] words = line.split("\\*+");
                if (words.length > maxi) {
                    maxi = words.length;
                    result = pos;
                }
            }
        }
        scanner.close();
        System.out.println(result);
    }
}

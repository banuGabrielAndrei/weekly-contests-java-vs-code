package week98;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src\\week98\\input.txt"));
        int pos = 1;
        int maxPos = 0;
        int maxi = 0;
        boolean emptyLine = false;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() == 0 && !emptyLine) {
                ++pos;
                emptyLine = true;
            } else if (line.length() > 0) {
                emptyLine = false;
                int nrWords = nrOfWords(line);
                if (nrWords > maxi) {
                    maxi = nrWords;
                    maxPos = pos;
                }
            }
        }
        scanner.close();
        System.out.println(maxPos);
    }

    public static int nrOfWords(String line) {
        int result = 1;
        boolean isWord = false;
        for (int i = 0; i < line.length(); ++i) {
            if (line.charAt(i) != '*' && !isWord) {
                isWord = true;
            } else if (line.charAt(i) == '*' && isWord) {
                isWord = false;
                ++result;
            }
        }
        return result;
    }
}

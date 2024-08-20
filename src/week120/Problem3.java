package week120;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src\\input.txt"));
        List<String> lines = new ArrayList<>();
        int maxi = 0;
        int rows = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ++rows;
            if (line.length() > maxi) {
                maxi = line.length();
            }
            lines.add(line);
        }
        scanner.close();
        char[][] mt = new char[rows + 1][maxi];
        for (int i = 1; i <= rows; ++i) {
            String word = lines.get(i - 1);
            for (int j = 0; j < word.length(); ++j) {
                mt[i][j] = word.charAt(j);
            }
        }
        int startI = 0;
        int startJ = 0;
        int stopI = 0;
        int stopJ = 0;
        int length = 0;
        int maxLength = 0;
        boolean exists = false;
        boolean firstFound = false;
        int currI = 0;
        int currJ = 0;
        for (int i = 1; i <= rows; ++i) {
            for (int j = 0; j < maxi; ++j) {
                if (isConsonant(mt[i][j])) {
                    exists = true;
                    if (!firstFound) {
                        currI = i;
                        currJ = j;
                        firstFound = true;
                    }
                    ++length;
                } else {
                    if (firstFound) {
                        if (length >= maxLength) {
                            maxLength = length;
                            startI = currI;
                            startJ = currJ;
                            stopI = i;
                            stopJ = j - 1;
                        }
                        length = 0;
                        firstFound = false;
                        currI = 0;
                        currJ = 0;
                    }
                }
            }
        }
        if (exists) {
            System.out.println(startI + " " + startJ + " " + stopI + " " + stopJ);
        } else {
            System.out.println("NU AVEM!");
        }
    }

    public static boolean isConsonant(char ch) {
        String vowels = "aeiouAEIOU";
        return Character.isLetter(ch) && !vowels.contains(String.valueOf(ch));
    }
}

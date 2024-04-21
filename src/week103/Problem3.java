package week103;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {

        int counter = 0;
        Scanner scannerInt = new Scanner(System.in);
        int lines = scannerInt.nextInt();
        int consonants = scannerInt.nextInt();

        Scanner scanner = new Scanner(new File("src\\week103\\input.txt"));
        boolean paragraphIsValid = true;
        int nrLines = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                ++nrLines;
                int nrConsonants = countConsonants(line);
                if (nrConsonants < consonants) {
                    paragraphIsValid = false;
                }
            } else {
                if (nrLines >= lines && paragraphIsValid) {
                    ++counter;
                }
                paragraphIsValid = true;
                nrLines = 0;
            }
        }
        if (nrLines >= lines && paragraphIsValid) {
            ++counter;
        }
        scannerInt.close();
        scanner.close();
        System.out.println(counter);
    }

    public static int countConsonants(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); ++i) {
            char ch = line.charAt(i);
            if (isLetter(ch) && !isVowel(ch)) {
                ++count;
            }
        }
        return count;
    }

    public static boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(String.valueOf(ch));
    }

    public static boolean isLetter(char ch) {
        return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
    }
}

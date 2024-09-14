package week124;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\input.txt"));
        String text = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            text += line;
            text += " ";
        }
        scanner.close();
        Set<Character> lowerCase = new HashSet<>();
        Set<Character> upperCase = new HashSet<>();
        for (int i = 0; i < text.length(); ++i) {
            if (Character.isLetter(text.charAt(i))) {
                if (Character.isLowerCase(text.charAt(i))) {
                    lowerCase.add(text.charAt(i));
                } else if (Character.isUpperCase(text.charAt(i))) {
                    upperCase.add(text.charAt(i));
                }
            }
        }
        int diffLowercase = 26 - lowerCase.size();
        int diffUppercase = 26 - upperCase.size();
        int missingNrOfLetters = diffLowercase + diffUppercase;
        if (missingNrOfLetters > 0) {
            System.out.println(missingNrOfLetters);
        } else {
            System.out.println("PANGRAMA!");
        }
    }
}

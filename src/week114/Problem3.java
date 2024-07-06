package week114;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException{
        var scanner = new Scanner(new File("src\\week114\\input.txt"));
        int counter = 0;
        String word = "";
        int index = 0;
        String[] words = new String[1001];
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ++counter;
            if (counter == 1) {
                word += line;
            } else {
               words[++index] = line; 
            }
        }
        scanner.close();
        boolean exists = true;
        for (int i = 1; i <= index; ++i) {
            String aux = words[i];
            String[] wrds = aux.split("[^a-zA-Z]+");
            for (int j = 0; j < wrds.length; ++j) {
                if (wrds[j].length() == word.length()) {
                    if (samePattern(wrds[j], word)) {
                        exists = true;
                        System.out.print(wrds[j] + " ");
                    }
                }
            }
        }
        if (!exists) {
            System.out.println("COSMIN SI-A LUAT TEAPA!");
        }
    }

    public static boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(String.valueOf(ch));
    }

    public static boolean samePattern(String a, String b) {
        for (int i =0; i < a.length(); ++i) {
            if (isVowel(a.charAt(i)) && !isVowel(b.charAt(i)) || isVowel(b.charAt(i)) && !isVowel(a.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

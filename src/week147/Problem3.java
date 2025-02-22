package week147;

import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String virus = scanner.nextLine();
        String word = scanner.nextLine();
        scanner.close();
        System.out.println(newWord(virus, word));
    }

    public static String newWord(String virus, String word) {
        while (word.contains(virus)) {
            word = word.replace(virus, "");
        }
        return word;
    }
}

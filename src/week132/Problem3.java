package week132;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        char x = scanner.next().charAt(0);
        char y = scanner.next().charAt(0);
        char z = scanner.next().charAt(0);
        int n = scanner.nextInt();
        List<String> words = new ArrayList<>();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String word = scanner.nextLine();
            words.add(word);
        }
        scanner.close();
        for (String word : words) {
            if (isValid(word, x, y, z)) {
                System.out.println(word);
            }
        }
    }

    public static boolean isValid(String word, char x, char y, char z) {
        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) != x && word.charAt(i) != y && word.charAt(i) != z) {
                return false;
            }
        }
        return true;
    }
}

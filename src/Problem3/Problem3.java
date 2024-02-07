package Problem3;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        scanner.close();
        ArrayList<String> words = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < text.length(); ++i) {
            if (isUpperCase(text.charAt(i))) {
                word.append(text.charAt(i));
                for (int j = i + 1; j < text.length() && !isUpperCase(text.charAt(j)); ++j) {
                    word.append(text.charAt(j));
                }
                words.add(word.toString());
            }
            word.setLength(0);
        }
        for (int i = 0; i < words.size() / 2; ++i) {
            System.out.print(words.get(words.size() - 1 - i) + " " + words.get(i) + " ");
        }
    }

    public static boolean isUpperCase(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }
}
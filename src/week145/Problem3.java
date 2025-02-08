package week145;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int lines = scanner.nextInt();
        Map<String, String> words = new HashMap<>();
        for (int i = 0; i < lines; ++i) {
            String k = scanner.next();
            String v = scanner.next();
            words.put(k, v);
        }
        scanner.nextLine();
        String sentence = scanner.nextLine();
        scanner.close();
        System.out.println(translatedText(sentence, words));
    }

    public static String translatedText(String sentence, Map<String, String> words) {
        String[] romanianWords = sentence.split(" ");
        StringBuilder newText = new StringBuilder();
        for (String word : romanianWords) {
            if (words.containsKey(word)) {
                newText.append(words.get(word)).append(" ");
            }
        }
        return newText.toString();
    }
}
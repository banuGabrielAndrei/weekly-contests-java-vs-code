package week97;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week97\\input.txt"));
        ArrayList<String> text = new ArrayList<>();
        while (scanner.hasNextLine()) {
            text.add(scanner.nextLine());
        }
        scanner.close();
        Pattern pattern = Pattern.compile("\\b[a-z]+(?:_[a-z]+)+\\b");
        for (int i = text.size() - 1; i >= 0; --i) {
            Matcher matcher = pattern.matcher(text.get(i));
            while (matcher.find()) {
                String result = matcher.group();
                System.out.print(toCamelCase(result) + " ");
                // System.out.println(result);
            }
        }
    }

    public static String toCamelCase(String snakeCase) {
        String[] words = snakeCase.split("_");
        String camelCase = "";
        camelCase += words[0];
        for (int i = 1; i < words.length; ++i) {
            camelCase += Character.toUpperCase(words[i].charAt(0));
            camelCase += words[i].substring(1);
        }
        return camelCase;
    }
}
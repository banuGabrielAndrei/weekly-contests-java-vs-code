package week92;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week92\\input.txt"));
        String text = scanner.nextLine();
        scanner.close();
        text += ".";
        List<String> words = new ArrayList<>();
        String aux = "";
        for (int i = 0; i < text.length(); ++i) {
            if (isUpperCase(text.charAt(i))) {
                aux += text.charAt(i);
                for (int j = i + 1; j < text.length() && !isUpperCase(text.charAt(j)) && text.charAt(j) != '.'; ++j) {
                    aux += text.charAt(j);
                }
            }
            if (!aux.isEmpty()) {
                words.add(aux);
                aux = "";
            }
            aux = "";

        }
        for (int k = 0, l = words.size() - 1; k <= l; ++k, --l) {
            System.out.print(words.get(l) + " ");
            if (k != l) {
                System.out.print(words.get(k) + " ");
            }
        }

    }

    public static boolean isUpperCase(char ch) {
        return 'A' <= ch && ch <= 'Z';
    }
}

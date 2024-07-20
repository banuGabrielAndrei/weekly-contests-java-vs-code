package week116;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\input.txt"));
        List<String> text = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line += " ";
            text.add(line);
        }
        scanner.close();
        for (int i = 0; i < text.size(); ++i) {
            StringBuilder txt = new StringBuilder();
            String words = text.get(i);
            String aux = "";
            for (int j = 0; j < words.length(); ++j) {
                if (Character.isLetter(words.charAt(j))) {
                    aux += words.charAt(j);
                } else {
                    if (!aux.isEmpty()) {

                        txt.append(modString(aux));
                        aux = "";
                    }
                    txt.append(words.charAt(j));
                }
            }
            text.set(i, txt.toString());
        }
        for (String result : text) {
            System.out.println(result);
        }
    }

    public static boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(String.valueOf(ch));
    }

    public static String modString(String word) {
        StringBuilder wrd = new StringBuilder(word);
        boolean firstVowel = false;
        char vowel = ' ';
        for (int i = 0; i < wrd.length(); ++i) {
            if (isVowel(wrd.charAt(i)) && !firstVowel) {
                vowel = Character.toLowerCase(wrd.charAt(i));
                firstVowel = true;
            }
        }
        if (firstVowel) {
            for (int j = wrd.length() - 1; j >= 0; --j) {
                if (Character.toLowerCase(wrd.charAt(j)) == vowel) {
                    wrd.deleteCharAt(j);
                    break;
                }
            }
        } else {
            char consonant = Character.toLowerCase(wrd.charAt(wrd.length() - 1));
            for (int k = 0; k < wrd.length(); ++k) {
                if (Character.toLowerCase(wrd.charAt(k)) == consonant) {
                    wrd.deleteCharAt(k);
                    break;
                }
            }
        }
        return wrd.toString();
    }
}

package week101;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week101\\input.txt"));
        Map<String, String> subject = new HashMap<>();
        Map<String, String> predicate = new HashMap<>();
        Map<String, String> adverb = new HashMap<>();
        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();
            String[] words = text.split("-");
            if (words[0].toLowerCase().equals("subiect")) {
                subject.put(words[0], words[1]);
            } else if (words[0].toLowerCase().equals("predicat")) {
                predicate.put(words[0], words[1]);
            } else {
                adverb.put(words[0], words[1]);
            }
        }
        scanner.close();
        System.out.println(maxLexico(subject) + " " + maxLexico(predicate) + " " + maxLexico(adverb));
    }

    public static String maxLexico(Map<String, String> map) {
        String result = "";
        String maxLexico = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().toLowerCase().compareTo(maxLexico) > 0) {
                maxLexico = entry.getValue().toLowerCase();
                result = entry.getValue();
            }
        }
        return result;
    }
}

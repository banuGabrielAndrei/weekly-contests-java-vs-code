package week100;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem3 {

    static String vowels = "aeiou";

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        Map<String, Integer> noteCnt = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int i = 1; i < line.length(); ++i) {
                if (isVowel(line.charAt(i - 1)) && isVowel(line.charAt(i))
                        && (i == line.length() - 1 || !isLetter(line.charAt(i + 1)))
                        && (i == 1 || !isLetter(line.charAt(i - 2)))) {
                    String aux = "";
                    aux += line.charAt(i - 1);
                    aux += line.charAt(i);
                    noteCnt.put(aux, noteCnt.getOrDefault(aux, 0) + 1);
                }
            }
        }
        scanner.close();
        System.out.println(mostFreqNote(noteCnt));
    }

    public static boolean isVowel(char ch) {
        return vowels.contains(String.valueOf(ch));
    }

    public static boolean isLetter(char ch) {
        return 'a' <= ch && ch <= 'z';
    }

    public static String mostFreqNote(Map<String, Integer> noteCnt) {
        int maxi = 0;
        String freqNote = "a";
        for (Map.Entry<String, Integer> entry : noteCnt.entrySet()) {
            int count = entry.getValue();
            String note = entry.getKey();
            if (count > maxi || (count == maxi && note.compareTo(freqNote) < 0)) {
                maxi = count;
                freqNote = note;
            }
        }
        return freqNote;
    }
}

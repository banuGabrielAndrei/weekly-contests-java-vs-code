package week102;

import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String[] words = new String[2001];
        int index = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line += ".";
            String aux = "";
            for (int i = 0; i < line.length(); ++i) {
                if (isLetter(line.charAt(i))) {
                    aux += line.charAt(i);
                } else {
                    if (!aux.isEmpty()) {
                        words[++index] = aux;
                        aux = "";
                    }
                }
            }
        }
        scanner.close();
        for (int i = 1; i < index; ++i) {
            for (int j = i + 1; j <= index; ++j) {
                char maxCharI = maxChar(words[i]);
                char maxCharJ = maxChar(words[j]);
                if (maxCharI == maxCharJ) {
                    if (words[j].compareTo(words[i]) > 0) {
                        String temp = words[i];
                        words[i] = words[j];
                        words[j] = temp;
                    }
                } else if (maxCharJ > maxCharI) {
                    String temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                }
            }
        }
        for (int i = 1; i <= index; ++i) {
            System.out.print(words[i] + " ");
        }
    }

    public static boolean isLetter(char ch) {
        return 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z';
    }

    public static char maxChar(String word) {
        char maxChar = Character.MIN_VALUE;
        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) > maxChar) {
                maxChar = word.charAt(i);
            }
        }
        return maxChar;
    }
}

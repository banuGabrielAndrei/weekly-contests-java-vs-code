package week94;

import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        char[][] mt = new char[a][b];
        for (int i = 0; i < a; ++i) {
            String word = scanner.next();
            for (int j = 0; j < b; ++j) {
                mt[i][j] = Character.toLowerCase(word.charAt(j));
            }
        }
        scanner.close();
        int counter = 0;
        for (int i = 1; i < a - 1; ++i) {
            for (int j = 1; j < b - 1; ++j) {
                if (isVowel(mt[i - 1][j]) && isVowel(mt[i + 1][j])
                        && isVowel(mt[i][j + 1]) && isVowel(mt[i][j - 1])
                        && mt[i - 1][j] < mt[i][j] && mt[i + 1][j] < mt[i][j]
                        && mt[i][j + 1] < mt[i][j] && mt[i][j - 1] < mt[i][j]) {
                    ++counter;
                }
            }
        }
        System.out.println(counter);
    }

    public static boolean isVowel(char ch) {
        String vowels = "aeiou";
        return vowels.contains(String.valueOf(ch));
    }
}

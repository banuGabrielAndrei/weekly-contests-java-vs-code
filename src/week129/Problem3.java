package week129;

import java.util.Arrays;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();
        String[] words1 = firstLine.split(" ");
        String[] words2 = secondLine.split(" ");

        Arrays.sort(words1);
        Arrays.sort(words2);

        for (int i = 0; i < words1.length; ++i) {
            for (int j = 0; j < words2.length; ++j) {
                System.out.println(words1[i] + " " + words2[j]);
            }
        }
        scanner.close();
    }
}

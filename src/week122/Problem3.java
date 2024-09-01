package week122;

import java.util.Arrays;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().toLowerCase();
        scanner.close();
        String[] words = line.split(" ");
        char[] sortedString1 = words[0].toCharArray();
        char[] sortedString2 = words[1].toCharArray();
        Arrays.sort(sortedString1);
        Arrays.sort(sortedString2);
        int changes = 0;
        for (int i = 0; i < sortedString1.length; ++i) {
            if (sortedString1[i] != sortedString2[i]) {
                ++changes;
            }
        }
        System.out.println(changes);
    }
}

package week103;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int[] frA = new int[10];
        int[] frB = new int[10];
        int counter = 0;
        while (a != 0) {
            ++frA[a % 10];
            a /= 10;
        }
        while (b != 0) {
            ++frB[b % 10];
            b /= 10;
        }
        for (int i = 0; i < 10; ++i) {
            if (frA[i] != 0 && frB[i] != 0) {
                if (frA[i] == frB[i]) {
                    ++counter;
                }
            }
        }
        scanner.close();
        System.out.println(counter);
    }
}

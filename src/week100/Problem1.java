package week100;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int evenCounter = 0;
        scanner.close();
        for (int i = a + 1; i < b; ++i) {
            if (i % 2 == 0) {
                ++evenCounter;
            }
        }
        System.out.println(evenCounter);
    }
}

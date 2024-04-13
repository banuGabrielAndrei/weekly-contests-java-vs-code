package week102;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int[] digits = new int[4];
        boolean sameDigits = true;
        int index = 0;
        while (a != 0) {
            digits[index] = a % 10;
            ++index;
            a /= 10;
        }
        for (int i = 0; i < digits.length - 1; ++i) {
            if (digits[i] != digits[i + 1]) {
                sameDigits = false;
                break;
            }
        }
        if (sameDigits) {
            int b = scanner.nextInt();
            System.out.println(b);
        } else {
            System.out.println(digits[2]);
        }
        scanner.close();
    }
}

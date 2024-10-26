package week130;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] digits = new int[3];
        int index = 0;
        while (n != 0) {
            digits[index] = n % 10;
            ++index;
            n /= 10;
        }
        scanner.close();
        boolean isValid = true;
        for (int i = digits.length - 1; i > 0; --i) {
            if (digits[i] != digits[i - 1] - 1) {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            System.out.println("SUNT");
        } else {
            System.out.println("NU SUNT");
        }
    }
}

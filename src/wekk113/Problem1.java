package wekk113;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int digit = scanner.nextInt();
        int[] digits = new int[3];
        int index = 0;
        digits[index] = digit;
        while (number != 0) {
            digits[++index] = number % 10;
            number /= 10;
        }
        scanner.close();
        for (int i = 0; i < index; ++i) { 
            for (int j = i + 1; j <= index; ++j) {
                if (digits[i] < digits[j]) {
                    int aux = digits[i];
                    digits[i] = digits[j];
                    digits[j] = aux;
                }
            }
        }
        for (int k = 0; k <= index; ++k) {
            System.out.print(digits[k]);
        }
    }
}

package week97;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        scanner.close();
        final int FIVE_HUNDRED = 500;
        if (FIVE_HUNDRED % number == 0) {
            int first_digit = 0;
            while (number >= 10) {
                number /= 10;
                first_digit = number;
            }
            System.out.println(first_digit);
        } else {
            System.out.println(number % 10);
        }
    }
}

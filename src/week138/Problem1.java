package week138;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] digits = new int[3];
        int index = -1;
        while (n != 0) {
            digits[++index] = n % 10;
            n /= 10;
        }
        scanner.close();
        if (digits[1] == digits[2]) {
            System.out.println("The third child is naughty");
        } else if (digits[0] == digits[2]) {
            System.out.println("The second child is naughty");
        } else if (digits[0] == digits[1]) {
            System.out.println("The first child is naughty");
        }
    }
}

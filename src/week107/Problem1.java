package week107;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int nr = scanner.nextInt();
        int counter = 0;
        Integer[] digits = new Integer[4];
        int index = 0;
        while (nr != 0) {
            digits[index] = nr % 10;
            ++index;
            nr /= 10;
        }
        int sum = digits[0] + digits[3];
        boolean cond1 = false;
        boolean cond2 = false;
        if (digits[3] % 2 != digits[2] % 2) {
            cond1 = true;
            ++counter;
        }
        if (sum == digits[1]) {
            cond2 = true;
            ++counter;
        }
        if (cond1 && cond2) {
            System.out.println("valid!");
        } else {
            System.out.println(counter);
        }
        scanner.close();
    }
}

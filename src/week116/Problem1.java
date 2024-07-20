package week116;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (b % a == 0) {
            if (a % 2 == 0) {
                System.out.println("par!");
            } else {
                System.out.println("impar!");
            }
        } else if (a % b == 0) {
            if (b % 2 == 0) {
                System.out.println("par!");
            } else {
                System.out.println("impar!");
            }
        } else {
            System.out.println(a + b);
        }
        scanner.close();
    }
}

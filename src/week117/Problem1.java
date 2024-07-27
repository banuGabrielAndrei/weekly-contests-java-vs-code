package week117;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (a % 2 == b % 2) {
            int c = scanner.nextInt();
            while (c >= 10) {
                c /= 10;
            }
            System.out.println(c);
        } else {
            System.out.println("NU!");
        }
        scanner.close();
    }
}

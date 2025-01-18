package week142;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int x = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();
        if (x == 1) {
            System.out.println(a + b);
        } else if (x == 2) {
            System.out.println(a - b);
        } else if (x == 3) {
            System.out.println(a * b);
        } else {
            System.out.println(a / b);
        }
    }
}

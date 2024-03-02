package week96;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();
        if (a == 0 || b == 0 || a == b) {
            if (a == 0) {
                System.out.println(b * 10 + a);
            } else {
                System.out.println(a * 10 + b);
            }
        } else {
            System.out.println("DA");
        }
    }
}

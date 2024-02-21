package week94;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();
        if (a >= 1 && b <= 9) {
            System.out.println("ZIGGY");
        } else {
            System.out.println(a + b);
        }
    }
}

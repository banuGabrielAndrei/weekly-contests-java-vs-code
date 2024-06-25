package week112;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if (10 <= a && a <= 99) {
            System.out.println(b + "" + a + "" + c);
        } else {
            System.out.println(b + "" + a);
        }
        scanner.close();
    }
}

package week104;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int x = scanner.nextInt();
        if (b + x >= 60) {
            System.out.println(a + 1);
        } else {
            System.out.println(a);
        }
        scanner.close();
    }
}

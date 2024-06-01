package week109;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int inverse = 0;
        int copyA = a;
        int sum = 0;
        int copyB = b;
        while (copyB != 0) {
            sum += copyB % 10;
            copyB /= 10;
        }
        while (copyA != 0) {
            inverse = inverse * 10 + copyA % 10;
            copyA /= 10;
        }
        if (a == b || inverse == b) {
            System.out.println(a);
        } else {
            System.out.println(sum);
        }
        scanner.close();
    }
}

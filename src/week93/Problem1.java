package week93;

import java.util.Scanner;

public class Problem1 {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();
        if (a == b) {
            System.out.println(a);
        } else if (a < b && (b - a) % 2 != 0) {
            System.out.println("surori");
        } else {
            if (a > b) {
                System.out.println(a);
            } else {
                System.out.println(b);
            }
        }
    }
}

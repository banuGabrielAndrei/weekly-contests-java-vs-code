package week144;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();
        if (validNumber(a)) {
            System.out.print(a + " ");
        }
        if (validNumber(b)) {
            System.out.print(b + " ");
        }
        if (validNumber(c)) {
            System.out.print(c + " ");
        }
    }

    public static boolean validNumber(int nr) {
        while (nr >= 10) {
            nr /= 10;
        }
        return nr % 2 == 0;
    }
}
package week148;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (sum(a) > sum(b)) {
            if (a < b) {
                System.out.println(a + " " + b);
            } else {
                System.out.println(b + " " + a);
            }
        } else {
            if (a > b) {
                System.out.println(a + " " + b);
            } else {
                System.out.println(b + " " + a);
            }
        }
        scanner.close();
    }

    public static int sum(int nr) {
        int sum = 0;
        while (nr != 0) {
            sum += nr % 10;
            nr /= 10;
        }
        return sum;
    }
}

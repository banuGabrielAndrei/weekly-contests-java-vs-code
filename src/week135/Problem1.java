package week135;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        if (n == 0 || n == 1 || n == 2) {
            System.out.println("NU" + " " + 3);
        } else if (n % 2 != 0) {
            int first = n - 2;
            int second = n - 1;
            System.out.println("DA" + " " + first + " " + second);
        } else {
            int res = n + 1;
            System.out.println("NU" + " " + res);
        }
    }
}

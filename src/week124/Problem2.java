package week124;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; ++i) {
            Long first = scanner.nextLong();
            int second = scanner.nextInt();
            System.out.println(printDigit(first, second));
        }
        scanner.close();
    }

    public static char printDigit(Long a, int b) {
        if (a < 0) {
            a *= -1;
        }
        String nrA = Long.toString(a);
        return nrA.charAt(b - 1);
    }
}

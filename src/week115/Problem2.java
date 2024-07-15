package week115;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int maxi = Integer.MIN_VALUE;
        while (true) {
            int value = scanner.nextInt();
            if (value == 0) {
                break;
            }
            if (value > maxi) {
                maxi = value;
            }
        }
        scanner.close();
        if (isPrime(inverse(maxi))) {
            System.out.println(inverse(maxi));
        } else {
            System.out.println("COSMIN A RAMAS FARA MASINA!");
        }
    }

    public static boolean isPrime(int nr) {
        int n = Math.abs(nr);
        if (n <= 1) {
            return false;
        }
        for (int div = 2; div <= n / 2; ++div) {
            if (n % div == 0) {
                return false;
            }
        }
        return true;
    }

    public static int inverse(int nr) {
        int n = Math.abs(nr);
        int inverse = 0;
        while (n != 0) {
            inverse = inverse * 10 + n % 10;
            n /= 10;
        }
        return inverse;
    }
}

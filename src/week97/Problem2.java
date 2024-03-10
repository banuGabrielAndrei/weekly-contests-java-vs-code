package week97;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[n];
        int counterValidNumbers = 0;
        for (int i = 0; i < n; ++i) {
            v[i] = scanner.nextInt();
            if (isValid(v[i])) {
                ++counterValidNumbers;
            }
        }
        scanner.close();
        if (counterValidNumbers == v.length) {
            System.out.println("AM CAUTAT DAR NU EXISTA!");
        } else if (counterValidNumbers > n / 2) {
            int min = 10001;
            for (int i = 0; i < n; ++i) {
                if (!isValid(v[i])) {
                    if (v[i] < min) {
                        min = v[i];
                    }
                }
            }
            System.out.println(min);
        } else if (counterValidNumbers <= n / 2) {
            System.out.println("NU SUNT SUFICIENTE!");
        }
    }

    public static boolean isValid(int nr) {
        if (nr >= 0 && nr <= 9) {
            return true;
        }
        int lastDigit = nr % 10;
        nr /= 10;
        while (nr > 0) {
            int digit = nr % 10;
            if (digit <= lastDigit) {
                return false;
            }
            lastDigit = digit;
            nr /= 10;
        }
        return true;
    }
}

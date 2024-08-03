package week118;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] v = new long[n];
        long[] w = new long[n];
        for (int i = 0; i < n; ++i) {
            v[i] = scanner.nextLong();
            if (isValid(v[i])) {
                w[i] = changeNr(v[i]);
            } else {
                w[i] = v[i];
            }
        }
        scanner.close();
        for (int j = 0; j < n; ++j) {
            System.out.print(w[j] + " ");
        }
    }

    public static boolean isValid(long nr) {
        int counter = 0;
        while (nr != 0) {
            if ((nr % 10) % 2 == 0) {
                ++counter;
            }
            nr /= 10;
        }
        return counter <= 3;
    }

    public static long changeNr(long nr) {
        String numStr = Long.toString(nr);
        int length = numStr.length();
        char[] digits = numStr.toCharArray();
        if (length % 2 == 0) {
            for (int i = 0; i < length / 2; ++i) {
                char aux = digits[i];
                digits[i] = digits[length / 2 + i];
                digits[length / 2 + i] = aux;
            }
        } else {
            for (int i = 0; i < length / 2; ++i) {
                char aux = digits[i];
                digits[i] = digits[length / 2 + 1 + i];
                digits[length / 2 + 1 + i] = aux;
            }
        }
        return Long.parseLong(new String(digits));
    }
}

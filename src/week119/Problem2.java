package week119;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] v = new long[n];
        for (int i = 0; i < v.length; ++i) {
            v[i] = scanner.nextLong();
            v[i] = changedNumber(v[i]);
            System.out.print(v[i] + " ");

        }
        scanner.close();
    }

    public static long changedNumber(long nr) {
        boolean isNegative = nr < 0;
        String number = String.valueOf(Math.abs(nr));
        char[] digits = number.toCharArray();
        char middle = ' ';
        if (digits.length % 2 == 0) {
            middle = digits[digits.length / 2 - 1];
        } else {
            middle = digits[digits.length / 2];
        }
        for (int i = 0; i < digits.length; ++i) {
            if (digits[i] == '0') {
                digits[i] = middle;
            }
        }
        long res = Long.parseLong(new String(digits));
        if (isNegative) {
            return res * -1;
        } else {
            return res;
        }
    }
}

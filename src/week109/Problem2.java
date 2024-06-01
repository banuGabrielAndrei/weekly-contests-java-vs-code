package week109;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        long[] v = new long[101];
        int index = 0;
        while (true) {
            v[index] = scanner.nextLong();
            if (sameDigits(v[index])) {
                break;
            }
            ++index;
        }
        scanner.close();
        for (int i = 0; i <= index; ++i) {
            if (validNumber(v[i])) {
                System.out.print(i + " ");
            }
        }

    }

    public static boolean sameDigits(long nr) {
        long number = Math.abs(nr);
        String digits = Long.toString(number);
        char firstDigit = digits.charAt(0);
        for (int i = 1; i < digits.length(); ++i) {
            if (digits.charAt(i) != firstDigit) {
                return false;
            }
        }
        return true;
    }

    public static boolean validNumber(Long nr) {
        int[] fr = new int[10];
        long no = Math.abs(nr);
        while (no != 0) {
            ++fr[(int) (no % 10)];
            no /= 10;
        }
        for (int j = 0; j < 10; ++j) {
            if (fr[j] > 1) {
                return false;
            }
        }
        return true;
    }
}

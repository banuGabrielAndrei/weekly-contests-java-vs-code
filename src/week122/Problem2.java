package week122;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Long[] numbers = new Long[n + 1];
        for (int i = 1; i <= n; ++i) {
            numbers[i] = scanner.nextLong();
            System.out.print(minNr(numbers[i]) + " ");
        }
        scanner.close();
    }

    public static long minNr(Long nr) {
        if (nr == 0) {
            return 0;
        }
        boolean isNegative = nr < 0;
        String newNr = "";
        int digits = nrOfDigits(nr);
        if (isNegative) {
            for (int i = 1; i <= digits; ++i) {
                newNr += "9";
            }
            return Long.parseLong(newNr) * -1;
        } else {
            if (digits == 1) {
                return 0;
            }
            newNr += "1";
            for (int i = 1; i < digits; ++i) {
                newNr += "0";
            }
            return Long.parseLong(newNr);
        }

    }

    public static int nrOfDigits(Long nr) {
        int counter = 0;
        while (Math.abs(nr) != 0) {
            nr /= 10;
            ++counter;
        }
        return counter;
    }
}
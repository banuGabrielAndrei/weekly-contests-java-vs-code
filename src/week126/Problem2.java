package week126;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] numbers = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            numbers[i] = scanner.nextLong();
        }
        scanner.close();
        boolean validNumbers = false;
        int pos = 0;
        long biggestNr = Long.MIN_VALUE;
        int maxDigits = 0;
        for (int i = 1; i < n; ++i) {
            if (allDigitsPrimes(numbers[i])) {
                validNumbers = true;
                int digits = maxPrimeDigits(numbers[i]);
                if (digits > maxDigits) {
                    maxDigits = digits;
                    biggestNr = numbers[i];
                    pos = i;
                } else if (digits == maxDigits) {
                    if (numbers[i] > biggestNr) {
                        biggestNr = numbers[i];
                        pos = i;
                    }
                } else if (numbers[i] == biggestNr && i > pos) {
                    pos = i;
                }
            }
        }
        if (!validNumbers) {
            System.out.println("AJUNGE LA DESTINATIE!");
        } else {
            System.out.println(pos);
        }
    }

    public static boolean isPrime(long nr) {
        if (nr <= 1) {
            return false;
        }
        for (int div = 2; div * div <= nr; ++div) {
            if (nr % div == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean allDigitsPrimes(long nr) {
        long num = Math.abs(nr);
        while (num != 0) {
            if (!isPrime(num % 10)) {
                return false;
            }
            num /= 10;
        }
        return true;
    }

    public static int maxPrimeDigits(long nr) {
        long num = Math.abs(nr);
        int counter = 0;
        while (num != 0) {
            ++counter;
            num /= 10;
        }
        return counter;
    }
}

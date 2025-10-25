package week182;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] values= new int[4001];
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            values[i] = scanner.nextInt();
            if (isPrime(values[i])) {
                n += values[i];
            } else {
                sum += values[i];
            }
        }
        scanner.close();
        System.out.println(sum);
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i * i <= number; ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
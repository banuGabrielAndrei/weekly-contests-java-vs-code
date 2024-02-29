package week95;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] fr = new int[10001];
        int lines = 0;
        for (int i = 1; i <= n; i += 2) {
            ++lines;
            for (int j = 1; j <= i; ++j) {
                int value = scanner.nextInt();
                if (isPrime(value)) {
                    ++fr[value];
                }
            }
        }
        scanner.close();
        int maxi = 0;
        int number = 1;
        for (int k = 2; k < 10001; ++k) {
            if (fr[k] > maxi || (fr[k] == maxi && k > number)) {
                maxi = fr[k];
                number = k;
            }
        }
        System.out.println(lines);
        System.out.println(number);
    }

    public static boolean isPrime(int nr) {
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
}
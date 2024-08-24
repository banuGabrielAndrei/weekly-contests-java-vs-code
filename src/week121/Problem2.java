package week121;

import java.util.Arrays;
import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        Integer[] v = new Integer[n + 1];
        Integer[] primes = new Integer[n + 1];

        int index = 0;
        for (int i = 1; i <= n; ++i) {
            v[i] = scanner.nextInt();
            if (isPrime(v[i])) {
                primes[++index] = v[i];
            }
        }
        scanner.close();
        Arrays.sort(primes, 1, index);
        Integer[] res = new Integer[index + 1];
        for (int i = 1; i <= x; ++i) {
            res[i] = primes[i];
        }
        for (int j = x; j >= 1; --j) {
            System.out.print(res[j] + " ");
        }
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

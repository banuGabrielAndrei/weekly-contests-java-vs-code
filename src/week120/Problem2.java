package week120;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] v = new long[n];
        int counter = 0;
        for (int i = 0; i < n; ++i) {
            v[i] = scanner.nextLong();
            if (isPseudoPerfect(v[i])) {
                ++counter;
            }
        }
        scanner.close();
        System.out.println(counter);
    }

    public static boolean isPseudoPerfect(long nr) {
        if (nr == 1) {
            return false;
        }
        long sum = 0;
        for (int i = 1; i <= nr; ++i) {
            if (nr % i == 0) {
                sum += i;
            }
        }
        return sum % nr == 0;
    }
}

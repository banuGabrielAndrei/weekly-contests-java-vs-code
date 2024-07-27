package week117;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[10001];
        int counter = 0;
        int index = 0;
        while (counter != n) {
            int nr = scanner.nextInt();
            v[++index] = nr;
            if (isPrime(nr)) {
                ++counter;
            }
        }
        scanner.close();
        int res = 0;
        for (int i = 1; i <= index; ++i) {
            if (i % 2 != 0) {
                if (isPrime(v[i]) && v[i] % 2 != 0) {
                    ++res;
                }
            }
        }
        System.out.println(res);
    }

    public static boolean isPrime(int nr) {
        if (nr <= 1) {
            return false;
        }
        for (int div = 2; div <= nr / 2; ++div) {
            if (nr % div == 0) {
                return false;
            }
        }
        return true;
    }
}

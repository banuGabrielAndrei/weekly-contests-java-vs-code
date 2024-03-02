package week96;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[n];
        for (int i = 0; i < n; ++i) {
            v[i] = scanner.nextInt();
        }
        scanner.close();
        for (int i = 0; i < n; ++i) {
            int nr = v[i];
            if (nr == 0) {
                System.out.print(nr + " ");
            } else {
                int[] fr = new int[10];
                int maxDigit = 9;
                while (nr != 0) {
                    ++fr[(int) (nr % 10)];
                    if (nr % 10 < maxDigit && nr % 10 != 0) {
                        maxDigit = nr % 10;
                    }
                    nr /= 10;
                }
                System.out.print(maxDigit);
                --fr[maxDigit];
                for (int j = 0; j < 10; ++j) {
                    for (int k = 1; k <= fr[j]; ++k) {
                        System.out.print(j);
                    }
                }
            }
            System.out.print(" ");
        }
    }
}

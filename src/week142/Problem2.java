package week142;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] v = new int[n + 1];
        int x = scanner.nextInt();
        int i = scanner.nextInt();
        int j = scanner.nextInt();
        scanner.close();
        for (int k = i; k <= j; k++) {
            v[k] = x;
        }
        if (i - 1 >= 1) {
            v[i - 1] = x + 1;
        }
        if (j + 1 <= n) {
            v[j + 1] = x - 1;
        }
        for (int k = i - 2; k >= 1; --k) {
            v[k] = v[k + 1] + 1;
        }
        for (int k = j + 2; k <= n; ++k) {
            v[k] = v[k - 1] - 1;
        }
        for (int k = 1; k <= n; ++k) {
            System.out.print(v[k] + " ");
        }
    }
}

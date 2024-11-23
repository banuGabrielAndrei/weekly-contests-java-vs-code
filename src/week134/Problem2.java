package week134;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int y = scanner.nextInt();
        int[][] mt = new int[n + 1][n + 1];
        int minEl = Integer.MAX_VALUE;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                mt[i][j] = scanner.nextInt();
                if (mt[i][j] < minEl) {
                    minEl = mt[i][j];
                }
            }
        }
        scanner.close();
        if (n == 1) {
            System.out.println(minEl);
        } else {
            int minIndex = Integer.MAX_VALUE;
            for (int i = 1; i < n; ++i) {
                for (int j = 1; j < n; ++j) {
                    if (mt[i][j] == y && mt[i][j + 1] == y
                            && mt[i + 1][j] == y
                            && mt[i + 1][j + 1] == y) {
                        minIndex = Math.min(i, minIndex);
                        // System.out.println(minIndex);
                    }
                }
            }
            if (minIndex == Integer.MAX_VALUE) {
                System.out.println(minEl);
            } else {
                System.out.println(minIndex);
            }
        }
    }
}

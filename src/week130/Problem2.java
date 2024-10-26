package week130;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] mt = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                mt[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        int magic = mt[n / 2 + 1][n / 2 + 1];
        int sum = 0;
        boolean isValid = true;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (i != n / 2 + 1 && j != n / 2 + 1) {
                    if (mt[i][j] >= magic) {
                        isValid = false;
                    }
                }
                sum += mt[i][j];
            }
        }
        if (isValid) {
            System.out.println(magic);
        } else {
            System.out.println(sum - magic);
        }
    }
}

package week137;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] mt = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                mt[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                values.add(mt[i][j]);
            }
            for (int k = i + 1; k < n; ++k) {
                values.add(mt[k][n - i - 1]);
            }
        }
        for (int val : values) {
            System.out.print(val + " ");
        }
    }
}

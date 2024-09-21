package week125;

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
        int intervals = 0;
        boolean isAscending = false;
        for (int i = 0; i < n - 1; ++i) {
            if (v[i] < v[i + 1]) {
                if (!isAscending) {
                    ++intervals;
                }
                isAscending = true;
            } else {
                isAscending = false;
            }
        }
        System.out.println(intervals);
    }
}

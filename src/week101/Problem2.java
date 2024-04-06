package week101;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int v[] = new int[n + 1];
        int k = scanner.nextInt();
        for (int i = 1; i <= n; ++i) {
            v[i] = scanner.nextInt();
        }
        scanner.close();
        int maxSum = Integer.MIN_VALUE;
        int startPos = -1;
        for (int i = 1; i <= n - k + 1; ++i) {
            int subseqSum = 0;
            for (int j = i; j < i + k; ++j) {
                subseqSum += v[j];
            }
            if (maxSum <= subseqSum) {
                maxSum = subseqSum;
                startPos = i;
            }
        }
        int lastPos = startPos + k - 1;
        System.out.println(startPos + " " + lastPos);
    }
}

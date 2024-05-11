package week106;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            v[i] = scanner.nextInt();
        }
        int minPos = 0;
        int maxPos = 0;
        int sum = 0;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < n; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                sum = v[i] + v[j];
                int diff = Math.abs(sum);
                if (diff < minDiff
                        || (diff == minDiff && (v[i] < v[minPos] || (v[i] == v[minPos] && v[j] < v[maxPos])))) {
                    minDiff = diff;
                    minPos = i;
                    maxPos = j;
                }
            }
        }
        if (v[minPos] > v[maxPos]) {
            int aux = minPos;
            minPos = maxPos;
            maxPos = aux;
        } else if (v[minPos] == v[maxPos]) {
            if (minPos > maxPos) {
                int aux = minPos;
                minPos = maxPos;
                maxPos = aux;
            }
        }
        scanner.close();
        System.out.println(minPos + " " + maxPos);
    }
}

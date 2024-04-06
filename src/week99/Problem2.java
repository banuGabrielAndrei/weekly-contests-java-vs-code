package week99;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int[] v = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            v[i] = scanner.nextInt();
        }
        scanner.close();
        int minValue = Integer.MAX_VALUE;
        int pos = 0;
        int value = -1;
        for (int i = 1; i <= n; ++i) {
            int diff = Math.abs(v[i] - x);
            if (diff < minValue || (diff == minValue && v[i] <= value)) {
                minValue = diff;
                value = v[i];
                pos = i;
            }
        }
        System.out.println(pos);
    }
}

package week110;

import java.util.Arrays;
import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[n];
        int[] sheeps = new int[n];
        for (int i = 0; i < n; ++i) {
            v[i] = scanner.nextInt();
            sheeps[i] = diff(v[i]);
        }
        scanner.close();
        Arrays.sort(sheeps);
        for (int j = 0; j < sheeps.length; ++j) {
            System.out.print(sheeps[j] + " ");
        }
    }

    public static int diff(int nr) {
        int no = Math.abs(nr);
        if (no < 10) {
            return 0;
        }
        int[] digits = new int[7];
        int index = 0;
        while (no != 0) {
            digits[++index] = no % 10;
            no /= 10;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= index; ++i) {
            if (digits[i] > max) {
                max = digits[i];
            }
            if (digits[i] < min) {
                min = digits[i];
            }
        }
        return max - min;
    }
}

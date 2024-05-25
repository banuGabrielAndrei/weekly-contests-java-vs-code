package week108;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[n + 1];
        int[] frAll = new int[10];
        for (int i = 1; i <= n; ++i) {
            v[i] = scanner.nextInt();
            int nr = Math.abs(v[i]);
            while (nr != 0) {
                ++frAll[nr % 10];
                nr /= 10;
            }
        }
        int maxi = 0;
        for (int i = 0; i < 10; ++i) {
            if (frAll[i] >= frAll[maxi]) {
                maxi = i;
            }
        }
        for (int j = 1; j <= n; ++j) {
            if (maxFreq(v[j]) == maxi) {
                System.out.print(v[j] + " ");
            }
        }
        scanner.close();
    }

    public static int maxFreq(int nr) {
        int number = Math.abs(nr);
        int[] fr = new int[10];
        int maxi = 0;
        while (number != 0) {
            ++fr[number % 10];
            number /= 10;
        }
        for (int i = 0; i < 10; ++i) {
            if (fr[i] >= fr[maxi]) {
                maxi = i;
            }
        }
        return maxi;
    }
}

package week98;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int[][] mt = new int[20][m];
        int[] fr = new int[1001];
        boolean validLines = true;
        while (validLines) {
            for (int i = 0; i < 20; ++i) {
                boolean sameDigits = true;
                for (int j = 0; j < m; ++j) {
                    mt[i][j] = scanner.nextInt();
                    ++fr[mt[i][j]];
                }
                for (int k = 0; k < m - 1; ++k) {
                    if (mt[i][k] != mt[i][k + 1]) {
                        sameDigits = false;
                    }
                }
                if (sameDigits) {
                    validLines = false;
                    break;
                }
            }
        }
        scanner.close();
        int maxi = 1;
        int mostFreq = -1;
        for (int i = 10; i <= 99; ++i) {
            if (fr[i] > maxi || (fr[i] == maxi && i % 10 > mostFreq % 10)
                    || (fr[i] == maxi && i % 10 == mostFreq % 10 && i > mostFreq)) {
                mostFreq = i;
                maxi = fr[i];
            }
        }
        System.out.println(mostFreq);
    }
}
package week144;

import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int lines = scanner.nextInt();
        int columns = scanner.nextInt();
        int querries = scanner.nextInt();
        int minTruffles = scanner.nextInt();
        int[][] matrix = new int[lines][columns];
        for (int i = 0; i < lines; ++i) {
            for (int j = 0; j < columns; ++j) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        int[] truffleswCodes = new int[querries];
        for (int i = 0; i < querries; ++i) {
            truffleswCodes[i] = scanner.nextInt();
        }
        scanner.close();
        for (int i = 0; i < querries; ++i) {
            int truffle = truffleswCodes[i];
            int allLines = 0;
            for (int j = 0; j < lines; ++j) {
                int truffleCount = 0;
                for (int k = 0; k < columns; ++k) {
                    if (matrix[j][k] == truffle) {
                        ++truffleCount;
                    }
                }
                if (truffleCount >= minTruffles) {
                    ++allLines;
                }
            }
            if (allLines == lines) {
                System.out.println("DA");
            } else {
                System.out.println("NU");
            }
        }
    }
}

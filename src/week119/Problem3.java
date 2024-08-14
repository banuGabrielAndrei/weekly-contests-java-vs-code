package week119;

import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        char[][] mt = new char[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            String line = scanner.nextLine();
            for (int j = 1; j <= n; ++j) {
                mt[i][j] = line.charAt(j - 1);
            }
        }
        scanner.close();
        boolean exists = false;
        for (int i = 1; i <= n; ++i) {
            char ch = mt[i][1];
            for (int j = i + 1; j <= n; ++j) {
                if (mt[i][j] == ch) {
                    exists = true;
                }
            }
        }
        if (!exists) {
            System.out.println("NU EXISTA!");
        }
    }
}
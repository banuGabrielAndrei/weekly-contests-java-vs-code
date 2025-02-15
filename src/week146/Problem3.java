package week146;

import java.util.Scanner;
import java.util.TreeSet;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = scanner.next().charAt(0);
            }
        }
        scanner.close();
        TreeSet<Character> result = sortedPassword(matrix, n);
        result.forEach(System.out::print);
    }

    public static TreeSet<Character> sortedPassword(char[][] matrix, int length) {
        TreeSet<Character> result = new TreeSet<>();
        for (int j = 0; j < length; ++j) {
            result.add(matrix[0][j]);
        }
        for (int i = 1; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                int value = matrix[i - 1][j] - 'a' + 1;
                int ascii = matrix[i][j] + value;
                if (ascii > 'z') {
                    matrix[i][j] = 'z';
                } else {
                    matrix[i][j] = (char) ascii;
                }
                result.add(matrix[i][j]);
            }
        }
        return result;
    }
}

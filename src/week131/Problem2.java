package week131;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] mt = new int[n][n];
        Set<Integer> values = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                mt[i][j] = scanner.nextInt();
                values.add(mt[i][j]);
            }
        }
        scanner.close();
        if (n == 1) {
            System.out.println(1);
        } else {
            List<Integer> sortedValues = new ArrayList<>(values);
            int result = 1;
            int maxLength = Integer.MIN_VALUE;
            for (int i = 0; i < sortedValues.size() - 1; ++i) {
                if (sortedValues.get(i) == sortedValues.get(i + 1) - 1) {
                    ++result;
                } else {
                    result = 1;
                }
                if (result > maxLength) {
                    maxLength = result;
                }
            }
            System.out.println(maxLength);
        }
    }
}

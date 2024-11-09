package week132;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Integer[][] mt = new Integer[n][m];
        List<Integer[]> maxRows = new ArrayList<>();
        int maxCount = 0;
        for (int i = 0; i < n; ++i) {
            Set<Integer> uniqueSameDigitNumbers = new HashSet<>();
            int counter = 0;
            for (int j = 0; j < m; ++j) {
                mt[i][j] = scanner.nextInt();
                if (sameDigits(mt[i][j]) && uniqueSameDigitNumbers.add(mt[i][j])) {
                    ++counter;
                }
            }
            if (counter > maxCount) {
                maxCount = counter;
                maxRows.clear();
                maxRows.add(mt[i]);
            } else if (counter == maxCount && counter > 0) {
                maxRows.add(mt[i]);
            }
        }

        scanner.close();
        for (Integer[] row : maxRows) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static boolean sameDigits(Integer nr) {
        String number = nr.toString();
        char first = number.charAt(0);
        for (int i = 1; i < number.length(); ++i) {
            if (number.charAt(i) != first) {
                return false;
            }
        }
        return true;
    }
}

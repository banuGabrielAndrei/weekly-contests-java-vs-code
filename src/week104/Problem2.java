package week104;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            v[i] = scanner.nextInt();
        }
        List<Integer> digits = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            int counter = 0;
            int nr = Math.abs(v[i]);
            while (nr != 0) {
                digits.add(nr % 10);
                nr /= 10;
            }
            Collections.reverse(digits);
            for (int j = 0; j < digits.size() - 2; ++j) {
                int num = digits.get(j) * 100 + digits.get(j + 1) * 10 + digits.get(j + 2);
                if (isPrime(num)) {
                    ++counter;
                }
            }
            if (counter >= 2) {
                result.add(v[i]);
            }
            digits.clear();
        }
        scanner.close();
        for (int numbers : result) {
            System.out.print(numbers + " ");
        }
    }

    public static boolean isPrime(int nr) {
        int number = Math.abs(nr);
        if (number <= 1) {
            return false;
        }
        for (int div = 2; div * div <= number; ++div) {
            if (number % div == 0) {
                return false;
            }
        }
        return true;
    }
}

package week116;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Long[] left = new Long[n];
        Long[] right = new Long[n];
        for (int i = 0; i < n; ++i) {
            left[i] = scanner.nextLong();
        }
        for (int j = 0; j < n; ++j) {
            right[j] = scanner.nextLong();
        }
        scanner.close();
        int counter = 0;
        for (int k = 0; k < n; ++k) {
            if (nrOfDigits(left[k]) == nrOfDigits(right[k])) {
                ++counter;
            }
        }
        if (counter == 0) {
            System.out.println("NUMERELE AU RAMAS SINGURE!");
        } else {
            System.out.println(counter);
        }
    }

    public static int nrOfDigits(Long nr) {
        long no = Math.abs(nr);
        int counterDigits = 0;
        if (nr == 0) {
            counterDigits = 1;
        }
        while (no != 0) {
            ++counterDigits;
            no /= 10;
        }
        return counterDigits;
    }
}

package week102;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int x = scanner.nextInt();
        int[] v = new int[101];
        int index = 0;
        int oddCounter = 0;
        int evenCounter = 0;
        while (oddCounter < k && evenCounter < k) {
            v[++index] = scanner.nextInt();
            if (isEven(v[index])) {
                ++evenCounter;
                oddCounter = 0;
            } else {
                ++oddCounter;
                evenCounter = 0;
            }
        }
        scanner.close();
        int digitsX = nrOfDigits(x);
        boolean isValid = false;
        Long sum = 0L;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= index; ++i) {
            if (nrOfDigits(v[i]) == digitsX) {
                isValid = true;
                sum += v[i];
                if (v[i] < min) {
                    min = v[i];
                }
            }
        }
        if (!isValid) {
            System.out.println(sum);
            System.out.println("NU EXISTA!");
        } else {
            System.out.println(sum);
            System.out.println(min);
        }

    }

    public static boolean isEven(int nr) {
        return nr % 2 == 0;
    }

    public static int nrOfDigits(int nr) {
        int counter = 0;
        while (nr != 0) {
            ++counter;
            nr /= 10;
        }
        return counter;
    }
}

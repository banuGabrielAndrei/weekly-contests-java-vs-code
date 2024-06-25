package week111;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] v = new long[n];
        long[] res = new long[n];
        int indexRes = 0;
        for (int i = 0; i < n; ++i) {
            v[i] = scanner.nextInt();
            if (isValidNrDigits(v[i]) && sameDigits(v[i])) {
                res[indexRes] = v[i];
                ++indexRes;
            }
        }
        for (int j = 0; j < indexRes - 1; ++j) {
            for (int k = j + 1; k < indexRes; ++k) {
                if (firstDigit(res[j]) < firstDigit(res[k]) || firstDigit(res[j]) == firstDigit(res[k]) &&
                        secondDigit(res[j]) < secondDigit(res[k]) || firstDigit(res[j]) == firstDigit(res[k])
                                && secondDigit(res[j]) == secondDigit(res[k]) && res[j] < res[k]) {
                    long aux = res[j];
                    res[j] = res[k];
                    res[k] = aux;
                }
            }

        }
        for (int l = 0; l < indexRes; ++l) {
            System.out.print(res[l] + " ");
        }
        scanner.close();
    }

    public static boolean isValidNrDigits(long nr) {
        int counter = 0;
        while (nr != 0) {
            ++counter;
            nr /= 10;
        }
        return counter % 2 == 0;
    }

    public static boolean sameDigits(long nr) {
        long[] v = new long[10];
        int index = 0;
        while (nr != 0) {
            v[index] = nr % 10;
            ++index;
            nr /= 10;
        }
        reverseArray(v, index);
        int firstHalf = 0;
        for (int i = 0; i < index / 2; ++i) {
            firstHalf = firstHalf * 10 + (int) v[i];
        }
        int secondHalf = 0;
        for (int j = index / 2; j < index; ++j) {
            secondHalf = secondHalf * 10 + (int) v[j];
        }
        return firstHalf == secondHalf;
    }

    public static void reverseArray(long[] arr, int index) {
        for (int i = 0; i < index / 2; ++i) {
            long aux = arr[i];
            arr[i] = arr[index - i - 1];
            arr[index - i - 1] = aux;
        }
    }

    public static long firstDigit(long nr) {
        long aux = nr;
        while (aux >= 10) {
            aux /= 10;
        }
        return aux;
    }

    public static long secondDigit(long nr) {
        long aux = nr;
        while (aux >= 100) {
            aux /= 10;
        }
        return aux % 10;
    }

}

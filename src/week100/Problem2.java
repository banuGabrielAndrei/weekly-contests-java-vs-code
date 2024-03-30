package week100;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[n];
        int min = 1001;
        int max = -min;
        int posMin = 0;
        int posMax = 0;
        for (int i = 0; i < n; ++i) {
            v[i] = scanner.nextInt();
            if (v[i] > max) {
                max = v[i];
                posMax = i;
            }
            if (v[i] < min) {
                min = v[i];
                posMin = i;
            }
        }
        scanner.close();
        if (posMin > posMax) {
            int aux = posMin;
            posMin = posMax;
            posMax = aux;

        }
        for (int j = posMin + 1; j < posMax; ++j) {
            if (v[j] < 0) {
                int nr = Math.abs(v[j]);
                if (isPrime(nr)) {
                    v[j] = inverseNr(nr) * -1;
                }
            } else {
                if (isPrime(v[j])) {
                    v[j] = inverseNr(v[j]);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            System.out.print(v[i] + " ");
        }
    }

    public static boolean isPrime(int nr) {
        if (nr <= 1) {
            return false;
        }
        for (int div = 2; div * div <= nr; ++div) {
            if (nr % div == 0) {
                return false;
            }
        }
        return true;
    }

    public static int inverseNr(int nr) {
        int inverse = 0;
        while (nr != 0) {
            inverse = inverse * 10 + nr % 10;
            nr /= 10;
        }
        return inverse;
    }
}

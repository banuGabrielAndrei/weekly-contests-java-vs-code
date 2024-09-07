package week123;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] v = new long[n];
        int odds = 0;
        int evens = 0;
        for (int i = 0; i < n; ++i) {
            v[i] = scanner.nextLong();
            long aux = Math.abs(v[i]);
            if (aux == 0) {
                ++evens;
            }
            while (aux != 0) {
                if (aux % 2 == 0) {
                    ++evens;
                } else {
                    ++odds;
                }
                aux /= 10;
            }
        }
        scanner.close();
        int supremeV = 0;
        if (odds == evens) {
            supremeV = 2;
        }
        if (odds > evens) {
            supremeV = 1;
        }
        boolean exists = false;
        for (int i = 0; i < n; ++i) {
            if (supremeVal(v[i]) != supremeV) {
                exists = true;
            }
        }
        if (!exists) {
            System.out.println("numerele s-au suparat!");
        } else {
            for (int j = 0; j < n; ++j) {
                if (supremeVal(v[j]) != supremeV) {
                    System.out.print(v[j] + " ");
                }
            }
        }
    }

    public static int supremeVal(long nr) {
        int even = 0;
        int odd = 0;
        long aux = Math.abs(nr);
        while (aux != 0) {
            if (aux % 2 == 0) {
                ++even;
            } else {
                ++odd;
            }
            aux /= 10;
        }
        if (odd == even) {
            return 2;
        } else if (even > odd) {
            return 0;
        } else {
            return 1;
        }
    }
}

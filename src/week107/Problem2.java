package week107;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[n + 1];
        int[] result = new int[n + 1];
        int resIndex = 0;
        boolean notExists = true;
        for (int i = 1; i <= n; ++i) {
            boolean good = false;
            boolean valid = true;
            int index = 0;
            int[] fr = new int[10];
            int[] digits = new int[11];
            v[i] = scanner.nextInt();
            int nr = Math.abs(v[i]);
            while (nr != 0) {
                digits[index] = nr % 10;
                ++index;
                ++fr[nr % 10];
                nr /= 10;
            }
            for (int j = 0; j < 10; ++j) {
                if (fr[j] > 0) {
                    if (fr[j] != 2) {
                        valid = false;
                        break;
                    }
                }
            }
            // System.out.println(valid);

            if (valid) {
                boolean ok = true;
                for (int k = 0; k < index - 3; k += 2) {
                    if (digits[k] != digits[k + 1]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    good = true;
                }
            }

            if (good) {
                result[++resIndex] = i;
                notExists = false;
            }
        }
        if (notExists) {
            System.out.println("NU FACEM CUMPARATURI!");
        } else {
            for (int l = resIndex; l > 0; --l) {
                System.out.print(result[l] + " ");
            }
        }

        scanner.close();
    }
}

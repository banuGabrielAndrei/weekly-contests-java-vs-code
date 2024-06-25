package week112;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[n];
        int[] res = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            v[i] = scanner.nextInt();
        }
        int index = 0;
        for (int i = 0; i < n; ++i) {
            if (isPalindrome(v[i])) {
                int copyNr = 0;
                while (v[i] != 0) {
                    if ((v[i] % 10) % 2 == 0) {
                        copyNr = copyNr * 10 + 0;
                    } else {
                        copyNr = copyNr * 10 + v[i] % 10;
                    }
                    v[i] /= 10;
                    
                }
                res[++index] = copyNr;
            } else {
                res[++index] = v[i];
            }
        }
        scanner.close();
        for (int j = index; j >= 1; --j) {
            System.out.print(res[j] + " ");
        }
    }

    public static boolean isPalindrome(int nr) {
        int inverse = 0;
        int number = nr;
        while (nr != 0) {
            inverse = inverse * 10 + nr % 10;
            nr /= 10;
        }
        return number == inverse;
    }
}

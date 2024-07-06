package week114;

import java.util.Scanner;


public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            v[i] = scanner.nextInt();
        }
        scanner.close();
        int maxDigits = 0;
        int firstIndex = 0;
        int secondIndex = 0;
        for (int i = 1; i <= n; ++i) {
            if (isPalindrome(v[i])) {
                if (nrOfDigits(v[i]) > maxDigits) {
                    maxDigits = nrOfDigits(v[i]);
                }
            }
        }
        boolean exists = false;
        while (maxDigits != 0) {
            int counter = 0;
            for (int j = 1; j <= n; ++j) {
                if (isPalindrome(v[j])) {
                    if (nrOfDigits(v[j]) == maxDigits){
                        ++counter;
                        if (firstIndex == 0) {
                            firstIndex = j;
                        }
                        secondIndex = j;
                    }
                }
            }
            if (counter == 2) {
                exists = true;
                break;
            } else {
                firstIndex = 0;
                --maxDigits;
            }
        }
        if (!exists) {
            System.out.println("NU EXISTA!");
        } else {
            System.out.println(firstIndex + " " + secondIndex);
        }
        
    }

    public static boolean isPalindrome(int nr) {
        if (nr < 0) {
            nr *= -1;
        }
        int no = nr;
        int inverse = 0;
        while (nr != 0) {
            inverse = inverse * 10 + nr % 10;
            nr /= 10;
        }
        return no == inverse;
    }

    public static int nrOfDigits(int nr) {
        int counter = 0;
        if (nr == 0) {
            return 1;
        }
        if (nr < 0) {
            nr *= -1;
        }
        while (nr != 0) {
            ++counter;
            nr /= 10;
        }
        return counter;
    }
}

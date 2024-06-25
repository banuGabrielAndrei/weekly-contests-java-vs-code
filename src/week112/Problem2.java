package week112;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[n];
        StringBuilder[] palindroms = new StringBuilder[n];
        for (int i = 0; i < n; ++i) {; 
            v[i] = scanner.nextInt();
            palindroms[i] = new StringBuilder();
            palindroms[i].append(v[i]);
        }
        scanner.close();
        for (int i = 0; i < palindroms.length; ++i) {
            if (isPalindrome(palindroms[i])) {
                palindroms[i] = changedNumber(palindroms[i]);
            }
        }
        for (int j = palindroms.length - 1; j >= 0; --j) {
            System.out.print(palindroms[j] + " ");
        }
    }

    public static boolean isPalindrome(StringBuilder number) {
        String nr = number.toString();
        String reversedNr = number.reverse().toString();
        number.reverse();
        return nr.equals(reversedNr);
    }

    public static StringBuilder changedNumber(StringBuilder nr) {
        for (int i = 0; i < nr.length(); ++i) {
            int digit = nr.charAt(i) - '0';
            if (digit % 2 == 0) {
                nr.setCharAt(i, '0');
            }
        }
        return nr;
    }
}

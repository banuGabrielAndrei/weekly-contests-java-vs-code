/*
STATEMENT:
Sparky, un rover trimis pe Marte, analizează trei fragmente dintr-o mostră de sol și notează pentru fiecare codul mineralului identificat.

Baza de cercetare îi transmite apoi codurile unuia, a două sau a trei minerale și îi cere să afle de câte ori a identificat fiecare dintre acestea în mostră.

Cerință
Se dau numerele naturale n și m, unde cifrele numărului n reprezintă codurile mineralelor identificate în cele trei fragmente ale mostrei, iar cifrele numărului m reprezintă codurile mineralelor transmise de baza de cercetare.

Să se afle de câte ori apare în numărul n fiecare cifră a numărului m.

Date de intrare
De pe prima linie se citesc numerele naturale n și m, separate printr-un spațiu.

Date de ieșire
Se vor afișa, separate prin câte un spațiu, numerele de apariții în n ale cifrelor numărului m, în ordinea în care acestea apar în m.

Restricții și precizări
100 ≤ n ≤ 999
1 ≤ m ≤ 999

Exemplu
Date de intrare	Date de ieșire
121 12	2 1
*/


package WellCode.week222;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.close();
        int[] digitsN = getDigits(n, digitCount(n));
        int[] digitsM = getDigits(m, digitCount(m));
        int[] result = new int[digitsM.length];
        for (int i = 0; i < digitsM.length; ++i) {
            result[i] = cntDigits(digitsN, digitsM[i]);
        }
        for (int i = 0; i < result.length; ++i) {
            System.out.print(result[i] + " ");
        }
    }

    public static int digitCount(int number) {
        int count = 0;
        while (number > 0) {
            ++count;
            number /= 10;
        }
        return count;
    }

    public static int[] getDigits(int number, int length) {
        int[] digits = new int[length];
        for (int i = length - 1; i >= 0; --i) {
            digits[i] = number % 10;
            number /= 10;
        }
        return digits;
    }

    public static int cntDigits(int[] digits, int digit) {
        int count = 0;
        for (int i = 0; i < digits.length; ++i) {
            if (digits[i] == digit) {
                ++count;
            }
        }
        return count;
    }
}

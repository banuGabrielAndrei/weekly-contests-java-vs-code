/*
STATEMENT:
După experiența acumulată la analiza anterioară, baza de cercetare îi permite lui Sparky să stabilească singur numărul fragmentelor prelevate, în funcție de duritatea solului.

La rândul ei, baza îi transmite, în funcție de necesitățile de moment, codurile a cel mult zece minerale și îi cere să afle de câte ori a identificat fiecare dintre acestea în mostră.

Cerință
Se dau numerele naturale n și m, unde cifrele numărului n reprezintă codurile mineralelor identificate în urma forajului, iar cifrele numărului m reprezintă codurile mineralelor transmise de baza de cercetare.

Să se afle de câte ori apare în numărul n fiecare cifră a numărului m.

Date de intrare
De pe prima linie se citesc numerele naturale n și m, separate printr-un spațiu.

Date de ieșire
Se vor afișa, separate prin câte un spațiu, numerele de apariții în n ale cifrelor numărului m, în ordinea în care acestea apar în m.

Restricții și precizări
1 ≤ n, m ≤ 2 000 000 000

Exemplu
Date de intrare	Date de ieșire
120212 201	3 1 2
*/

package WellCode.week222;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.close();
        int[] freq = new int[10];
        int copyN = n;
        while (copyN > 0) {
            ++freq[copyN % 10];
            copyN /= 10;
        }
        int[] digitsM = new int[digitCount(m)];
        int copyM = m;
        for (int i = digitsM.length - 1; i >= 0; --i) {
            digitsM[i] = copyM % 10;
            copyM /= 10;
        }
        for (int i = 0; i < digitsM.length; ++i) {
            System.out.print(freq[digitsM[i]] + " ");
        }
        System.out.println();
    }

    public static int digitCount(int number) {
        int count = 0;
        while (number > 0) {
            ++count;
            number /= 10;
        }
        return count;
    }
}

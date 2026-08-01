/*
STATEMENT:
Pe măsură ce explorarea avansează, Sparky analizează un număr tot mai mare de fragmente de sol. Pentru a putea găsi rapid informațiile necesare, sistemul său de bord păstrează codurile mineralelor identificate în ordine crescătoare.

Baza de cercetare îi transmite apoi codurile mineralelor de interes și îi cere să afle de câte ori a identificat fiecare dintre acestea.

Cerință
Se dau numerele naturale n și m, un șir de n numere naturale ordonat crescător, reprezentând codurile mineralelor identificate de Sparky, și un șir de m numere naturale, reprezentând codurile mineralelor transmise de baza de cercetare.

Să se afle de câte ori apare în primul șir fiecare element al celui de-al doilea șir.

Date de intrare
De pe prima linie se citesc numerele naturale n și m, separate printr-un spațiu.
De pe a doua linie se citesc cele n elemente ale primului șir, separate prin câte un spațiu.
De pe a treia linie se citesc cele m elemente ale celui de-al doilea șir, separate prin câte un spațiu.

Date de ieșire
Se vor afișa, separate prin câte un spațiu, numerele de apariții în primul șir ale elementelor celui de-al doilea șir, în ordinea în care acestea apar în al doilea șir.

Restricții și precizări
1 ≤ n, m ≤ 30 000
0 ≤ elementele celor două șiruri ≤ 2 000 000 000
Limbajele acceptate pentru această problemă sunt C++, Java și Perl.
Exemplu
Date de intrare	Date de ieșire
5 3             3 1 0
1 2 2 2 4
2 1 5

*/

package week222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem3 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        int[] digitsN = new int[n];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; ++i) {
            digitsN[i] = Integer.parseInt(tokenizer.nextToken());
        }
        var output = new StringBuilder();
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; ++i) {
            int query = Integer.parseInt(tokenizer.nextToken());
            if (i > 0) {
                output.append(' ');
            }
            output.append(countOccurrences(digitsN, query));
        }
        System.out.println(output);
    }

    public static int firstOccurrence(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        int firstIndex = -1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (arr[middle] == value) {
                firstIndex = middle;
                right = middle - 1;
            } else if (arr[middle] < value) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return firstIndex;
    }

    public static int lastOccurrence(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        int lastIndex = -1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (arr[middle] == value) {
                lastIndex = middle;
                left = middle + 1;
            } else if (arr[middle] < value) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return lastIndex;
    }

    public static int countOccurrences(int[] arr, int value) {
        int first = firstOccurrence(arr, value);
        if (first == -1) {
            return 0;
        }
        int last = lastOccurrence(arr, value);
        return last - first + 1;
    }
}




/*
==> issue with the input reading too slow when using Scanner class
==> using BufferedReader and StringTokenizer is much faster
==> "time limit exceeded" when using Scanner class
import java.util.Scanner;

public class prog {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] digitsN = new int[n];
        int[] digitsM = new int[m];
        for (int i = 0; i < n; ++i) {
            digitsN[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; ++i) {
            digitsM[i] = scanner.nextInt();
            System.out.print(countOccurrences(digitsN, digitsM[i]) + " ");
        }
        scanner.close();
    }

    public static int firstOccurrence(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        int firstIndex = -1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (arr[middle] == value) {
                firstIndex = middle;
                right = middle - 1;
            } else if (arr[middle] < value) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return firstIndex;
    }

    public static int lastOccurrence(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        int lastIndex = -1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (arr[middle] == value) {
                lastIndex = middle;
                left = middle + 1;
            } else if (arr[middle] < value) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return lastIndex;
    }

    public static int countOccurrences(int[] arr, int value) {
        int first = firstOccurrence(arr, value);
        if (first == -1) {
            return 0;
        }
        int last = lastOccurrence(arr, value);
        return last - first + 1;
    }
}
*/
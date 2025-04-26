package week156;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        if (isSacredGround(matrix)) {
            System.out.println("Pamantul este sacru");
            if (biggestNegativeValue(matrix) != Integer.MIN_VALUE) {
                System.out.println(biggestNegativeValue(matrix));
            } else {
                System.out.println("NU EXISTA");
            }
        } else {
            System.out.println("Pamantul nu este sacru");
            if (smallestPositiveValue(matrix) != Integer.MAX_VALUE) {
                System.out.println(smallestPositiveValue(matrix));
            } else {
                System.out.println("NU EXISTA");
            }
        }
    }

    public static boolean isSacredGround(int[][] matrix) {
        int n = matrix.length;
        int sumDiagPrincipal = 0;
        int sumDiagSecundar = 0;
        for (int i = 0; i < n; i++) {
            sumDiagPrincipal += matrix[i][i];
            sumDiagSecundar += matrix[i][n - 1 - i];
        }
        if (sumDiagPrincipal != sumDiagSecundar) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            int sumRow = 0;
            int sumCol = 0;
            for (int j = 0; j < n; j++) {
                sumRow += matrix[i][j];
                sumCol += matrix[j][i];
            }
            if (sumRow != sumDiagPrincipal || sumCol != sumDiagPrincipal) {
                return false;
            }
        }
        return true;
    }

    public static int biggestNegativeValue(int[][] matrix) {
        int biggest = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < 0 && matrix[i][j] > biggest) {
                    biggest = matrix[i][j];
                }
            }
        }
        return biggest;
    }

    public static int smallestPositiveValue(int[][] matrix) {
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] >= 0 && matrix[i][j] < smallest) {
                    smallest = matrix[i][j];
                }
            }
        }
        return smallest;
    }
}

/*STATEMENT
 * Cerință
Se dă o matrice de n x n elemente, reprezentând o proprietate a unui funcționar din Egipt.
Fiecare element din această matrice reprezintă altitudinea maximă a unei bucăți din proprietate.
Dacă pământul este sacru, aflați cea mai mare altitudine a unei bucăți care să fie negativă, respectiva bucată fiind locul mormântului.
În caz contrar, cea mai mică altitudine a unei bucăți care să fie pozitivă va reprezenta bucata de pământ unde va trebui să caute Aurelius.

Date de intrare
De pe prima linie se citește de la tastatură numărul natural n.
De pe următoarele n linii se citesc câte n numere întregi, reprezentând elementele matricei.

Date de ieșire
Pe prima linie se va afișa mesajul Pamantul este sacru sau Pamantul nu este sacru, în funcție de caz, conform cerinței.
Pe a doua linie se va afișa cel mai mare număr negativ sau cel mai mic număr pozitiv din matrice, după caz, reprezentând altitudinea porțiunii de pământ unde va trebui să caute Aurelius.

Restricții și precizări
1 ≤ n ≤ 20
-100 <= elementele matricei <= 100
Dacă va trebui afișat pe linia 2 un element pozitiv și acesta nu există în matrice SAU un element negativ și nu există în matrice, se va afișa în schimb textul NU EXISTA.
Considerăm că 0 este număr pozitiv
Exemplu
Date de intrare	Date de ieșire
3
2 7 6
9 5 1
4 3 8	Pamantul este sacru
NU EXISTA
 */

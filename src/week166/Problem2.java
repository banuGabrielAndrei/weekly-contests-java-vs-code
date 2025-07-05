package week166;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] digits = new int[n];
        for (int i = 0; i < n; ++i) {
            digits[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; ++i) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            int x = scanner.nextInt();
            System.out.println(answer(digits, l - 1, r - 1, x));
        }
        scanner.close();
    }

    public static String answer(int[] digits, int l, int r, int x) {
        int sum = 0;
        for (int i = 0; i < digits.length; ++i) {
            if (i >= l && i <= r) {
                sum += x;
            } else {
                sum += digits[i];
            }
        }
        if (sum % 2 == 0) {
            return "DA";
        } else {
            return "NU";
        }
    }
}

// STATEMENT:
// Într-un mic sat pitoresc, locuia un tânăr curios și isteț pe nume Mirel. Acesta era cunoscut în comunitate drept un băiat căruia îi plăcea să scrie cod.

// Într-o zi s-a răspândit o veste intrigantă în sat. O comoară legendară, ascunsă de strămoșii lor, putea fi găsită doar dacă se rezolvă o serie de enigme.

// A fost adus în fața tuturor un pergament vechi, pe care erau scrise cerințele primei enigme. Pentru a dezvălui calea către comoară, citise Mirel cu glas tare, trebuie să rezolvi această problemă: dat fiind un șir de numere și o serie de interogări, determină pentru fiecare interogare dacă suma elementelor din șir devine pară atunci când anumite elemente sunt înlocuite cu o valoare specifică.

// Deoarece îți dorești să demonstrezi că ești mai bun decât Mirel la scris cod, te-ai și apucat de treabă.

// Cerință
// Pentru a le fi mai ușor să rezolve enigma, sătenii au transformat textul din pergament într-o cerință inteligibilă.
// Se dă un număr natural n și un șir de n numere naturale.
// Apoi, se dă un număr natural m, ce reprezintă numărul de interogări ce trebuie făcute.
// O interogare este reprezentată de următoarea problemă: dacă schimbăm toate valorile elementelor din șirul inițial, aflate pe pozițiile din intervalul [l, r] din șir, cu valoarea x, valoarea sumei tuturor elementelor din șir va fi sau nu un număr par?
// Pentru fiecare interogare, răspunsul afișat de programul tău trebuie să fie DA, sau NU, după caz.

// Date de intrare
// De pe prima linie se citesc numerele naturale n și m.
// De pe a doua linie se citesc n numere naturale, reprezentând elementele șirului.
// De pe fiecare dintre următoarele m linii se citesc câte 3 valori: l, r și x.

// Date de ieșire
// Pentru fiecare interogare, se va afișa pe o linie nouă unul dintre mesajele DA, sau NU, după caz, conform cerinței.

// Restricții și precizări
// 2 ≤ n ≤ 100 000
// 1 ≤ m ≤ 1000
// 1 ≤ l ≤ r ≤ n
// 0 ≤ valorile elementelor din șir, x ≤ 100
// Exemplu
// Date de intrare	Date de ieșire
// 8 3
// 5 6 7 2 3 1 9 13
// 1 2 6
// 2 4 9
// 4 8 2	NU
// DA
// DA
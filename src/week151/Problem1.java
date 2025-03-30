package week151;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        if (isValid(n)) {
            System.out.println("DA");
        } else {
            System.out.println("NU");
        }
    }

    public static boolean isValid(int nr) {
        int[] digits = new int[4];
        int index = 0;
        while (nr != 0) {
            digits[index] = nr % 10;
            nr /= 10;
            ++index;
        }
        for (int i = 0; i < digits.length - 1; ++i) {
            if (digits[i] != digits[i + 1] + 1) {
                return false;
            }
        }
        return true;
    }
}

//checking github

/*
 * PROBLEM STATEMENT
 * După doar câteva zile petrecute în internship la compania locală de IT, ai
 * reușit să te faci remarcat.
 * 
 * Văzând cât de dedicat și hotărât ești să înveți cât mai multe lucruri ce țin
 * de programare în cât mai scurt timp, superiorii tăi au venit cu ideea de a-ți
 * da un test.
 * 
 * Dacă vei reuși să rezolvi acest test, te vor promova din statutul de intern
 * în cel de angajat full time.
 * 
 * Pentru a rezolva testul, trebuie să scrii un program, care pentru un număr n
 * format din fix 4 cifre, va afișa mesajul DA, dacă cifrele care formează
 * numărul sunt consecutive și în ordine crescătoare.
 * 
 * Dacă acestea nu sunt consecutive și în ordine crescătoare, programul tău va
 * trebui să afișeze mesajul NU.
 * 
 * Date de intrare
 * De pe prima linie se citește de la tastatură numărul natural n.
 * 
 * Date de ieșire
 * Pe ecran se va afișa mesajul DA, dacă cifrele numărului n sunt consecutive și
 * în ordine crescătoare, sau NU, în caz contrar.
 * 
 * Restricții și precizări
 * 1000 ≤ n ≤ 9999
 * Cifrele consecutive sunt cele care urmează una după cealaltă în șirul
 * numerelor naturale
 * Exemplu
 * Date de intrare Date de ieșire
 * 2345 DA
 * 1324 NU
 */

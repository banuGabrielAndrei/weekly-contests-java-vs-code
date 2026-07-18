/*
STATEMENT:
Radu a finalizat programele pentru calcularea taxelor salariale.

Următoarea sa sarcină este să genereze un raport fiscal pentru angajații firmei.

Cerință
Se dau numărul n de angajați, iar pentru fiecare angajat se cunosc numele, salariul brut s și luna l.

Să se afișeze angajații în ordine crescătoare după totalul obligațiilor fiscale.

Dacă doi angajați au același total al obligațiilor fiscale, aceștia se vor afișa în ordine alfabetică după nume.

Obligațiile fiscale ale unui angajat sunt contribuția la asigurările sociale (CAS), contribuția la asigurările sociale de sănătate (CASS), impozitul pe venit și contribuția asiguratorie pentru muncă (CAM).

Totalul obligațiilor fiscale este suma acestor patru valori, iar acestea se calculează pentru fiecare angajat după cum urmează:

Mai întâi, trebuie stabilită baza de calcul, care reprezintă diferența dintre salariul brut și suma neimpozabilă.

Suma neimpozabilă se stabilește astfel:
Dacă luna este cuprinsă între 1 și 6, pentru un salariu brut de cel mult 4300 de lei, suma neimpozabilă este de 300 de lei. Dacă însă salariul brut este mai mare de 4300 de lei, suma neimpozabilă este 0.

Dacă luna este cuprinsă între 7 și 12, pentru un salariu brut de cel mult 4500 de lei, suma neimpozabilă este de 200 de lei. Dacă însă salariul brut este mai mare de 4500 de lei, suma neimpozabilă este 0.

După stabilirea bazei de calcul, obligațiile fiscale se calculează după cum urmează:

CAS reprezintă 20% din baza de calcul;
CASS reprezintă 10% din baza de calcul;
impozitul pe venit reprezintă 10% din suma rămasă din baza de calcul după scăderea CAS și CASS;
CAM reprezintă 2% din salariul brut și este plătită separat de angajator.
Date de intrare
De pe prima linie se citește numărul natural n, reprezentând numărul de angajați.
De pe următoarele n linii se citesc numele unui angajat și numerele naturale s și l, separate prin câte un spațiu, reprezentând salariul brut, respectiv luna pentru care se calculează obligațiile fiscale.

Date de ieșire
Se vor afișa n linii, corespunzătoare angajaților ordonați după regulile din cerință.
Pe fiecare linie se vor afișa, separate prin câte un spațiu, numele angajatului, CAS, CASS, impozitul pe venit, CAM și totalul obligațiilor fiscale.

Restricții și precizări
1 ≤ n ≤ 100;
1 ≤ l ≤ 12;
4000 ≤ s ≤ 100000;
Fiecare salariu brut s este multiplu de 100;
Numele fiecărui angajat are între 1 și 20 de caractere;
Numele sunt formate doar din litere ale alfabetului englez și nu conțin spații;
Numele angajaților sunt distincte;
Numele fiecărui angajat începe cu literă mare, iar celelalte litere sunt mici
Toate valorile calculate sunt numere naturale.
Recomandăm rezolvarea problemei folosind structuri de date, instrucțiuni repetitive, instrucțiuni decizionale și operații aritmetice, fără funcții predefinite de sortare.
Exemplu
Date de intrare	Date de ieșire
5
Ionel 4300 5
Maria 4400 5
Gigel 4500 8
Ana 4300 5
Sorin 5000 5	Ana 800 400 280 86 1566
Ionel 800 400 280 86 1566
Gigel 860 430 301 90 1681
Maria 880 440 308 88 1716
Sorin 1000 500 350 100 1950
Explicație
Totalurile obligațiilor fiscale sunt:

pentru Ionel: 800 + 400 + 280 + 86 = 1566;
pentru Maria: 880 + 440 + 308 + 88 = 1716;
pentru Gigel: 860 + 430 + 301 + 90 = 1681;
pentru Ana: 800 + 400 + 280 + 86 = 1566;
pentru Sorin: 1000 + 500 + 350 + 100 = 1950.
Ana și Ionel au același total al obligațiilor fiscale, astfel că sunt afișați în ordine alfabetică.
*/


package week220;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem3 {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayList<String> rows = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            String name = scanner.next();
            int s = scanner.nextInt();
            int l = scanner.nextInt();
            int cas = computeCAS(s, l);
            int cass = computeCASS(s, l);
            int tax = computeImpozit(s, l);
            int cam = computeCAM(s);
            int total = cas + cass + tax + cam;

            rows.add(name + "," + cas + "," + cass + "," + tax + "," + cam + "," + total);
        }
        scanner.close();

        for (int i = 0; i < rows.size() - 1; ++i) {
            int min = i;
            for (int j = i + 1; j < rows.size(); ++j) {
                if (comesFirst(rows.get(j), rows.get(min))) {
                    min = j;
                }
            }
            if (min != i) {
                String tmp = rows.get(i);
                rows.set(i, rows.get(min));
                rows.set(min, tmp);
            }
        }
        for (int i = 0; i < rows.size(); ++i) {
            System.out.println(rows.get(i).replace(",", " "));
        }
    }

    public static boolean comesFirst(String a, String b) {
        int totalA = getTotal(a);
        int totalB = getTotal(b);
        if (totalA != totalB) {
            return totalA < totalB;
        }
        return getName(a).compareTo(getName(b)) < 0;
    }

    public static String getName(String row) {
        return row.split(",")[0];
    }

    public static int getTotal(String row) {
        String[] parts = row.split(",");
        return Integer.parseInt(parts[5]);
    }

    public static int sumNonTax(int s, int l) {
        if (l >= 1 && l <= 6) {
            return s <= 4300 ? 300 : 0;
        }
        return s <= 4500 ? 200 : 0;
    }

    public static int computeBase(int s, int l) {
        return s - sumNonTax(s, l);
    }

    public static int computeCAS(int s, int l) {
        return computeBase(s, l) * 20 / 100;
    }

    public static int computeCASS(int s, int l) {
        return computeBase(s, l) * 10 / 100;
    }

    public static int computeImpozit(int s, int l) {
        int baza = computeBase(s, l);
        return (baza - computeCAS(s, l) - computeCASS(s, l)) * 10 / 100;
    }

    public static int computeCAM(int s) {
        return s * 2 / 100;
    }
}

/*
 * IS THERE A MORE EFFICIENT WAY?
 *
 * For this problem it does not matter in practice: n <= 100, so even the slowest
 * correct solution finishes instantly. But it's worth understanding WHERE this
 * version wastes work, and how a tighter version would look.
 *
 * 1) THE REAL WASTE HERE: re-parsing strings during the sort.
 *    We store each person as one string "name,cas,...,total". Every time the
 *    selection sort compares two people, comesFirst() calls split(",") and
 *    parseInt() on BOTH strings - and the sort compares pairs about n*n/2 times.
 *    So we split/parse the same strings thousands of times just to read numbers
 *    we already knew when we first computed them.
 *
 *    FIX: parse ONCE. Keep the total as a real number next to the string, e.g.
 *    two parallel arrays lined up by index i (no new types needed):
 *
 *        String[] line   = new String[n];   // "name cas cass tax cam total" (ready to print)
 *        int[]    total  = new int[n];       // the total as a number, for comparing
 *        String[] name   = new String[n];    // the name, for the tie-break
 *
 *    Now the sort compares plain ints and strings directly - no split, no parseInt
 *    inside the loop. When you swap during the sort, swap all three arrays at the
 *    same index so a person's data stays together. This turns each comparison from
 *    "split a string + convert text to number" into a single fast integer compare.
 *
 * 2) THE SORT ALGORITHM ITSELF: selection sort is O(n*n) (it scans the rest of
 *    the list for every position). A faster algorithm like merge sort is
 *    O(n log n) and would matter if n were large (say 100000). BUT: the contest
 *    forbids built-in sort functions, and here n <= 100, so n*n = at most 10000
 *    steps - nothing. Selection sort is the right call for this problem: simple,
 *    correct, and plenty fast.
 *
 * 3) SMALL EXTRAS: reading with BufferedReader instead of Scanner is faster for
 *    very large inputs, and building output with a StringBuilder + one print at
 *    the end beats many System.out.println calls. Again - only meaningful for big
 *    inputs, irrelevant at n <= 100.
 *
 * BOTTOM LINE: the biggest easy win is "parse once, store the number" (point 1),
 * which removes repeated string parsing from the sort. Changing the sort algorithm
 * (point 2) is not worth it here because n is tiny and built-in sort is banned.
 */

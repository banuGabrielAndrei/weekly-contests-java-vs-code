/*
STATEMENT:
Programul realizat de Radu calculează corect taxele și salariul net al unui angajat.

Următoarea sa sarcină este să studieze cum pot fi aplicate două procente de taxare unor salarii brute diferite, astfel încât salariile nete obținute să fie egale.

Cerință
Se dau salariile brute a și b ale angajaților Ionel și Gigel.
Să se determine procentele naturale de taxare x și y care trebuie aplicate celor două salarii, astfel încât salariile nete ale celor doi angajați să fie egale.

Salariul net al lui Ionel se obține astfel: a * (100 - x) / 100
Salariul net al lui Gigel se obține astfel: b * (100 - y) / 100

Dacă există mai multe soluții, se va alege cea cu cea mai mică valoare pentru x.
Dacă încă există mai multe soluții cu aceeași valoare pentru x, se va alege cea cu cea mai mică valoare pentru y.

Date de intrare
De pe prima linie se citesc numerele naturale a și b, separate printr-un spațiu, reprezentând salariile brute ale lui Ionel, respectiv Gigel.

Date de ieșire
Pe prima linie se vor afișa, separate printr-un spațiu, procentele de taxare x și y.

Restricții și precizări
100 ≤ a, b ≤ 100000;
Salariile brute a și b sunt multipli de 100;
1 ≤ x, y ≤ 99;
Calcularea salariilor nete se realizează prin împărțiri exacte;
Se garantează că există cel puțin o pereche de procente pentru care salariile nete sunt egale.
Recomandăm rezolvarea problemei folosind doar instrucțiuni repetitive, instrucțiuni decizionale și operații aritmetice, fără structuri de date și fără funcții matematice predefinite.
Exemplu
Date de intrare	Date de ieșire
4000 5000	5 24
Explicație
Pentru un procent de taxe de 5%, salariul net al lui Ionel este:
4000 × (100 - 5) / 100 = 3800
Pentru un procent de taxe de 24%, salariul net al lui Gigel este:
5000 × (100 - 24) / 100 = 3800
Astfel cele două salarii nete sunt egale.
Există și alte soluții, precum 10 28 și 15 32, dar 5 24 este soluția cu cea mai mică valoare pentru x.



*/

package week220;

import java.util.Scanner;

public class Problem2 {
    static final int HUNDRED = 100;
    static final int MAX_PERCENTAGE = 99;

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();

        boolean found = false;
        for (int x = 1; x <= MAX_PERCENTAGE && !found; ++x) {
            int netIonel = a * (HUNDRED - x) / HUNDRED;
            for (int y = 1; y <= MAX_PERCENTAGE && !found; ++y) {
                int netGigel = b * (HUNDRED - y) / HUNDRED;
                if (netIonel == netGigel) {
                    found = true;
                    System.out.println(x + " " + y);
                    break;
                }
            }
        }
    }
}

/*
 * IS THERE A MORE EFFICIENT WAY?
 *
 * For this problem it does not matter in practice: x and y only go from 1 to 99,
 * so the double loop does at most 99 * 99 = 9801 checks - that finishes instantly.
 * But here is where the extra work is, and how to remove it.
 *
 * 1) THE WASTE: the inner loop tries every y for each x.
 *    For a fixed x we already know Ionel's net pay. Instead of testing all 99
 *    values of y to find the one that matches, we can COMPUTE the y we need
 *    directly with arithmetic - no inner loop.
 *
 *    We want:  b * (100 - y) / 100 == netIonel
 *    Multiply both sides by 100:      b * (100 - y) == netIonel * 100
 *    So:                              100 - y == (netIonel * 100) / b
 *    Therefore:                       y == 100 - (netIonel * 100) / b
 *
 *    That y is only valid if the division is exact (no remainder) and the result
 *    lands in 1..99. So the faster loop looks like this (still smallest x first,
 *    so the first hit is the answer):
 *
 *        for (int x = 1; x <= MAX_PERCENTAGE; ++x) {
 *            int netIonel = a * (HUNDRED - x) / HUNDRED;
 *            int need = netIonel * HUNDRED;      // what b * (100 - y) must equal
 *            if (need % b == 0) {                // only then can an exact y exist
 *                int y = HUNDRED - need / b;
 *                if (y >= 1 && y <= MAX_PERCENTAGE) {
 *                    System.out.println(x + " " + y);
 *                    break;                       // first x = smallest x = the answer
 *                }
 *            }
 *        }
 *
 *    This turns O(99 * 99) into O(99): for each x we do a couple of arithmetic
 *    checks instead of a whole inner scan. It uses only +,-,*,/ and % - no data
 *    structures and no predefined math functions, so it respects the contest rules.
 *
 * 2) WHY THE FIRST HIT IS STILL CORRECT: we loop x upward, and for each x there is
 *    at most ONE y that works (we solved for it), so the first valid (x, y) has the
 *    smallest x - and its single y - exactly the tie-break the statement asks for.
 *
 * BOTTOM LINE: the smart trick is "don't search for y, calculate it". It is ~100x
 * fewer steps. For n this tiny both versions are instant, so the double loop is
 * perfectly fine to submit - the direct-y version is just the cleaner, faster idea.
 */

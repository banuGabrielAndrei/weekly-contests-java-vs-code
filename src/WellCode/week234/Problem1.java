/*STATEMENT

Trei turiști așteaptă să urce cu telecabina la o cabană, iar organizatorul vrea să afle numărul minim de curse necesare.

Într-o cursă spre cabană pot urca unul sau doi turiști, cu o greutate totală de cel mult x. Turiștii urcă în ordinea în care stau la coadă.

Cerință
Se dau numerele naturale a, b și c, reprezentând greutățile celor trei turiști în ordinea din coadă, și limita de greutate x.
Să se afle numărul minim de curse spre cabană necesare pentru a transporta toți turiștii, respectând regulile de îmbarcare.

Date de intrare
De pe prima linie se citește numărul natural x. De pe a doua linie se citesc numerele naturale a, b și c, separate prin câte un spațiu.

Date de ieșire
Pe prima linie se afișează un singur număr natural: numărul minim de curse spre cabană.

Restricții și precizări
1 ≤ x ≤ 1 000 000
1 ≤ a, b, c ≤ x
Fiecare turist trebuie transportat exact o dată.
Greutățile turiștilor și limita x sunt exprimate în aceeași unitate de măsură.
Recomandăm rezolvarea problemei fără utilizarea structurilor repetitive, a vectorilor și a funcțiilor predefinite, cu excepția celor necesare pentru citirea datelor și afișarea rezultatului.
Exemplu
Date de intrare	Date de ieșire
100
60 60 40	2
Explicație
În prima cursă urcă primul turist, cu greutatea 60. În a doua cursă urcă următorii doi turiști, cu greutățile 60 și 40, a căror sumă este 100.
Ordinea din coadă este păstrată, iar limita de greutate este respectată în ambele curse.
O singură cursă nu poate transporta trei turiști, deci numărul minim de curse este 2. */

package WellCode.week234;

import java.util.Scanner;

public class Problem1 {
    
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int limitWeight = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();
        int trips = computeTrips(limitWeight, a, b, c);
        System.out.println(trips);
    }

    public static int computeTrips(int limitWeight, int a, int b, int c) {
        if (a + b <= limitWeight || b + c <= limitWeight) {
            return 2;
        }
        return 3;
    }
}
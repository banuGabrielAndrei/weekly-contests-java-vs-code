/*STATEMENT

La stația telecabinei s-a format o coadă de turiști care vor să urce la o cabană, iar organizatorul vrea să afle numărul minim de curse necesare.

Într-o cursă spre cabană pot urca unul sau doi turiști, cu o greutate totală de cel mult x. Turiștii urcă în ordinea în care stau la coadă.

Cerință
Se dau numărul de turiști n, limita de greutate x și cele n greutăți, în ordinea din coadă.
Să se afle numărul minim de curse spre cabană necesare pentru a transporta toți turiștii, respectând regulile de îmbarcare.

Date de intrare
De pe prima linie se citesc numerele naturale n și x, separate printr-un spațiu. De pe a doua linie se citesc cele n greutăți, numere naturale separate prin câte un spațiu.

Date de ieșire
Pe prima linie se afișează un singur număr natural: numărul minim de curse spre cabană.

Restricții și precizări
1 ≤ n ≤ 1 000
1 ≤ x ≤ 1 000 000
Fiecare greutate este cuprinsă între 1 și x, inclusiv.
Fiecare turist trebuie transportat exact o dată.
Greutățile turiștilor și limita x sunt exprimate în aceeași unitate de măsură.
Exemplu
Date de intrare	Date de ieșire
4 100
60 60 40 40	3
Explicație
Turiștii pot urca în trei curse, în ordinea din coadă:

prima cursă: primul turist, cu greutatea 60;
a doua cursă: al doilea și al treilea turist, cu greutățile 60 și 40, cu suma 100;
a treia cursă: al patrulea turist, cu greutatea 40.
Pentru a transporta cei patru turiști în doar două curse, în fiecare cursă ar trebui să urce câte doi. Păstrând ordinea, primii doi turiști ar urca împreună, dar suma greutăților lor este 120, mai mare decât limita 100. Prin urmare, numărul minim de curse este 3.
 */





package WellCode.week234;

import java.util.Scanner;

public class Problem2 {
    
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; ++i) {
            weights[i] = scanner.nextInt();
        }
        scanner.close();
        System.out.println(computeTrips(n, x, weights));
    }

    public static int computeTrips(int n, int limitWeight, int[] weights) {
        int trips = 0;
        int i = 0;
        while (i < n) {
            if (i + 1 < n && weights[i] + weights[i + 1] <= limitWeight) {
                i += 2;
            } else {
                ++i;
            }
            ++trips;
        }
        return trips;
    }
}
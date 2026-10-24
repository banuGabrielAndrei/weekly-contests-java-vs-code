/*STATEMENT
Organizatorul unei excursii vrea să afle numărul minim de curse necesare pentru ca toți turiștii să urce cu telecabina la o cabană.

Într-o cursă spre cabană pot urca unul sau doi turiști, cu o greutate totală de cel mult x. Organizatorul poate reordona turiștii înainte de îmbarcare, astfel încât cei care urcă împreună pot fi aleși de pe oricare două poziții din șirul inițial.

Cerință
Se dau numărul de turiști n, limita de greutate x și cele n greutăți.
Să se afle numărul minim de curse spre cabană necesare pentru a transporta toți turiștii, respectând regulile de îmbarcare.

Date de intrare
De pe prima linie se citesc numerele naturale n și x, separate printr-un spațiu. De pe a doua linie se citesc cele n greutăți, numere naturale separate prin câte un spațiu.

Date de ieșire
Pe prima linie se afișează un singur număr natural: numărul minim de curse spre cabană.

Restricții și precizări
1 ≤ n ≤ 100 000
1 ≤ x ≤ 1 000 000 000
Fiecare greutate este cuprinsă între 1 și x, inclusiv.
Fiecare turist trebuie transportat exact o dată.
Greutățile turiștilor și limita x sunt exprimate în aceeași unitate de măsură.
Exemplu
Date de intrare	Date de ieșire
4 100
60 60 40 40	2
Explicație
În prima cursă pot urca primul și al treilea turist din șirul inițial, cu greutățile 60 și 40. În a doua cursă pot urca al doilea și al patrulea turist, tot cu greutățile 60 și 40. Suma greutăților din fiecare cursă este 100, deci limita este respectată.
Deoarece într-o cursă pot urca cel mult doi turiști, pentru cei patru turiști sunt necesare cel puțin două curse. Prin urmare, numărul minim de curse este 2
*/


package WellCode.week234;

import java.util.Arrays;
import java.util.Scanner;

public class Problem3 {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int limitWeight = scanner.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; ++i) {
            weights[i] = scanner.nextInt();
        }
        scanner.close();
        System.out.println(computeTrips(n, limitWeight, weights));
    }

    public static int computeTrips(int n, int limitWeight, int[] weights) {
        Arrays.sort(weights);
        int trips = 0;
        int light = 0;
        int heavy = n-1 ;
        while (light <= heavy) {
            if (weights[light] + weights[heavy] <= limitWeight) {
                ++light;
            }
            --heavy;
            ++trips;
        }
        return trips;
    }
}
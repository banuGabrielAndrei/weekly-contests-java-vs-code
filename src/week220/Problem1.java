/*
STATEMENT:

Radu s-a angajat în departamentul IT al unei firme de contabilitate.

Prima sa sarcină este să creeze un program care să calculeze taxele și salariul net al unui angajat.

Cerință
Se dau salariul brut s al unui angajat și luna l.
Să se determine contribuția la asigurările sociale (CAS), contribuția la asigurările sociale de sănătate (CASS), impozitul pe venit, contribuția asiguratorie pentru muncă (CAM) și salariul net.

Calculul se realizează după următoarele reguli:

Mai întâi, trebuie stabilită baza de calcul, care reprezintă diferența dintre salariul brut și suma neimpozabilă.

Suma neimpozabilă se stabilește astfel:
Dacă luna este cuprinsă între 1 și 6, pentru un salariu brut de cel mult 4300 de lei, suma neimpozabilă este de 300 de lei. Dacă însă salariul brut este mai mare de 4300 de lei, suma neimpozabilă este 0.

Dacă luna este cuprinsă între 7 și 12, pentru un salariu brut de cel mult 4500 de lei, suma neimpozabilă este de 200 de lei. Dacă însă salariul brut este mai mare de 4500 de lei, suma neimpozabilă este 0.

După stabilirea bazei de calcul, contribuțiile și salariul net se calculează după cum urmează:

CAS reprezintă 20% din baza de calcul;
CASS reprezintă 10% din baza de calcul;
impozitul pe venit reprezintă 10% din suma rămasă din baza de calcul după scăderea CAS și CASS;
CAM reprezintă 2% din salariul brut și este plătită separat de angajator, fără a fi scăzută la calcularea salariului net;
salariul net se obține scăzând CAS, CASS și impozitul pe venit din salariul brut.
Date de intrare
De pe prima linie se citesc numerele naturale s și l, separate printr-un spațiu, reprezentând salariul brut al angajatului, respectiv luna pentru care se calculează salariul.

Date de ieșire
Pe prima linie se vor afișa, separate prin câte un spațiu, valorile corespunzătoare CAS, CASS, impozitului pe venit, CAM și salariului net.

Restricții și precizări
1 ≤ l ≤ 12;
4000 ≤ s ≤ 100000;
salariul brut s este multiplu de 100;
toate valorile calculate sunt numere naturale.
Recomandăm rezolvarea problemei folosind doar instrucțiuni decizionale și operații aritmetice, fără structuri repetitive și fără structuri de date.
Exemplu
Date de intrare	Date de ieșire
4300 5	800 400 280 86 2820
Explicație
Luna 5 este cuprinsă între 1 și 6, iar salariul brut nu depășește 4300 de lei. Astfel, suma neimpozabilă este de 300 de lei, iar baza de calcul este de 4000 de lei.

Contribuțiile și salariul net se calculează astfel:

CAS: 20% din 4000, adică 800;
CASS: 10% din 4000, adică 400;
impozitul pe venit: 10% din 4000 - 800 - 400, adică 280;
CAM: 2% din 4300, adică 86;
salariul net: 4300 - 800 - 400 - 280, adică 2820.
*/

package week220;

import java.util.Scanner;

public class Problem1 {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        int l = scanner.nextInt();
        scanner.close();

        int cas = 0;
        int cass = 0;
        int tax = 0;
        int cam = 0;
        int netSalary = 0;

        cas = computeCAS(s, l);
        cass = computeCASS(s, l);
        tax = computeImpozit(s, l);
        cam = computeCAM(s);
        netSalary = computeNetSalary(s, l);

        System.out.println(cas + " " + cass + " " + tax + " " + cam + " " + netSalary);
    }

    public static int sumaNeimpozabila(int s, int l) {
        if (l >= 1 && l <= 6) {
            return s <= 4300 ? 300 : 0;
        }
        return s <= 4500 ? 200 : 0;
    }

    public static int computeBase(int s, int l) {
        return s - sumaNeimpozabila(s, l);
    }

    public static int computeCAS(int s, int l) {
        return (int) (computeBase(s, l) * 0.2);
    }

    public static int computeCASS(int s, int l) {
        return (int) (computeBase(s, l) * 0.1);
    }

    public static int computeImpozit(int s, int l) {
        int baza = computeBase(s, l);
        return (int) ((baza - computeCAS(s, l) - computeCASS(s, l)) * 0.1);
    }

    public static int computeCAM(int s) {
        return (int) (s * 0.02);
    }

    public static int computeNetSalary(int s, int l) {
        return s - computeCAS(s, l) - computeCASS(s, l) - computeImpozit(s, l);
    }
}
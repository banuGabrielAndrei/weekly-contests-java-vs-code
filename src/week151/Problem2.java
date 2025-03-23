package week151;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] digits = new int[n];
        for (int i = 0; i < n; ++i) {
            digits[i] = scanner.nextInt();
        }
        scanner.close();
        int[] partialSum = new int[n];
        partialSum[0] = digits[0];
        for (int i = 1; i < n; ++i) {
            partialSum[i] = partialSum[i - 1] + digits[i];
        }
        int totalSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            int currentSum = partialSum[n - 1 - i];
            totalSum += currentSum;
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        if (isPrime(totalSum)) {
            System.out.println(totalSum);
        } else {
            System.out.println(maxSum);
        }
    }

    public static boolean isPrime(int nr) {
        if (nr < 2) {
            return false;
        }
        for (int i = 2; i * i <= nr; ++i) {
            if (nr % i == 0) {
                return false;
            }
        }
        return true;
    }
}

/*
 * PROBLEM STATEMENT
 * Cu ocazia weekend-ului prelungit, tu și cei n - 1 verișori de-ai tăi v-ați
 * hotărât să organizați o petrecere în pijamale.
 * 
 * Entuziasmați de petrecerea care urmează, ați început să căutați jocuri și
 * activități cu ajutorul cărora să vă umpleți timpul.
 * Pentru ca toată lumea să fie implicată și șă aibă parte de jocuri și
 * activități care îi plac, ați decis ca fiecare din voi să vină cu un joc sau o
 * activitate preferată pentru petrecere.
 * 
 * Jocul la care te-ai gândit tu este ”Telefonul fără fir”. Pentru că vă place
 * matematica și informatica, te-ai gândit să schimbi un pic regulile jocului.
 * 
 * Primul jucător îi va spune jucătorului din dreapta sa, un număr. Al doilea
 * jucător va spune jucătorului din dreapta lui numărul spus de primul jucător,
 * plus numărul la care s-a gândit el.
 * 
 * Astfel, în general, un jucător va trebui să îi spună jucătorului din dreapta
 * sa toate numerele spuse de jucătorul precedent (din stânga sa), iar apoi îi
 * va spune numărul la care s-a gândit el. Toți acești pași se vor repeta până
 * în momentul în care se ajunge la ultimul jucător.
 * 
 * În final, ultimul jucător va spune cele n - 1 numere spuse de penultimul
 * jucător, iar apoi va mai spune și el un număr la care s-a gândit. După asta,
 * va trebui să spună dacă șirul format din cele n numere pe care le-a spus este
 * un șir prim.
 * 
 * Pentru a determina dacă șirul este unul prim, ultimul jucător trebuie să afle
 * suma primelor n elemente, plus suma primelor n - 1 elemente, plus suma
 * primelor n - 2 elemente, ș.a.m.d până la primul element.
 * 
 * Dacă această sumă este un număr prim, șirul este unul prim, iar ultimul
 * jucător va trebui să spună care este acel număr.
 * 
 * Însă dacă șirul nu este unul prim, jucătorul din urmă, va trebui să spună
 * care este cea mai mare sumă, dintre cele n sume calculate.
 * 
 * Pentru a reuși să verificați corectitudinea răspunsurilor, vei construi un
 * program care să rezolve corect jocul.
 * 
 * Date de intrare
 * Pe prima linie se citește de la tastatură numărul natural n, reprezentând
 * numărul de jucători participanți.
 * Pe următoarea linie se citesc cele n numere, reprezentând numerele spuse de
 * fiecare jucător.
 * 
 * Date de ieșire
 * Se va afișa pe ecran suma menționată dacă șirul este un șir prim conform
 * condiției din enunț, sau cea mai mare sumă dintre cele n sume calculate dacă
 * șirul nu este un șir prim.
 * 
 * Restricții și precizări
 * 1 ≤ numărul de jucători (n) ≤ 500 000
 * -1000 ≤ valoarea fiecărui număr spus de jucători ≤ 1000
 * Pentru rezolvarea problemei e suficient să se folosească tipul de date int
 * (toate numerele calculate se vor încadra în tipul de date int)
 * Suma care dictează dacă șirul este prim sau nu, va fi mereu un număr natural,
 * deci >= 0
 * Exemplu
 * Date de intrare Date de ieșire
 * 5
 * 1 2 4 3 6
 */

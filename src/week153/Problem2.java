package week153;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> digits = new ArrayList<>();
        Set<Integer> inverses = new HashSet<>();
        Set<Integer> allValues = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            int digit = scanner.nextInt();
            digits.add(digit);
        }
        scanner.close();
        if (validList(digits)) {
            inverses = inverseList(digits);
            for (Integer nr: inverses) {
                System.out.print(nr + " ");
            }
        } else {
            allValues.addAll(digits);
            for (Integer nr: allValues) {
                System.out.print(nr + " ");
            }
        }
    }

    public static Integer inverse(Integer digit) {
        int inverse = 0;
        while (digit > 0) {
            inverse = inverse * 10 + digit % 10;
            digit /= 10;
        }
        return inverse;
    }

    public static boolean validList(List<Integer> digits) {
        for (Integer value : digits) {
            Integer inverse = inverse(value);
            if (!digits.contains(inverse)) {
                return false;
            }
        }
        return true;
    }

    public static Set<Integer> inverseList(List<Integer> digits) {
        Set<Integer> inverses = new TreeSet<>((a, b) -> b - a);
        for (Integer digit : digits) {
            inverses.add(inverse(digit));
        }
        return inverses;
    }
}


/*Se dă un șir de n numere naturale, reprezentând numerele din sistemul lui Dragoș după virusarea acestuia.

Dacă toate inversele elementelor din șir se află și ele în șir, afișați un șir care conține inversele elementelor în ordine descrescătoare, fiecare valoare o singură dată.
În caz contrar, afișați un șir care conține doar elementele șirului în ordine crescătoare, fiecare valoare o singură dată.
Chiar dacă mai multe elemente au aceeași valoare, o vei afișa o singură dată.
Procedând astfel, numerele din sistemul lui Dragoș vor fi la locul lor inițial.

Date de intrare
Pe prima linie se află numărul natural n ce reprezintă numărul de elemente din sistemul lui Dragoș.
Pe a doua linie se află cele n numere naturale din acest șir.

Date de ieșire
Se vor afișa elementele cerute, în ordine crescătoare sau descrescătoare și separate prin spații, în funcție de caz, conform cerinței.

Restricții și precizări
1 ≤ n ≤ 1000
0 ≤ valoarea elementelor din șir ≤ 999
Se garantează că ultima cifră a elementelor din șir e diferită de 0, excepție făcând numărul 0.
Exemplu
Date de intrare	Date de ieșire
6
21 321 1 21 12 123	321 123 21 12 1 */
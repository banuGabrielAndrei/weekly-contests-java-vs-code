package week156;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();
        int product = a * b * c;
        int sum = a + b + c;
        if (product % 2 == 0 && sum % 2 != 0) {
            System.out.println("Produsul este par");
        } else if (product % 2 == 0 && sum % 2 == 0) {
            System.out.println("Ambele sunt pare");
        } else if (product % 2 != 0 && sum % 2 != 0) {
            System.out.println("Ambele sunt impare");
        } else {
            System.out.println("Sumar par, produs impar");
        }
    }
}

/*STATEMENT
 * De pe prima linie se citesc numerele naturale a, b și c.

Date de ieșire
În cazul în care produsul numerelor este un număr par, iar suma un număr impar, pe ecran se va afișa mesajul Produsul este par.
Dacă atât suma, cât și produsul numerelor sunt pare, se va afișa mesajul Ambele sunt pare.
Dacă atât suma, cât și produsul numerelor sunt impare, se va afișa mesajul Ambele sunt impare.
 */

package week166;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int firstDigit = n / 1000;
        if (n < 1000) {
            firstDigit = n / 100;
        }
        int lastDigit = n % 10;
        int secondDigit = (n / 100) % 10;
        int thirdDigit = (n / 10) % 10;
        scanner.close();
        if ((firstDigit - lastDigit) > (secondDigit - thirdDigit)) {
            System.out.println("ESTE NOROCOS");
        } else {
            System.out.println("NU ESTE NOROCOS");
        }
    }
}


//STATEMENT:
// George este o persoană foarte superstițioasă. El consideră că anumite numere îi poartă noroc, pe când altele nu. Deoarece vrea să își cumpere o nouă casă, el vrea să se asigure că numărul casei alese va fi un număr norocos, de aceea te-a rugat pe tine să scrii un program care să determine automat dacă numărul casei pe care o va achiziționa este sau nu norocos.

// Cerință
// Se dă un număr natural n, ce reprezintă numărul casei pe care George vrea să o achiziționeze.
// Aflați dacă diferența dintre prima și ultima lui cifră este strict mai mare decât diferența dintre a doua și a treia lui cifră, acest număr reprezentând un număr norocos în opinia lui George.

// Date de intrare
// De pe prima linie se citește numărul natural n, reprezentând numărul unei case pe care vrea să o achiziționeze George.

// Date de ieșire
// Pe prima linie se va afișa unul dintre mesajele ESTE NOROCOS, sau NU ESTE NOROCOS, după caz, conform cerinței.

// Restricții și precizări
// 999 ≤ n ≤ 9999
// Exemplu
// Date de intrare	Date de ieșire
// 1234	NU ESTE NOROCOS
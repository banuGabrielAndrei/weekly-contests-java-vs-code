package week134;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        Integer a = scanner.nextInt();
        Integer b = scanner.nextInt();
        scanner.close();
        StringBuilder digitsA = new StringBuilder(a.toString());
        StringBuilder digitsB = new StringBuilder(b.toString());
        char lastB = digitsB.charAt(digitsB.length() - 1);
        char lastA = digitsA.charAt(digitsA.length() - 1);
        digitsA.setCharAt(0, lastB);
        digitsB.setCharAt(0, lastA);
        System.out.println(digitsA + " " + digitsB);
    }
}

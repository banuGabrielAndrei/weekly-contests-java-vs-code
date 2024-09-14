package week124;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        Integer a = scanner.nextInt();
        Integer b = scanner.nextInt();
        String digitA = a.toString();
        String digitB = b.toString();
        if (digitB.contains(digitA)) {
            System.out.println("DA!");
        } else {
            System.out.println("NU!");
        }
        scanner.close();
    }
}

package week118;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int money = a * 5;
        int rest = money - c;
        if (b != rest) {
            System.out.println(rest);
        } else {
            System.out.println("NU A GRESIT!");
        }
        scanner.close();
    }
}

package week95;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int l = scanner.nextInt();
        int distance = scanner.nextInt();
        int averageFuel = scanner.nextInt();
        scanner.close();
        if (distance * averageFuel / 100 <= l) {
            System.out.println("AJUNGE");
        } else {
            System.out.println(distance * averageFuel / 100 - l);
        }
    }
}
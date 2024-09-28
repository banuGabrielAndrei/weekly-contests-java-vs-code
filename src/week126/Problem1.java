package week126;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int speed = scanner.nextInt();
        int distance = scanner.nextInt();
        if (speed == 0) {
            System.out.println("niciodata!");
         } else if (distance % speed == 0) {
            System.out.println(distance / speed);
        } else {
            System.out.println(distance / speed + 1);
        }
        scanner.close();
    }
}

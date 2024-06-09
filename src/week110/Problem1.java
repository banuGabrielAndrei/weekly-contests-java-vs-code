package week110;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int sheeps = 0;
        sheeps = b * c;
        int currentMounth = a + b;
        if (a % 2 != 0 && currentMounth % 2 != 0) {
            sheeps += c;
        }
        scanner.close();
        System.out.println(sheeps);
    }
}

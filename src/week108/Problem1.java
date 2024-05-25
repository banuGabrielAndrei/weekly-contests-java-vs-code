package week108;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (a * 2 > b * 3) {
            System.out.println(a * 2);
        } else if (a * 2 < b * 3) {
            System.out.println(a + b);
        } else {
            System.out.println("NECASTIGATOR!");
        }
        scanner.close();
    }
}

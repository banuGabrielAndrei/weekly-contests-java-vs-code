package week146;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n >= 0 && n <= 9) {
            System.out.println("Localul A");
        } else if (n >= 10 && n <= 99) {
            System.out.println("Localul B");
        } else {
            System.out.println("Localul C");
        }
        scanner.close();
    }
}

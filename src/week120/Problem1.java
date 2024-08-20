package week120;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        if ((n * n) % 2 == 0) {
            System.out.println(n);
        } else {
            System.out.println(n * 2);
        }
    }
}

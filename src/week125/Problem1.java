package week125;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int[] nr = new int[5];
        for (int i = 1; i < 5; ++i) {
            nr[i] = scanner.nextInt();
        }
        scanner.close();
        for (int i = 4; i >= 1; --i) {
            if (nr[i] % 2 != 0) {
                System.out.print(i + " ");
            }
        }
    }
}

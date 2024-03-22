package week98;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] v = new int[4];
        scanner.close();
        int index = 3;
        while (n != 0) {
            v[index] = n % 10;
            --index;
            n /= 10;
        }
        int pos = -1;
        for (int i = 0; i < 4; ++i) {
            if (v[i] % 3 == 0) {
                pos = i + 1;
                break;
            }
        }
        if (pos == 1) {
            System.out.println("prima");
        } else if (pos == 2) {
            System.out.println("a doua");
        } else if (pos == 3) {
            System.out.println("a treia");
        } else {
            System.out.println("a patra");
        }
    }
}

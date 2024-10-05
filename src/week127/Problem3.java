package week127;

import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int departments = scanner.nextInt();
        int trucks = scanner.nextInt();
        int[] deps = new int[departments];
        int[] trks = new int[trucks];
        for (int i = 0; i < departments; ++i) {
            deps[i] = scanner.nextInt();
        }
        for (int j = 0; j < trucks; ++j) {
            trks[j] = scanner.nextInt();
        }
        scanner.close();
        for (int i = 0; i < trks.length; ++i) {
            int sum = 0;
            boolean ok = false;
            for (int j = 0; j < deps.length; ++j) {
                sum += deps[j];
                if (sum == trks[i]) {
                    ok = true;
                    System.out.println(j + 1);
                    break;
                }
            }
            if (!ok) {
                System.out.println(-1);
            }
        }
    }
}

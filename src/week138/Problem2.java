package week138;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] values = new int[n];
        int[] fr = new int[101];
        for (int i = 0; i < n; ++i) {
            values[i] = scanner.nextInt();
            ++fr[values[i]];
        }
        scanner.close();
        int maxi = -1;
        boolean allEqual = false;
        for (int i = 0; i < 101; ++i) {
            if (fr[i] > maxi) {
                maxi = i;
            }
            if (fr[i] == n) {
                allEqual = true;
            }
        }
        int counter = 0;
        for (int i = 0; i < n; ++i) {
            if (values[i] != maxi) {
                ++counter;
            }
        }
        if (allEqual) {
            System.out.println(0);
            System.out.println("Wunorse, you lucky elf!");
        } else {
            System.out.println(counter);
        }
    }
}
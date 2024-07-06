package week114;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int[] fr = new int[10];
        while (a != 0) {
            ++fr[a % 10];
            a /= 10;
        }
        while (b != 0) {
            ++fr[b % 10];
            b /= 10;
        }
        scanner.close();
        int counter = 0;
        boolean sameFourdigits = false;
        for (int i = 0;i < fr.length; ++i) {
            if (fr[i] == 2) {
                ++counter;
            }
            if (fr[i] == 4) {
                sameFourdigits = true;
            }
        }
        if (counter == 2 || sameFourdigits) {
            System.out.println("SE POATE!");
        } else {
            System.out.println("NU SE POATE!");
        }
    }
}

package week129;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();
        System.out.println(result(a, b));
    }

    public static int result(int a, int b) {
        int validNumbers = 0;
        if (b == 1) {
            validNumbers = 1;
        }
        for (int i = a; i >= 0; --i) {
            int counter = 0;
            int nr = i;
            while (nr != 0) {
                if ((nr % 10) % 2 == 0) {
                    ++counter;
                }
                nr /= 10;
            }
            if (counter == b) {
                ++validNumbers;
            }
        }
        if (b == 0) {
            --validNumbers;
        }
        return validNumbers;
    }
}

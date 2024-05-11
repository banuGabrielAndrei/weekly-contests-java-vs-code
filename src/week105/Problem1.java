package week105;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (inverse(a) > inverse(b)) {
            System.out.println(a);
        } else {
            System.out.println(b);
        }
        scanner.close();
    }

    public static int inverse(int nr) {
        int inverse = 0;
        while (nr != 0) {
            inverse = inverse * 10 + nr % 10;
            nr /= 10;
        }
        return inverse;
    }
}

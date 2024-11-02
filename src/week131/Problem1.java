package week131;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (b == inverseNr(a)) {
            System.out.println("DA");
        } else {
            System.out.println("NU");
        }
        scanner.close();
    }

    public static int inverseNr(int nr) {
        int inverse = 0;
        while (nr != 0) {
            inverse = inverse * 10 + nr % 10;
            nr /= 10;
        }
        return inverse;
    }
}

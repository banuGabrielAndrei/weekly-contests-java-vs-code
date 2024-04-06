package week101;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int nr = scanner.nextInt();
        int lastDigit = nr % 10;
        scanner.close();
        if (lastDigit < 5) {
            while (nr % 10 != 0) {
                --nr;
            }
        } else {
            while (nr % 10 != 0) {
                ++nr;
            }
        }
        System.out.println(nr);
    }
}

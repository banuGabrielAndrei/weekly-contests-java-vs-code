package week145;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int counter = 0;
        while (true) {
            long nr = scanner.nextLong();
            if (nr == x) {
                break;
            } else if (hasZero(nr)) {
                ++counter;
            }
        }
        scanner.close();
        System.out.println(counter);
    }

    public static boolean hasZero(long nr) {
        while (nr > 0) {
            if (nr % 10 == 0) {
                return true;
            }
            nr /= 10;
        }
        return false;
    }
}
package week99;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int nr = scanner.nextInt();
        scanner.close();
        int nr1 = 0;
        int nr2 = 0;
        if (nr % 2 == 0) {
            nr1 = nr - 1;
            nr2 = nr - 3;
        } else {
            nr1 = nr - 2;
            nr2 = nr - 4;
        }
        System.out.println(nr1 + " " + nr2);
    }
}

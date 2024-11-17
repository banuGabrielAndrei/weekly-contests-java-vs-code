package week133;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        Integer n = scanner.nextInt();
        System.out.println(result(n));
        scanner.close();
    }

    public static int result(Integer n) {
        char firstDigit = n.toString().charAt(0);
        if (firstDigit == '1') {
            return 100;
        } else if (firstDigit == '2') {
            return 200;
        } else if (firstDigit == '3') {
            return 300;
        } else if (firstDigit == '4') {
            return 400;
        } else {
            return 500;
        }
    }
}

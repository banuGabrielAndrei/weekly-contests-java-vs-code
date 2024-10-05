package week127;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        Integer x = scanner.nextInt();
        Integer y = scanner.nextInt();
        Integer z = scanner.nextInt();
        boolean digitsX = false;
        boolean digitsZ = false;
        Integer copyY = y;
        int foundDigit = 0;
        while (copyY != 0) {
            int lastDigit = copyY % 10;
            int copyX = x;
            while (copyX != 0) {
                if (lastDigit == copyX % 10) {
                    digitsX = true;
                    foundDigit = lastDigit;
                    break;
                }
                copyX /= 10;
            }
            copyY /= 10;
        }
        while (y != 0) {
            int lastY = y % 10;
            int copyZ = z;
            while (copyZ != 0) {
                if (lastY == copyZ % 10 && lastY != foundDigit) {
                    digitsZ = true;
                    break;
                }
                copyZ /= 10;
            }
            y /= 10;
        }
        scanner.close();
        if (digitsX && digitsZ) {
            System.out.println("SE POATE");
        } else {
            System.out.println("NU SE POATE");
        }
    }
}

package week122;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        Integer a = scanner.nextInt();
        Integer b = scanner.nextInt();
        if (biggestMiddle(a) > biggestMiddle(b)) {
            System.out.println(a);
        } else if (biggestMiddle(b) > biggestMiddle(a)) {
            System.out.println(b);
        } else {
            System.out.println("EGALE!");
        }
        scanner.close();
    }

    public static int biggestMiddle(Integer nr) {
        String number = nr.toString();
        char[] digits = number.toCharArray();
        String middleDigits = "";
        middleDigits += digits[1];
        middleDigits += digits[2];
        return Integer.parseInt(middleDigits);
    }
}

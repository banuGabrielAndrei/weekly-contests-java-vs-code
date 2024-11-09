package week132;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        Integer a = scanner.nextInt();
        if (isPalindrome(a) && a > x) {
            System.out.print(a + " ");
        }
        Integer b = scanner.nextInt();
        if (isPalindrome(b) && b > x) {
            System.out.print(b + " ");
        }
        Integer c = scanner.nextInt();
        if (isPalindrome(c) && c > x) {
            System.out.print(c + " ");
        }
        scanner.close();
    }

    public static boolean isPalindrome(Integer nr) {
        String number = nr.toString();
        StringBuilder inverse = new StringBuilder(number).reverse();
        return inverse.toString().equals(number);
    }
}

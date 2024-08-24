package week121;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int nr = scanner.nextInt();
        System.out.println(changeNr(nr));
        scanner.close();
    }

    public static int changeNr(int nr) {
        String no = Integer.toString(nr);
        char[] digits = no.toCharArray();
        char aux = digits[0];
        digits[0] = digits[digits.length - 1];
        digits[digits.length - 1] = aux;
        return Integer.parseInt(new String(digits));
    }
}

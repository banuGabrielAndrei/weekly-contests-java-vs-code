package week106;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int result = a + 1;
        while (result % 2 != b) {
            ++result;
        }
        System.out.println(result);
        scanner.close();
    }
}

package week147;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int day = scanner.nextInt();
        if (1 <= day && day <= 28) {
            System.out.println("SE POATE");
        } else {
            System.out.println("NU SE POATE");
        }
        scanner.close();
    }
}

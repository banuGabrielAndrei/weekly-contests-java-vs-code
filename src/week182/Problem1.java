package week182;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a= scanner.nextInt();
        int b = scanner.nextInt();
        boolean firstCondition = a < b;
        boolean secondCondition = (b - a) % 2 != 0;
        if (firstCondition && secondCondition) {
            System.out.println("surori");
        } else {
            if (firstCondition) {
                System.out.println(b);
            } else {
                System.out.println(a);
            }
        }
        scanner.close();
    }
}
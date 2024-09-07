package week123;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();
        int sumA = digitsSum(a);
        int sumB = digitsSum(b);
        int counter = 0;
        boolean isValidA = true;
        boolean isValidB = true;
        while (a != 0) {
            if (sumA % (a % 10) != 0) {
                isValidA = false;
                break;
            }
            a /= 10;
        }
        while (b != 0) {
            if (sumB % (b % 10) != 0) {
                isValidB = false;
                break;
            }
            b /= 10;
        }
        if (isValidA) {
            ++counter;
        }
        if (isValidB) {
            ++counter;
        }
        System.out.println(counter);
    }

    public static int digitsSum(int nr) {
        int sum = 0;
        while (nr != 0) {
            sum += nr % 10;
            nr /= 10;
        }
        return sum;
    }
}

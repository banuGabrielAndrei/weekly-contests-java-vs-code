package week129;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int counter = 0;
        while (number != 0) {
            if ((number % 10) == 9) {
                ++counter;
            }
            number /= 10;
        }
        scanner.close();
        System.out.println(counter);
    }
}

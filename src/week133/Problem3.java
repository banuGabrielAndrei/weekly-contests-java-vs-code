package week133;

import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] numbers = line.split(",");
        scanner.close();
        for (int i = numbers.length - 1; i >= 0; --i) {
            if (i == 0) {
                System.out.print(doubleValue(numbers[i]));
            } else {
                System.out.print(doubleValue(numbers[i]) + ",");
            }
        }
    }

    public static Integer doubleValue(String number) {
        return Integer.parseInt(number) * 2;
    }
}

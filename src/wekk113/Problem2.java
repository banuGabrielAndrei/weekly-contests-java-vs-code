package wekk113;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int[] numbers = new int[501];
        int index = 0;
        while (true) {
            numbers[++index] = scanner.nextInt();
            if(isPerfect(numbers[index])) {
                break;
            }
        }
        scanner.close();
        int actSum = 0;
        int lengthAll = index + 1;
        int sumAll = lengthAll * (lengthAll - 1) / 2;
        for (int i = 1; i <= index; ++i) {
            actSum += numbers[i];
        }
        System.out.println(sumAll - actSum);
    }

    public static boolean isPerfect(int nr) {
        if (nr == 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i <= nr / 2; ++i) {
            if (nr % i == 0) {
                sum += i;
            }
        }
        return sum == nr;
    }
}

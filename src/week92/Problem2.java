package week92;

import java.util.Scanner;

public class Problem2 {
    private static final int MIN_TWO_DIGITS = 10;
    private static final int MAX_TWO_DIGITS = 99;
    private static final int MIN_THREE_DIGITS = 100;
    private static final int MAX_THREE_DIGITS = 999;
    private static final int MAX_LENGTH = 10000;

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int[] fr = new int[MAX_LENGTH + 1];
        int x = scanner.nextInt();
        int sum = 0;
        int counterTwoDigits = 0;
        while (true) {
            int number = scanner.nextInt();
            if (number < 0) {
                break;
            }
            if (isTwoDigits(number)) {
                ++fr[number];
            } else if (isThreeDigits(number)) {
                if (number > x) {
                    sum += number;
                }
            }
        }
        scanner.close();
        for (int i = 0; i < MAX_LENGTH + 1; ++i) {
            if (fr[i] > 0) {
                ++counterTwoDigits;
            }
        }
        System.out.println(counterTwoDigits);
        System.out.println(sum);
    }

    public static boolean isTwoDigits(int nr) {
        return nr >= MIN_TWO_DIGITS && nr <= MAX_TWO_DIGITS;
    }

    public static boolean isThreeDigits(int nr) {
        return nr >= MIN_THREE_DIGITS && nr <= MAX_THREE_DIGITS;
    }
}

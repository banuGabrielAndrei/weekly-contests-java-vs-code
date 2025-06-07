import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; ++i) {
            numbers[i] = scanner.nextInt();
        }
        int value = maxSizeSubsequence(numbers);
        int onesFound = 0;
        int zeroCount = 0;
        while (onesFound < value && scanner.hasNextInt()) {
            int b = scanner.nextInt();
            if (b == 1) {
                ++onesFound;
            } else if (b == 0) {
                ++zeroCount;
            }
        }
        scanner.close();
        if (zeroCount == 0) {
            System.out.println("NU AVEM");
        } else {
            System.out.println(zeroCount);
        }
    }

    public static int maxSizeSubsequence(int[] numbers) {
        int max = 0;
        int currentSize = 0;
        for (int i = 0; i < numbers.length; ++i) {
            if (isPrimeAndOdd(numbers[i])) {
                if (currentSize == 0 || numbers[i] > numbers[i - 1]) {
                    ++currentSize;
                } else {
                    currentSize = 1;
                }
                max = Math.max(max, currentSize);
            } else {
                currentSize = 0;
            }
        }
        return max;
    }

    public static boolean isPrimeAndOdd(int number) {
        if (number < 2 || number % 2 == 0) {
            return false;
        }
        for (int div = 2; div * div <= number; ++div) {
            if (number % div == 0) {
                return false;
            }
        }
        return true;
    }
}
package week94;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        scanner.close();
        int maxLength = 2;
        int start = 0;
        int end = 1;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int diff = numbers[j] - numbers[i];
                int currentLength = 2;
                int lastNumber = numbers[j];
                int currentEnd = j;
                for (int k = j + 1; k < n; k++) {
                    if (numbers[k] - lastNumber == diff) {
                        currentLength++;
                        lastNumber = numbers[k];
                        currentEnd = k;
                    }
                }
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    start = i;
                    end = currentEnd;
                }
            }
        }
        System.out.println(maxLength);
        System.out.println((start + 1) + " " + (end + 1));
    }
}

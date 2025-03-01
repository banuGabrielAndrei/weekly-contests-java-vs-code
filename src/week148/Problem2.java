package week148;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sequence = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; ++i) {
            numbers[i] = scanner.nextInt();
        }
        scanner.close();
        List<int[]> seqs = getSequences(numbers, sequence);
        int result = result(seqs);
        System.out.println(result);
    }

    public static List<int[]> getSequences(int[] numbers, int sequence) {
        List<int[]> seqs = new ArrayList<>();
        for (int i = 0; i < numbers.length - sequence + 1; ++i) {
            int[] values = new int[sequence];
            for (int j = 0; j < sequence; ++j) {
                values[j] = numbers[i + j];
            }
            seqs.add(values);
        }
        return seqs;
    }

    public static int result(List<int[]> seqs) {
        int counter = 0;
        for (int[] seq : seqs) {
            int evens = 0;
            int odds = 0;
            for (int value : seq) {
                if (isEven(value)) {
                    ++evens;
                } else {
                    ++odds;
                }
            }
            if (evens == 2 * odds) {
                ++counter;
            }
        }
        return counter;
    }

    public static boolean isEven(int nr) {
        return nr % 2 == 0;
    }
}

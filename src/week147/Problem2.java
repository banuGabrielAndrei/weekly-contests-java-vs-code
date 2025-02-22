package week147;

import java.util.HashMap;
import java.util.Map;
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
        System.out.println(maxAppearPair(numbers));
    }

    public static String maxAppearPair(int[] numbers) {
        Map<String, Integer> pairs = new HashMap<>();
        for (int i = 0; i < numbers.length - 1; ++i) {
            String pair = numbers[i] + " " + numbers[i + 1];
            pairs.put(pair, pairs.getOrDefault(pair, 0) + 1);
        }
        String maxFreqPair = "";
        int maxFreq = 0;
        for (Map.Entry<String, Integer> entry : pairs.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                maxFreqPair = entry.getKey();
            } else if (entry.getValue() == maxFreq) {
                String[] cPair = entry.getKey().split(" ");
                String[] cMaxPair = maxFreqPair.split(" ");
                int cPairFirst = Integer.parseInt(cPair[0]);
                int cPairSecond = Integer.parseInt(cPair[1]);
                int cMaxPairFirst = Integer.parseInt(cMaxPair[0]);
                int cMaxPairSecond = Integer.parseInt(cMaxPair[1]);
                if (cPairFirst < cMaxPairFirst ||
                        (cPairFirst == cMaxPairFirst && cPairSecond < cMaxPairSecond)) {
                    maxFreqPair = entry.getKey();
                }
            }
        }
        return maxFreqPair;
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] pieces = new int[n];
        for (int i = 0; i < n; ++i) {
            pieces[i] = scanner.nextInt();
        }
        scanner.close();
        int answer = minNumberOfSets(pieces);
        System.out.println(answer);
    }

    private static int minNumberOfSets(int[] pieces) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int piece : pieces) {
            frequencyMap.put(piece, frequencyMap.getOrDefault(piece, 0) + 1);
        }
        List<Integer> uniquePieces = new ArrayList<>(frequencyMap.keySet());
        Collections.sort(uniquePieces);
        int ascendingSets = countSetsForOrder(frequencyMap, uniquePieces, true);
        Collections.reverse(uniquePieces);
        int descendingSets = countSetsForOrder(frequencyMap, uniquePieces, false);
        return Math.min(ascendingSets, descendingSets);
    }

    private static int countSetsForOrder(Map<Integer, Integer> freqMap, List<Integer> sortedKeys, boolean ascending) {
        int setsCount = 0;
        for (int i = 0; i < sortedKeys.size(); ++i) {
            int currentValue = sortedKeys.get(i);
            int adjacentValue;
            if (ascending) {
                adjacentValue = currentValue - 1;
            } else {
                adjacentValue = currentValue + 1;
            }
            int currentFreq = freqMap.get(currentValue);
            int adjacentFreq = freqMap.getOrDefault(adjacentValue, 0);

            int newSetsStartingHere = currentFreq - adjacentFreq;
            if (newSetsStartingHere > 0) {
                setsCount += newSetsStartingHere;
            }
        }
        return setsCount;
    }
}
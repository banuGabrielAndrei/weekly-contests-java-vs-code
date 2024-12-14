package week137;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] mt = new int[n + 1][n + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                mt[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        Map<Integer, Integer> values = new HashMap<Integer, Integer>();
        for (int j = 0; j < n; ++j) {
            int sum = 0;
            for (int i = 0; i < n; ++i) {
                sum += mt[i][j];
            }
            values.put(j, sum);
        }
        List<Map.Entry<Integer, Integer>> ent = new ArrayList<>(values.entrySet());
        ent.sort(Map.Entry.comparingByValue());
        for (Map.Entry<Integer, Integer> entry : ent) {
            int column = entry.getKey();
            System.out.print(mt[0][column] + " ");
        }
    }
}
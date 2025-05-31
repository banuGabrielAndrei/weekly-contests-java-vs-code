import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        char[][] board = new char[size][size];
        Map<Character, Integer> pieceCount = new HashMap<>();
        for (int i = 0; i < size; ++i) {
            String line = scanner.next();
            for (int j = 0; j < size; ++j) {
                board[i][j] = line.charAt(j);
                char chessPiece = board[i][j];
                if (chessPiece != '0') {
                    pieceCount.put(chessPiece, pieceCount.getOrDefault(chessPiece, 0) + 1);
                } 
            }
        }
        scanner.close();
        char mostFrequentPiece = '0';
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : pieceCount.entrySet()) {
            if (entry.getValue() > maxCount || (entry.getValue() == maxCount && entry.getKey() > mostFrequentPiece)) {
                maxCount = entry.getValue();
                mostFrequentPiece = entry.getKey();
            }
        }
        if (pieceCount.size() > 0) {
            System.out.println(mostFrequentPiece);
        } else {
            System.out.println(":(");
        }
    }
}
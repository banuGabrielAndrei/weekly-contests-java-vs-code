package CCC.CCC2026.LEVELS.Level3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Level3CCC2026 {

    static final String DIR = "weekly-contests-java-vs-code/src/CCC/CCC2026/LEVELS/Level3";
    static final String INPUT = "in_level-3_0-example.txt";
    static final int[] DR = {0, 0, 1, 1, 0, -1, -1};
    static final int[] DC = {0, 2, 1, -1, -2, -1, 1};

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(DIR, INPUT));
        int n = Integer.parseInt(lines.get(0).trim());

        Path outFile = Path.of(DIR, "output", INPUT.replace("in_", "out_"));
        Files.createDirectories(outFile.getParent());

        try (BufferedWriter out = Files.newBufferedWriter(outFile)) {
            int idx = 1;
            for (int b = 0; b < n; ++b) {
                String[] dims = lines.get(idx).trim().split(" ");
                ++idx;
                int rows = Integer.parseInt(dims[1]);

                char[][] board = new char[rows][];
                for (int r = 0; r < rows; ++r) {
                    board[r] = lines.get(idx).toCharArray();
                    ++idx;
                }
                ++idx;
                int[][] hits = new int[rows][];
                for (int r = 0; r < rows; ++r) {
                    hits[r] = new int[board[r].length];
                }

                for (int er = 0; er < rows; ++er) {
                    for (int ec = 0; ec < board[er].length; ++ec) {
                        char ch = board[er][ec];
                        if (ch < '1' || ch > '6') {
                            continue;
                        }
                        int dir = ch - '0';
                        int r = er + DR[dir], c = ec + DC[dir];
                        while (r >= 0 && r < rows && c >= 0 && c < board[r].length) {
                            if (board[r][c] == 'O' || board[r][c] == 'M') {
                                ++hits[r][c];
                            }
                            r += DR[dir];
                            c += DC[dir];
                        }
                    }
                }

                for (int r = 0; r < rows; ++r) {
                    for (int c = 0; c < board[r].length; ++c) {
                        if (hits[r][c] == 2) {
                            board[r][c] = 'M';
                        } else if (hits[r][c] >= 3) {
                            board[r][c] = 'O';
                        }
                    }
                    out.write(board[r]);
                    out.newLine();
                }
                out.newLine();
            }
        }
    }
}

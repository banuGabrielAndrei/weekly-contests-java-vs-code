package CCC.CCC2026.LEVELS.Level2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Level2CCC2026 {

    static final String DIR = "weekly-contests-java-vs-code/src/CCC/CCC2026/LEVELS/Level2";
    static final String INPUT = "in_level-2_0-example.txt";
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
                int er = -1, ec = -1, dir = 0;
                for (int r = 0; r < rows; ++r) {
                    board[r] = lines.get(idx).toCharArray();
                    ++idx;
                    for (int c = 0; c < board[r].length; ++c) {
                        char ch = board[r][c];
                        if (ch >= '1' && ch <= '6') {
                            er = r; ec = c; dir = ch - '0'; 
                        }
                    }
                }
                ++idx;
                int r = er + DR[dir], c = ec + DC[dir];
                while (r >= 0 && r < rows && c >= 0 && c < board[r].length) {
                    if (board[r][c] == 'O') board[r][c] = 'M';
                    r += DR[dir];
                    c += DC[dir];
                }

                for (char[] row : board) {
                    out.write(row);
                    out.newLine();
                }
                out.newLine();
            }
        }
    }
}

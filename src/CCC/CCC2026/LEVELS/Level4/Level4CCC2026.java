package CCC.CCC2026.LEVELS.Level4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Level4CCC2026 {

    static final String DIR = "weekly-contests-java-vs-code/src/CCC/CCC2026/LEVELS/Level4";
    static final String INPUT = "in_level-4_0-example.txt";
    static final int[] DR = {0, 0, 1, 1, 0, -1, -1};
    static final int[] DC = {0, 2, 1, -1, -2, -1, 1};

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(DIR, INPUT));
        int n = Integer.parseInt(lines.get(0).trim());

        Path outFile = Path.of(DIR, "output", INPUT.replace("in_", "out_"));
        Files.createDirectories(outFile.getParent());
        try (BufferedWriter out = Files.newBufferedWriter(outFile)) {
            int idx = 1;
            for (int p = 0; p < n; ++p) {
                int radius = Integer.parseInt(lines.get(idx).trim());
                ++idx;
                int ticks = Integer.parseInt(lines.get(idx).trim());
                ++idx;

                char[][] board = emptyBoard(radius);
                int[][] ring = border(radius);
                int[] pos = {0, 5 * radius, 0, radius, 2 * radius, 3 * radius, 4 * radius};
                boolean[] active = new boolean[7];
                int[][] hits = new int[board.length][];
                for (int r = 0; r < board.length; ++r) {
                    hits[r] = new int[board[r].length];
                }
                List<int[]> touched = new ArrayList<>();
                for (int t = 0; t < ticks; ++t) {
                    String[] ins = lines.get(idx).trim().split("\\s+");
                    ++idx;
                    for (int e = 1; e <= 6; ++e) {
                        char op = ins[e - 1].charAt(0);
                        if (op == 'F') {
                            pos[e] = (pos[e] + 1) % ring.length;
                        }
                        else if (op == 'B') {
                            pos[e] = (pos[e] + ring.length - 1) % ring.length;
                        }
                        else if (op == 'T') {
                            active[e] = !active[e];
                        }
                    }
                    for (int e = 1; e <= 6; ++e) {
                        if (!active[e]) {
                            continue;
                        }
                        int r = ring[pos[e]][0] + DR[e], c = ring[pos[e]][1] + DC[e];
                        while (r >= 0 && r < board.length && c >= 0 && c < board[r].length) {
                            if (board[r][c] == 'O' || board[r][c] == 'M') {
                                if (hits[r][c] == 0) {
                                    touched.add(new int[]{r, c});
                                }
                                ++hits[r][c];
                            }
                            r += DR[e];
                            c += DC[e];
                        }
                    }
                    for (int[] cell : touched) {
                        int r = cell[0], c = cell[1];
                        if (hits[r][c] == 2) board[r][c] = 'M';
                        else if (hits[r][c] >= 3) board[r][c] = 'O';
                        hits[r][c] = 0;
                    }
                    touched.clear();
                }
                ++idx;

                for (char[] row : board) {
                    out.write(row);
                    out.newLine();
                }
                out.newLine();
            }
        }
    }

    // Same drawing as Level 1
    static char[][] emptyBoard(int radius) {
        char[][] board = new char[2 * radius + 1][];
        for (int row = 0; row <= 2 * radius; ++row) {
            int d = Math.abs(row - radius);
            int symbols = 2 * radius + 1 - d;
            char[] line = new char[4 * radius + 1];
            Arrays.fill(line, '-');
            for (int k = 0; k < symbols; ++k) {
                boolean border = d == radius || k == 0 || k == symbols - 1;
                line[d + 2 * k] = border ? '*' : 'O';
            }
            board[row] = line;
        }
        return board;
    }

    static int[][] border(int radius) {
        List<int[]> ring = new ArrayList<>();
        for (int k = 0; k <= radius; ++k) {
            ring.add(new int[]{0, radius + 2 * k});
        }
        for (int r = 1; r <= 2 * radius; ++r) {
            ring.add(new int[]{r, r <= radius ? 3 * radius + r : 5 * radius - r});
        }
        for (int k = 1; k <= radius; ++k) {
            ring.add(new int[]{2 * radius, 3 * radius - 2 * k});
        }
        for (int r = 2 * radius - 1; r >= 1; --r) {
            ring.add(new int[]{r, Math.abs(r - radius)});
        }
        return ring.toArray(new int[0][]);
    }
}

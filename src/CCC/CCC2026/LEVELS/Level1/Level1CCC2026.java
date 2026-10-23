package CCC.CCC2026.LEVELS.Level1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Level1CCC2026 {

    static final String DIR = "weekly-contests-java-vs-code/src/CCC/CCC2026/LEVELS/Level1";
    static final String INPUT = "in_level-1_0-example.txt";

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(DIR, INPUT));
        int n = Integer.parseInt(lines.get(0).trim());

        Path outFile = Path.of(DIR, "output", INPUT.replace("in_", "out_"));
        Files.createDirectories(outFile.getParent());
        
        try (BufferedWriter out = Files.newBufferedWriter(outFile)) {
            for (int i = 1; i <= n; ++i) {
                int r = Integer.parseInt(lines.get(i).trim());
                for (int row = 0; row <= 2 * r; ++row) {
                    int d = Math.abs(row - r);
                    int symbols = 2 * r + 1 - d;
                    StringBuilder sb = new StringBuilder();
                    sb.append("-".repeat(d));
                    for (int k = 0; k < symbols; ++k) {
                        if (k > 0) {
                            sb.append('-');
                        }
                        boolean border = d == r || k == 0 || k == symbols - 1;
                        sb.append(border ? '*' : 'O');
                    }
                    sb.append("-".repeat(d));   
                    out.write(sb.toString());
                    out.newLine();
                }
                out.newLine();
            }
        }
    }
}

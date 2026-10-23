package WellCode.week234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Problem3 {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("weekly-contests-java-vs-code/src/WellCode/week234/input.txt");
        BufferedReader in = Files.exists(file) ? Files.newBufferedReader(file) : new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        String line;
        while ((line = in.readLine()) != null) {
            out.append(processTextLine(line)).append('\n');
        }
        System.out.print(out);
    }

    static String processTextLine(String line) {
        // Numbers on the line: String[] p = line.trim().split(" "); int a = Integer.parseInt(p[0]);
        // Use Long.parseLong for values above 2 billion.
        return line;
    }
}

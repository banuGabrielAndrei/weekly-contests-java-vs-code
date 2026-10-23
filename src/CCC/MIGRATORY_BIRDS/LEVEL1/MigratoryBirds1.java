package CCC.MIGRATORY_BIRDS.LEVEL1;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MigratoryBirds1 {

    static final String DIR = "/home/gabrielbanu/Documents/JavaCCC/weekly-contests-java-vs-code/src/CCC/MIGRATORY_BIRDS/LEVEL1/";

    // Change only this line per level (e.g. "in_level-1_3-c.txt"). null = stdin/stdout for submission.
    // Output goes to "Problem output/out_..." with the same suffix as the input.
    static final String INPUT = "in_level-1_5-e.txt";

    record Bop(int id, int temp, int humidity) {}

    // The data contains the word "seventeen" instead of 17 (only word present in all files).
    static int num(String s) {
        return s.equals("seventeen") ? 17 : Integer.parseInt(s.trim());
    }

    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        OutputStream os = System.out;
        if (INPUT != null) {
            in = new FileInputStream(DIR + "Problem input/" + INPUT);
            Path outFile = Paths.get(DIR + "Problem output/" + INPUT.replaceFirst("^in_", "out_"));
            Files.createDirectories(outFile.getParent());
            os = new FileOutputStream(outFile.toFile());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));

       List<Bop> bops = new ArrayList<>();
       
       String line;
       while ((line = br.readLine()) != null)  {
        String[] parts = line.split(",");
        bops.add(new Bop(num(parts[0]), num(parts[1]), num(parts[2])));
       }

       bops.sort(Comparator.comparingInt(Bop::temp).reversed()
            .thenComparingInt(Bop::humidity)
            .thenComparingInt(Bop::id));

       StringJoiner sj = new StringJoiner(" ");
       for (Bop b : bops) {
           sj.add(String.valueOf(b.id()));
       }
       out.println(sj);

        out.flush();
        out.close();
        br.close();
    }
}

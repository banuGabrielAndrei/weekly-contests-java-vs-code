package CCC.MIGRATORY_BIRDS.LEVEL2;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MigratoryBirds2 {

    static final String DIR = "/home/gabrielbanu/Documents/JavaCCC/weekly-contests-java-vs-code/src/CCC/MIGRATORY_BIRDS/LEVEL2/";

    // Change only this line per level (e.g. "in_level-2_3-c.txt"). Output: "Problem output/out_..." (same suffix).
    static final String INPUT = "in_level-2_1-a.txt";

    // Same data as level 1 (temperature in °C after fixing, humidity), looked up by BOP id.
    static final String LEVEL1_DATA = DIR + "Problem input/all_data_from_level_1.in";

    // score == null means "missing" (this is what we predict).
    record Row(int id, double vegetation, double insects, double urbanLight, Double score) {}

    record Weather(double temp, double humidity) {}

    public static void main(String[] args) throws IOException {
        Map<Integer, Weather> weather = readWeather(LEVEL1_DATA);

        List<Row> target = readRows(DIR + "Problem input/" + INPUT);

        // Train on every known score from all level 2 files (file c has no known scores of its own).
        List<Row> training = new ArrayList<>();
        try (DirectoryStream<Path> files = Files.newDirectoryStream(Paths.get(DIR + "Problem input/"), "in_level-2_*.txt")) {
            for (Path f : files) {
                for (Row r : readRows(f.toString())) {
                    if (r.score() != null) {
                        training.add(r);
                    }
                }
            }
        }
        double[] w = trainLinear(training, weather);

        Path outFile = Paths.get(DIR + "Problem output/" + INPUT.replaceFirst("^in_", "out_"));
        Files.createDirectories(outFile.getParent());
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outFile.toFile())))) {
            out.println("BOP,Bird Love Score [<3]");
            for (Row r : target) {
                if (r.score() == null) {
                    out.println(r.id() + "," + String.format(Locale.US, "%.2f", predict(w, features(r, weather))));
                }
            }
        }
    }

    // id -> (temperature in °C, humidity). Temperatures >= 50 are really °F (real °C values stay below ~46).
    static Map<Integer, Weather> readWeather(String file) throws IOException {
        Map<Integer, Weather> m = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // header
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] p = line.split(",");
                double t = num(p[1]);
                if (t >= 50) {
                    t = (t - 32) * 5 / 9;
                }
                m.put(Integer.parseInt(p[0]), new Weather(t, num(p[2])));
            }
        }
        return m;
    }

    // Reads a level 2 file: BOP,Vegetation,Insects,Urban Light,Bird Love Score (or "missing").
    static List<Row> readRows(String file) throws IOException {
        List<Row> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // header
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] p = line.split(",");
                Double score = p[4].trim().equals("missing") ? null : Double.valueOf(p[4]);
                rows.add(new Row(Integer.parseInt(p[0]), num(p[1]), num(p[2]), num(p[3]), score));
            }
        }
        return rows;
    }

    // The data contains the word "seventeen" instead of 17 in some level 1 files.
    static double num(String s) {
        return s.trim().equals("seventeen") ? 17 : Double.parseDouble(s.trim());
    }

    // [1, vegetation, insects, urban light, temperature, humidity]
    static double[] features(Row r, Map<Integer, Weather> weather) {
        Weather w = weather.get(r.id());
        return new double[] {1, r.vegetation(), r.insects(), r.urbanLight(), w.temp(), w.humidity()};
    }

    static double predict(double[] w, double[] x) {
        double s = 0;
        for (int i = 0; i < w.length; i++) {
            s += w[i] * x[i];
        }
        return s;
    }

    // Ordinary least squares: solve (X^T X) w = X^T y.
    static double[] trainLinear(List<Row> rows, Map<Integer, Weather> weather) {
        int n = 6;
        double[][] a = new double[n][n + 1]; // augmented matrix [X^T X | X^T y]
        for (Row r : rows) {
            double[] x = features(r, weather);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] += x[i] * x[j];
                }
                a[i][n] += x[i] * r.score();
            }
        }
        return gauss(a);
    }

    // Gauss-Jordan elimination with partial pivoting on an n x (n+1) augmented matrix.
    static double[] gauss(double[][] a) {
        int n = a.length;
        for (int c = 0; c < n; c++) {
            int p = c;
            for (int r = c + 1; r < n; r++) {
                if (Math.abs(a[r][c]) > Math.abs(a[p][c])) p = r;
            }
            double[] tmp = a[c]; a[c] = a[p]; a[p] = tmp;
            for (int r = 0; r < n; r++) {
                if (r == c) continue;
                double f = a[r][c] / a[c][c];
                for (int k = c; k <= n; k++) {
                    a[r][k] -= f * a[c][k];
                }
            }
        }
        double[] w = new double[n];
        for (int i = 0; i < n; i++) {
            w[i] = a[i][n] / a[i][i];
        }
        return w;
    }
}

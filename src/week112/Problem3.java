package week112;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week112\\input.txt"));
        List<String> text = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            text.add(line);
        }
        scanner.close();
        for (String line : text) {
            System.out.println(line);
        }
    }
}

package week126;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\input.txt"));
        List<String> words = new ArrayList<>();
        int lines = 0;
        String word = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ++lines;
            if (lines == 1) {
                word += line.toUpperCase();
            } else {
                words.add(line);
            }
        }
        scanner.close();
        int counter = 0;
        for (int i = 0; i < words.size(); ++i) {
            int pos = words.get(i).indexOf(word);
            while (pos != -1) {
                ++counter;
                pos = words.get(i).indexOf(word, pos + word.length());
            }
        }
        System.out.println(counter);
    }
}

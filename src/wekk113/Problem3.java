package wekk113;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException{
        String[] lines = new String[1001];
        Set<String> res = new LinkedHashSet();
        int index = 0;
        var scanner = new Scanner(new File("src\\wekk113\\input.txt"));
        while (scanner.hasNextLine()) {
            lines[++index] = scanner.nextLine().toLowerCase();
        }
        scanner.close();
        String word = lines[1];
        for (int i = 2; i <= index; ++i) {
            String line = lines[i];
            String[] words = line.split(" ");
            for (int j = 0; j < words.length; ++j) {
                if (word.contains(words[j])) {
                    res.add(words[j]);
                }
            }
        }
        if (res.isEmpty()) {
            System.out.println("NU EXISTA!");
        } else {
        for (String wrd : res) {
            System.out.print(wrd + " ");
        }
    }
        
    }
}

package week153;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        scanner.close();
        System.out.println(displayNoSeq(text)); 
    }

    public static int displayNoSeq(String text) {
        Set<String> sequences = new HashSet<>(); 
        for (int i = 0; i < text.length() - 1; ++i) {
            String sequence = text.substring(i, i + 2); 
            sequences.add(sequence); 
        }
        return sequences.size(); 
    }
}

/*
 * ONLY C++ SOLUTION ACCEPTED
 * 
 * int displayNoSeq(char text[]) {
    set<string> sequences; 
    int length = strlen(text); 
    for (int i = 0; i < length - 1; ++i) {
        string sequence = string(1, text[i]) + text[i + 1]; 
        sequences.insert(sequence); 
    }
    return sequences.size();
}
 * 
 */

package week156;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException{
        var scanner= new Scanner(new File("src\\input.txt"));
        List<String> words = new ArrayList<>();
        while (scanner.hasNextLine()){
            String line  = scanner.nextLine().toLowerCase();
            words.addAll(getWords(line));
        }
        scanner.close();
        int result = 0;
        for (String word : words) {
            if (!word.isEmpty()) {
                if (isValidWord(word)) {
                    ++result;
                }
            }
        }
        System.out.println(result);
    }

    public static List<String> getWords(String line){
        String[] words = line.split("[^a-zA-Z]+");
        return List.of(words);
    }

    public static boolean isValidWord(String word) {
        Set<Character> letters = new HashSet<>();
        for (char ch: word.toCharArray()) {
            letters.add(ch);
            if (letters.size() > 2) {
                return false;
            }
        }
        return true;
    }
}

/*STATEMENT
 * Write a program that reads a text file and counts the number of words that contain at most two different letters.
 * For example, the words "aa", "bb", "cc", "aabb", "abab" are valid, while "abc", "aabbc" are not.

Date de intrare	Date de ieșire
Abc.Aaa.BbCc
123rgH,i-HZZ	4
 */

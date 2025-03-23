package week151;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\input.txt"));
        String text = "";
        while (scanner.hasNext()) {
            text += scanner.nextLine();
        }
        scanner.close();
        int maxLength = 0;
        String result = "";
        List<String> words = words(text);
        boolean found = false;
        for (String word : words) {
            if (isValidWord(word)) {
                found = true;
                if (word.length() > maxLength || word.length() == maxLength && word.compareTo(result) < 0) {
                    maxLength = word.length();
                    result = word;
                }
            }
        }
        if (found) {
            System.out.println(result);
        } else {
            System.out.println("Ist nicht vorhanden!");
        }
    }

    public static List<String> words(String text) {
        List<String> words = new ArrayList<>();
        String[] wrds = text.split("[^a-zA-Z]");
        for (String wrd : wrds) {
            if (!wrd.isEmpty()) {
                words.add(wrd);
            }
        }
        return words;
    }

    public static boolean isValidWord(String word) {
        int[] freq = new int['z' + 1];
        for (int i = 0; i < word.length(); ++i) {
            ++freq[word.charAt(i)];
        }
        for (int j = 0; j < freq.length; ++j) {
            if (freq[j] > 1) {
                return false;
            }
        }
        return true;
    }
}

/*
 * PROBLEM STAEMENT
 * Academicienii germani din departamentul de filosofie folosesc foarte des
 * sintagma 'absurditatea universului', motiv pentru care s-au gândit să creeze
 * un cuvânt care să reprezinte acest concept.
 * Unul din ei a avut ideea de a crea acest cuvânt într-un mod absurd. Practic,
 * vor genera un text aleatoriu, iar apoi vor alege din el cel mai lung cuvânt
 * care are toate literele distincte.
 * Nepunându-și problema că acel cuvânt nu va putea fi pronunțat, te-au rugat pe
 * tine să faci selecția automată a noului cuvânt din vocabularul german.
 * 
 * Cerință
 * Se dă un text ce poate fi situat pe mai multe linii, reprezentând textul
 * inițial propus de filosofii Academiei Germane.
 * Să se determine cel mai lung cuvânt care are toate literele distincte, acesta
 * fiind noul cuvânt din dicționarul german care se va traduce drept
 * 'absurditatea universului'.
 * Dacă nu există niciun cuvânt cu toate literele distincte se va afișa Ist
 * nicht vorhanden!.
 * 
 * Date de intrare
 * De pe una sau mai multe linii se citește textul propus de academicienii
 * germani.
 * 
 * Date de ieșire
 * Se va afișa pe prima linie cel mai lung cuvânt din text care are toate
 * literele distincte, respectiv noul cuvânt din dicționar.
 * Dacă nu există niciun cuvânt cu toate literele distincte se va afișa mesajul
 * Ist nicht vorhanden!.
 * 
 * Restricții și precizări
 * 1 ≤ lungimea textului ≤ 2000
 * 1 ≤ lungimea unui cuvânt ≤ 2000
 * Un cuvânt este reprezentat de o succesiune de litere mici și mari ale
 * alfabetului englez
 * Textul poate conține litere mici și mari ale alfabetului englez, simboluri,
 * semne de punctuație și spații
 * În cazul în care există mai multe astfel de cuvinte, să se afișeze cel mai
 * mic din punct de vedere lexicografic
 * Consideram că 'a' != 'A', 'b' != 'B', … , 'z' != 'Z'
 * Exemplu
 * Date de intrare Date de ieșire
 * Aabcadefg…abcd
 * !xyzd!!! xyxzd
 *
 */

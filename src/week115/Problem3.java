package week115;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException{
        var scanner = new Scanner(new File("src\\week115\\input.txt"));
        int maxi = 0;
        int min = 1001;
        String mini = "";
        List<String> text = new ArrayList<>();
        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            line += " ";
            String aux = "";
            for (int i = 0; i < line.length(); ++i) {
                if (Character.isLetter(line.charAt(i))) {
                    aux += line.charAt(i);
                } else {
                    if (aux.length() > maxi) {
                        maxi = aux.length();
                    }
                    if (aux.length() <= min && !aux.isEmpty()) {
                        min = aux.length();
                        mini = aux;
                    }
                    aux = "";
                }
            }
            text.add(line);
        }
        scanner.close();
        for (int i = 0; i < text.size(); ++i) {
            String aux = "";
            StringBuilder modLine = new StringBuilder();
            String ln = text.get(i);
            for (int j = 0; j < ln.length(); ++j) {
                if (Character.isLetter(ln.charAt(j))) {
                    aux += ln.charAt(j);
                } else {
                    if (!aux.isEmpty() && aux.length() == maxi) {
                        modLine.append(mini);
                    } else {
                        modLine.append(aux);
                    }
                    modLine.append(ln.charAt(j));
                    aux = "";
                }
            }
            text.set(i, modLine.toString());
            System.out.println(text.get(i));
        }
    }
}

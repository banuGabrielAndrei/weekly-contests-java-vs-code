package week112;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException{
        var scanner = new Scanner(new File("src\\week112\\input.txt"));
        List<String> lines = new ArrayList<>();
        String aux = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
            aux += line;
        }
        aux += ".";
        scanner.close();
        long maxi = Long.MIN_VALUE;
        long nr = 0;
        boolean negative = false;
        boolean haveNumber = false;
        for (int i = 0; i < aux.length(); ++i) {
            if (i > 0 && Character.isDigit(aux.charAt(i)) && aux.charAt(i - 1) == '-') {
                negative = true;
            } else if(Character.isDigit(aux.charAt(i))) {
                haveNumber = true;
                nr = nr * 10 + aux.charAt(i) - '0';
            } else {
                if (negative) {
                    nr *= -1;
                }
                if (nr > maxi) {
                    maxi = nr;
                }
                negative = false;
                nr = 0;
            }
        } 
        if (!haveNumber) {
            System.out.println(lines.get(0));
        } else {
            for (int i = 0; i < lines.size(); ++i) {
                System.out.println(lines.get(i));
            }
        }
    }
}

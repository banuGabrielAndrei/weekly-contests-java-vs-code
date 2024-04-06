package week99;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week99\\input.txt"));
        int numbersCounter = 0;
        int evenCounter = 0;
        int oddCounter = 0;
        String prefix = "0";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line += "!";
            String aux = "";
            for (int i = 0; i < line.length(); ++i) {
                if (isDigit(line.charAt(i))) {
                    aux += line.charAt(i);
                } else {
                    if (aux.startsWith(prefix) && aux.length() == 1) {
                        ++numbersCounter;
                        ++evenCounter;
                        aux = "";
                    } else if (!aux.startsWith(prefix) && aux.length() > 0) {
                        int lastDigit = Integer.parseInt(String.valueOf(aux.charAt(aux.length() - 1)));
                        if (lastDigit % 2 == 0) {
                            ++evenCounter;
                        } else {
                            ++oddCounter;
                        }
                        ++numbersCounter;
                        aux = "";
                    }
                }
            }
        }
        scanner.close();
        System.out.println(numbersCounter);
        System.out.println(evenCounter + " " + oddCounter);
    }

    public static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }
}
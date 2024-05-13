package week105;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException {
        var scanner = new Scanner(new File("src\\week105\\input.txt"));
        int cntRight = 0;
        int cntLeft = 0;
        int cntUp = 0;
        int cntDown = 0;
        int posRightLeft = 0;
        int posUpDown = 0;
        int cntCheckpoint = 0;
        boolean backToStart = false;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().toLowerCase();
            line += '.';
            String aux = "";
            for (int i = 0; i < line.length(); ++i) {
                if (isLetter(line.charAt(i))) {
                    aux += line.charAt(i);
                } else {
                    if (aux.equals("dreapta")) {
                        ++cntRight;
                        ++posRightLeft;
                        if (checkPosition(posRightLeft, posUpDown)) {
                            backToStart = true;
                            ++cntCheckpoint;
                        }
                    } else if (aux.equals("stanga")) {
                        ++cntLeft;
                        --posRightLeft;
                        if (checkPosition(posRightLeft, posUpDown)) {
                            backToStart = true;
                            ++cntCheckpoint;
                        }
                    } else if (aux.equals("sus")) {
                        ++cntUp;
                        ++posUpDown;
                        if (checkPosition(posRightLeft, posUpDown)) {
                            backToStart = true;
                            ++cntCheckpoint;
                        }
                    } else if (aux.equals("jos")) {
                        ++cntDown;
                        --posUpDown;
                        if (checkPosition(posRightLeft, posUpDown)) {
                            backToStart = true;
                            ++cntCheckpoint;
                        }
                    }
                    aux = "";
                }
            }
        }
        scanner.close();
        if (backToStart) {
            System.out.println("DA");
            System.out.println(cntCheckpoint);
        } else {
            System.out.println("NU");
            System.out.println(cntLeft + " " + cntRight + " " + cntUp + " " + cntDown);
        }
    }

    public static boolean checkPosition(int leftRight, int upDown) {
        return leftRight == 0 && upDown == 0;
    }

    public static boolean isLetter(char ch) {
        return 'a' <= ch && ch <= 'z';
    }
}

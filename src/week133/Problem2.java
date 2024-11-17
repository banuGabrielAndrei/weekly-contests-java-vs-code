package week133;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int[][] mt = new int[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                mt[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        int result = checkWin(mt);
        if (result != 3 && result != 2) {
            System.out.println("Jucatorul " + result);
        } else if (gameNotOver(mt)) {
            System.out.println("Continua");
        } else {
            System.out.println("Egal");
        }
    }

    public static int checkWin(int mt[][]) {
        for (int i = 0; i < 3; ++i) {
            if (mt[i][0] == mt[i][1] && mt[i][0] == mt[i][2]) {
                return mt[i][0];
            }
        }
        for (int j = 0; j < 3; ++j) {
            if (mt[0][j] == mt[1][j] && mt[0][j] == mt[2][j]) {
                return mt[0][j];
            }
        }

        if (mt[0][0] == mt[1][1] && mt[0][0] == mt[2][2]) {
            return mt[0][0];
        }

        if (mt[0][2] == mt[1][1] && mt[0][2] == mt[2][0]) {
            return mt[0][2];
        }
        return 3;
    }

    public static boolean gameNotOver(int mt[][]) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (mt[i][j] == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}

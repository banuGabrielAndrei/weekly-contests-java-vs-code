package week119;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int[] binary = new int[4];
        int counter1 = 0;
        for (int i = 0; i < 4; ++i) {
            binary[i] = scanner.nextInt();
            if (binary[i] == 1) {
                ++counter1;
            }
        }
        scanner.close();
        boolean isBinary = true;
        for (int i = 0; i < 3; ++i) {
            if (binary[i] == binary[i + 1]) {
                isBinary = false;
            }
        }
        if (isBinary) {
            System.out.println("zig-zag!");
        } else {
            System.out.println(counter1);
        }
    }
}

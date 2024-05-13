package week105;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int[] fr = new int[20001];
        int[] frNeg = new int[20001];
        int freq = 0;
        for (int pos = 1; freq != 4; ++pos) {
            int nr = scanner.nextInt();
            if (nr < 0) {
                ++frNeg[-nr];
                freq = frNeg[-nr];
            } else {
                ++fr[nr];
                freq = fr[nr];
            }
            if (freq % 2 != 0) {
                System.out.print(pos + " ");
            }
        }
        scanner.close();
    }
}

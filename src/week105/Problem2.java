package week105;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int[] n = new int[101];
        int index = 0;
        int[] fr = new int[2001];
        while (true) {
            int nr = scanner.nextInt();
            if (index > 0 && fr[Math.abs(n[index - 1])] % 2 != 0) {
                System.out.print(index + " ");
            }
            ++fr[Math.abs(nr)];
            n[index] = nr;
            ++index;
            if (fr[Math.abs(nr)] == 4) {
                break;
            }
        }
        scanner.close();
    }
}

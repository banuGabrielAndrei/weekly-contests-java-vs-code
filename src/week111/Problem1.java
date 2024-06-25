package week111;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();
        if (z >= x && z <= y) {
            System.out.println("NICIO PROBLEMA!");
        } else {
            System.out.println("NU REZISTA!");
        }
        scanner.close();
    }
}

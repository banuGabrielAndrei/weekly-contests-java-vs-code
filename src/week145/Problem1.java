package week145;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int ageAndrei = scanner.nextInt();
        int ageMihai = scanner.nextInt();
        if (ageAndrei > ageMihai) {
            System.out.println("Andrei");
            System.out.println(ageAndrei - ageMihai);
        } else {
            System.out.println("Mihai");
            System.out.println(ageMihai - ageAndrei);
        }
        scanner.close();
    }
}

package week127;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int firstNumber = 0;
        int biggestNr = Integer.MIN_VALUE;
        int smallestNr = Integer.MAX_VALUE;
        int counter = 0;
        while (true) {
            ++counter;
            int number = scanner.nextInt();
            if (counter == 1) {
                firstNumber = number;
                if (number > biggestNr) {
                    biggestNr = number;
                }
                if (number < smallestNr) {
                    smallestNr = number;
                }
            } else {
                if (number > biggestNr) {
                    biggestNr = number;
                }
                if (number < smallestNr) {
                    smallestNr = number;
                }
                int diff = Math.abs(firstNumber - number);
                if (diff == biggestNr) {
                    break;
                }
            }

        }
        scanner.close();
        System.out.println(smallestNr);
    }
}

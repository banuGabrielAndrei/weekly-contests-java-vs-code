package week103;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        List<Integer> v3 = new ArrayList<>();
        List<Integer> v5 = new ArrayList<>();
        List<Integer> v7 = new ArrayList<>();
        
        while (true) {
            int nr = scanner.nextInt();
            if (nr % 3 == 0 || nr % 5 == 0 || nr % 7 == 0) {
                if (nr % 3 == 0) {
                    v3.add(nr);
                }
                if (nr % 5 == 0) {
                    v5.add(nr);
                }
                if (nr % 7 == 0) {
                    v7.add(nr);
                }
            } else {
                break;
            }
        }
        scanner.close();
        Collections.sort(v3);
        Collections.sort(v5);
        Collections.sort(v7);
        for (int num : v3) {
            System.out.print(num + " ");
        }
        for (int num : v5) {
            System.out.print(num + " ");
        }
        for (int num : v7) {
            System.out.print(num + " ");
        }
    }
}

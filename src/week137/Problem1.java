package week137;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();
        List<Integer> values = new ArrayList<>();
        int first = Math.abs(a - b);
        values.add(first);
        int second = Math.abs(a - c);
        values.add(second);
        int third = Math.abs(b - c);
        values.add(third);
        Boolean ok = false;
        for (int nr : values) {
            if (nr > 5) {
                ok = true;
                break;
            }
        }
        if (ok) {
            System.out.println("NU");
        } else {
            System.out.println("DA");
        }
    }
}

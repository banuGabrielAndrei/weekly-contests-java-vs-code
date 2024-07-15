package week115;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        List<Integer> values = new ArrayList<>();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();
        values.add(a);
        values.add(b);
        values.add(c);
        Collections.sort(values);
        System.out.println(values.get(1) + values.get(2));
    }
}

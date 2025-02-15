package week146;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Problem2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int numbers = scanner.nextInt();
        int price = scanner.nextInt();
        List<Integer> values = new ArrayList<>();
        int sum = 0;
        while (true) {
            if (sum == price || values.size() == numbers) {
                break;
            }
            int nr = scanner.nextInt();
            sum += nr;
            values.add(nr);
        }
        scanner.close();
        List<Integer> result = firstAndLastEvenNumbers(values);
        for (int i = 0; i < result.size(); ++i) {
            System.out.print(result.get(i) + " ");
        }
    }

    public static List<Integer> firstAndLastEvenNumbers(List<Integer> values) {
        List<Integer> result = new ArrayList<>();
        boolean found = false;
        int last = 0;
        for (int i = 0; i < values.size(); ++i) {
            if (!found && values.get(i) % 2 == 0) {
                result.add(values.get(i));
                found = true;
            }
            if (values.get(i) % 2 == 0) {
                last = values.get(i);
            }
        }
        result.add(last);
        return result;
    }
}

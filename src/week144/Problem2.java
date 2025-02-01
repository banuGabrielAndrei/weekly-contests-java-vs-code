package week144;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Set<Integer> detainees = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            detainees.add(scanner.nextInt());
        }
        scanner.close();
        boolean allPresent = true;
        for (int i = 1; i <= n; ++i) {
            if (!detainees.contains(i)) {
                allPresent = false;
                break;
            }
        }
        if (allPresent) {
            System.out.println("Da");
        } else {
            System.out.println("Nu");
        }
    }
}

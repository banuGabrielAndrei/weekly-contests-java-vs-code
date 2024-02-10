package week93;

import java.util.Scanner;

public class Problem2 {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int numbers = scanner.nextInt();
        int sum = 0;
        int index = 0;
        int[] v = new int[2001];
        for (int i = 1; i <= numbers; ++i) {
            int nr = scanner.nextInt();
            if (isPrime(nr)) {
                numbers += nr;
            } else {
                v[index++] = nr;
            }
        }
        scanner.close();
        for (int j = 0; j < index; ++j) {
            sum += v[j];
        }
        System.out.println(sum);
    }

    public static boolean isPrime(int nr) {
        if (nr <= 1) {
            return false;
        }
        for (int div = 2; div * div <= nr; ++div) {
            if (nr % div == 0) {
                return false;
            }
        }
        return true;
    }
}

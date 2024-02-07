import java.util.Arrays;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();
        int[] v = new int[3];
        v[0] = a;
        v[1] = b;
        v[2] = c;
        Arrays.sort(v);
        boolean isValid = true;
        for (int i = 0; i < v.length - 1; ++i) {
            if (v[i] != v[i + 1] - 1) {
                isValid = false;
            }
        }
        if (isValid) {
            System.out.println("CONSECUTIVE");
        } else {
            System.out.println();
        }
    }
}

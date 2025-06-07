import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();
        boolean bothDivizors = (b % a == 0) && (c % a == 0);
        if (bothDivizors) {
            System.out.println("EROARE");
        } else if (b % a == 0) {
            System.out.println(b);
        } else if (c % a == 0) {
            System.out.println(c);
        }
    }
}
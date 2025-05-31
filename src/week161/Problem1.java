import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int checkedValue = scanner.nextInt();
        int firstDigit = scanner.nextInt();
        int secondDigit = scanner.nextInt();
        int thirdDigit = scanner.nextInt();
        scanner.close();
        int firstTwoDigits = firstDigit * 10 + secondDigit;
        if (firstTwoDigits * thirdDigit == checkedValue || inverse(firstTwoDigits) * thirdDigit == checkedValue) {
            System.out.println("DA");
        } else {
            System.out.println("NU");
        }
    }

    public static int inverse(int value) {
        int result = 0;
        while (value > 0) {
            result = result * 10 + value % 10;
            value /= 10;
        }
        return result;
    }
}
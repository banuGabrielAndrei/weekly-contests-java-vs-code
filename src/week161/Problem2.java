import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int books = scanner.nextInt();
        int[][] recipes = new int[books][3];
        for (int i = 0; i < books; ++i) {
            recipes[i][0] = scanner.nextInt(); 
            recipes[i][1] = scanner.nextInt(); 
            recipes[i][2] = scanner.nextInt();
        }
        scanner.close();
        int minTimeRecipe1 = Integer.MAX_VALUE;
        int minTimeRecipe2 = Integer.MAX_VALUE;
        int minTimeBoth = Integer.MAX_VALUE;
        for (int i = 0; i < books; ++i) {
            int a = recipes[i][0];
            int b = recipes[i][1];
            int c = recipes[i][2];
            if (a == 1 && b == 1) {
                minTimeBoth = Math.min(minTimeBoth, c);
            }
            if (a == 1) {
                minTimeRecipe1 = Math.min(minTimeRecipe1, c);
            }
            if (b == 1) {
                minTimeRecipe2 = Math.min(minTimeRecipe2, c);
            }
        }
        if (minTimeRecipe1 == Integer.MAX_VALUE || minTimeRecipe2 == Integer.MAX_VALUE) {
            if (minTimeBoth == Integer.MAX_VALUE) {
                System.out.println("NU MANCAM IN SEARA ASTA");
            } else {
                System.out.println(minTimeBoth);
            }
        } else {
            int separateTime = minTimeRecipe1 + minTimeRecipe2;
            int combinedTime = minTimeBoth;
            System.out.println(Math.min(separateTime, combinedTime));
        }
    }
}

package week138;

/*
 * ONLY C++ solution accepted
 * 
 * #include <iostream>
 * using namespace std;
 * #include <string>
 * 
 * bool isVowel(char ch) {
 *  string vowels = "aeiouAEIOU";
 *  return vowels.find(ch) != string::npos;
 * }
 * 
 * int main() {
 *  string text;
 *  getline(cin, text);
 *  string result;
 *  int length = text.size();
 *  for (int i = 1; i <= length - 1; ++i) {
 *      char first = text[i - 1];
 *      char second = text[i + 1];
 *      if (isVowel(first) && isVowel(second)) {
 *          if (!isalpha(text[i])) {
 *              result += text[i];
 *          }
 *      }
 *  }
 *  cout << result << endl;
 *  return 0;
 * }
 */
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        scanner.close();
        text += ".";
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < text.length() - 1; ++i) {
            char first = text.charAt(i - 1);
            char second = text.charAt(i + 1);
            if (isVowel(first) && isVowel(second)) {
                if (!Character.isLetterOrDigit(text.charAt(i))) {
                    result.append(text.charAt(i));
                }
            }
        }
        System.out.println(result);
    }

    public static boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(String.valueOf(ch));
    }
}

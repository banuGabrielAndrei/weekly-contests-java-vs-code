package week148;

/*C++ SOLUTION MANDATORY 
 * void deleteWords(char text[]) {
    char result[1000] = ""; 
    char word[100] = "";   
    int wordIndex = 0, resultIndex = 0, vowelCount = 0;
    
    for (int i = 0; text[i] != '\0'; ++i) {
        char currentChar = text[i];
        
        if (isalpha(currentChar)) {
            word[wordIndex++] = currentChar;
            if (strchr("aeiouAEIOU", currentChar)) {
                ++vowelCount;
            }
        } else {
            word[wordIndex] = '\0';  
            
            if (wordIndex > 0 && vowelCount < 2) {  
                strcat(result, word);
            }
            
            if (!isspace(currentChar)) { 
                int len = strlen(result);
                result[len] = currentChar;
                result[len + 1] = '\0';
            } else {
                strcat(result, " ");
            }
            
            
            wordIndex = 0;
            vowelCount = 0;
        }
    }
    
    strcpy(text, result); 
}
 *
*/

import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        scanner.close();
        System.out.println(result(text));
    }

    public static String result(String text) {
        StringBuilder sb = new StringBuilder(text + " ");
        StringBuilder currentWord = new StringBuilder();
        StringBuilder finalResult = new StringBuilder();

        for (int i = 0; i < sb.length(); ++i) {
            char currentChar = sb.charAt(i);
            if (Character.isLetter(currentChar)) {
                currentWord.append(currentChar);
            } else {
                if (validWord(currentWord.toString())) {
                    currentWord.setLength(0);
                } else {
                    finalResult.append(currentWord);
                    currentWord.setLength(0);
                }
                finalResult.append(currentChar);
            }
        }

        return finalResult.toString().trim();
    }

    public static boolean validWord(String word) {
        int counter = 0;
        for (int i = 0; i < word.length(); ++i) {
            if (isVowel(word.charAt(i))) {
                ++counter;
            }
        }
        return counter >= 2;
    }

    public static boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(String.valueOf(ch));
    }
}

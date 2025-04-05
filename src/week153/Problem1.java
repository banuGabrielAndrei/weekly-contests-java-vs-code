package week153;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int space = scanner.nextInt();
        int books = scanner.nextInt(); 
        int bookSize = scanner.nextInt();
        scanner.close();
        int totalBooksSize = books * bookSize; 
        if (totalBooksSize <= space) {
            System.out.println(0); 
        } else {
            int booksThatFit = space / bookSize; 
            System.out.println(books - booksThatFit); 
        }
    }
}


/*Cerință
În noua casă a lui Andrei există un raft de x centimetri. El vrea să își pună cele a cărți din vechea casă pe raft.
Având în vedere că fiecare carte are câte b centimetri, aflați câte cărți NU vor încăpea pe raft, acestea fiind cărțile pe care Andrei le va lăsa în vechea casă.

Date de intrare
De pe prima linie se citesc cele 3 numere naturale: x, a și b, respectiv numărul de centimetri ai raftului din noua casă, numărul total de cărți pe care le deține Andrei și numărul de centimetri pe care îl ocupă o carte pe raft.

Date de ieșire
Pe ecran se va afișa numărul de cărți care NU vor încăpea pe raft.

Restricții și precizări
1 ≤ a ≤ 30
1 ≤ b ≤ 60
1 ≤ x ≤ 200
Exemplu
Date de intrare	Date de ieșire
20 3 7	1 */

package week166;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Problem3 {
    static final int N = 8;
    static final int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static final int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        char[][] board = new char[N][N];
        int knightX = -1, knightY = -1;
        for (int i = 0; i < N; ++i) {
            String line = scanner.next();
            for (int j = 0; j < N; ++j) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'C') {
                    knightX = i;
                    knightY = j;
                }
            }
        }
        scanner.close();

        List<char[][]> configs = new ArrayList<>();
        for (int d = 0; d < 8; ++d) {
            int nx = knightX + dx[d];
            int ny = knightY + dy[d];
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == '.') {
                char[][] newBoard = copyBoard(board);
                newBoard[knightX][knightY] = '.';
                newBoard[nx][ny] = 'C';
                configs.add(newBoard);
            }
        }

        if (configs.isEmpty()) {
            printBoard(board);
        } else {
            configs.sort(Comparator.comparingInt((char[][] b) -> findKnight(b)[0])
                                   .thenComparingInt(b -> findKnight(b)[1]));
            for (int i = 0; i < configs.size(); ++i) {
                printBoard(configs.get(i));
                if (i != configs.size() - 1) System.out.println();
            }
        }
    }

    static char[][] copyBoard(char[][] board) {
        char[][] copy = new char[N][N];
        for (int i = 0; i < N; ++i)
            copy[i] = board[i].clone();
        return copy;
    }

    static int[] findKnight(char[][] board) {
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                if (board[i][j] == 'C')
                    return new int[]{i, j};
        return new int[]{-1, -1};
    }

    static void printBoard(char[][] board) {
        for (int i = 0; i < N; ++i)
            System.out.println(new String(board[i]));
    }
}

//// STATEMENT:
// Marcel este jucător profesionist de șah.
// Deoarece urmează să participe la campionatul mondial de șah, acesta dorește să își perfecționeze jocul și să ajungă un maestru al calului.

// Marcel dorește să folosească calul pentru a ieși din orice încurcătură ar intra pe parcursul unei partide.
// Pentru a reuși acest lucru s-a gândit să se antreneze folosind o tablă de șah, formată din 8x8 pătrățele.

// Pe această tablă, Marcel va avea mai multe piese printre care și un cal.
// Scopul antrenamentului este acela de a găsi toate mutările valide ale calului pe tabla de joc, în vederea memorării configurațiilor valide ale tablei de șah găsite.
// O mutare validă a calului reprezintă mutarea acestuia de pe poziția inițială, pe o poziție pe care nu se află o altă piesă.

// Pentru a reuși să găsească toate aceste mutări cât mai repede încât să aibă cât mai mult timp să le memoreze, Marcel are nevoie de ajutorul tău pentru a-i scrie un program care să îi arate toate configurațiile tablei de șah, în care calul poate face o mutare validă.

// Programul pe care trebuie să îl scrii va primi tabla de șah sub forma unei matrice formate din 8 linii și 8 coloane, această matrice reprezentând configurația inițială a tablei de șah.
// Pe fiecare dintre cele 8 linii ale matricei se pot afla câte 8 caractere cu următoarele semnificații:

// caracterul C, reprezentând calul;
// caracterul P, reprezentând restul pieselor;
// caracterul ., reprezentând o căsuță goală.

// În urma acestor date, programul va trebui să afișeze pe ecran toate configurațiile valide rezultate în urma mutării calului o singură dată. Dacă nu se poate realiza nicio mutare validă a calului, va trebui ca programul tău să afișeze configurația inițială a tablei de șah.

// Date de intrare
// Se citește o matrice de caractere formată din 8 linii și 8 coloane. Valorile elementelor matricei pot fi:

// caracterul C, reprezentând calul;
// caracterul P, reprezentând restul pieselor;
// caracterul ., reprezentând o căsuță goală.
// Date de ieșire
// Se vor afișa pe ecran una sau mai multe matrice de caractere formate din 8 linii și 8 coloane, reprezentând configurațiile posibile ale tablei de șah după ce s-a efectuat o mutare cu calul pe o poziție validă.

// Matricele ce reprezintă configurația tablei de joc pentru fiecare mutare a calului vor fi afișate în ordine lexicografică crescătoare în funcție de linia și coloana pe care se va afla calul în urma mutării efectuate. Ele vor fi separate de o linie nouă în momentul afișării.

// Dacă nu se poate realiza nicio mutare validă a calului, se va afișa pe ecran configurația inițială a tablei de șah.

// Restricții și precizări
// Un cal se deplasează două pătrate pe verticală și unul pe orizontală, sau două pătrate pe orizontală și un pătrat pe verticală, sărind peste alte piese
// Calul se poate deplasa doar în interiorul tablei de șah
// O mutare validă a calului reprezintă mutarea acestuia de pe poziția inițială pe o poziție pe care nu se află o altă piesă
// Exemplu
// Date de intrare	Date de ieșire
// ........
// ........
// .P......
// ........
// ........
// ........
// ........
// .......C	

// ........
// ........
// .P......
// ........
// ........
// ......C.
// ........
// ........
//
//
// ........
// ........
// .P......
// ........
// ........
// ........
// .....C..
// ........

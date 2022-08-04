import java.util.Scanner;

public class TicTacToe {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        
        System.out.println("\nLet's play tic tac toe");

        char[][] board = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };

        printBoard(board);
        String endMessage = "Game ends in a tie - please play again!";

        for (int i = 1; i < 10; i++) {
            char turn;
            if (i % 2 == 0) {
                turn = 'O';
            } else {
                turn = 'X';
            }
            System.out.println("Turn: " + turn);
            int[] box = askUser(board);
            board[box[0]][box[1]] = turn;
            printBoard(board);
            int winner = checkWin(board);
            if (winner == 3) {
                endMessage = "X wins!";
                break;
            } else if (winner == -3) {
                endMessage = "O wins";
                break;
            }
        }

        System.out.println(endMessage);
        scan.close();
    }
    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            System.out.print("\t");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static int[] askUser(char[][] board) {
        System.out.print("\t* pick a row and column number: ");
        int column = scan.nextInt();
        int row = scan.nextInt();
        if (board[column][row] != '_') {
            System.out.println("that board position is taken...");
            return askUser(board);
        }
        return new int[]{column, row};
    }
    public static int getBoardPositionValueBasedOnChar(char[][] board, int row, int col) {
        int result = 0;
        if (board[row][col] == 'X') {
            result = 1;
        } else if (board[row][col] == 'O') {
            result = -1;
        }
        return result;
    }
    public static int checkWin(char[][] board) {
        int leftDiagCount = 0;
        int rightDiagCount = 0;
        for (int i = 0; i < 3; i++) {
            int rowCount = 0;
            int colCount = 0;
            leftDiagCount += getBoardPositionValueBasedOnChar(board, i, i);
            rightDiagCount += getBoardPositionValueBasedOnChar(board, i, 2 - i);
            if (leftDiagCount == 3 || leftDiagCount == -3) {
                return leftDiagCount;
            }
            if (rightDiagCount == 3 || rightDiagCount == -3) {
                return rightDiagCount;
            }
            for (int j = 0; j < 3; j++) {
                rowCount += getBoardPositionValueBasedOnChar(board, i, j);
                if (rowCount == 3 || rowCount == -3) {
                    return rowCount;
                }
                colCount += getBoardPositionValueBasedOnChar(board, j, i);
                if (colCount == 3 || colCount == -3) {
                    return colCount;
                }
            }
        }
        return 0;
    }
}
/*
  Ah Young Oh ahyoung.oh@stonybrook.edu all rights reservered.
  Recreated by Ruskonert (ruskonert@gmail.com)  
*/
public class Main {
    private static int spacesLeft;
    private static char[][] board;

    // ===================== Additional descriptions ============================
    // The implementating the algorithm of automaic detecting draw
    // The stored array that is coditional of victory.
    // The meaning of number is location, That is Following:
    // - - - - -
    // 1 | 2 | 3
    // - + - + -
    // 4 | 5 | 6
    // - + - + -
    // 7 | 8 | 9
    // - - - - -
    private static int[][] conditionOfVictory = {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};

    // Detect the draw even the value is not fully.
    // Check the player have the conditional of victory with investiagte the board.
    // It sames draw the game if no have it even one.
    // shapeOfPlayer is char, which is shape of player.
    public static boolean containsConditionalOfVictory(char shapeOfPlayer) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < 3; j++) {
                // Get the shape from 2d array (that is board)
                char shape = board[i][j];

                //If the board position is empty, it is assumed that the player can fill.
                if(shape == ' ') shape = shapeOfPlayer;

                // Replace the position of the two-dimensional array with the index number.
                // Converted for more efficient calculations.
                int index = getAreaNumber(i, j);

                // Gets a collection of conditions that can be defeated.
                int[][] condition = getConditional(index);
                for (int[] ints : condition) {
                    // If the array is empty (no more conditions to win)
                    if(ints[0] == 0) break;

                    // If the player have a condition to win
                    if (duplicates(ints, shape)) return true;
                }
            }
        }
        // 여기에 도달했다면 이길수 있는 조건이 더이상 없습니다.
        return false;
    }

    // 
    // Make sure that the index coordinates in the arr array all look the same.
    // If there is another shape, it means that it is blocked by another player.
    public static boolean duplicates(int[] arr, char shareOfPlayer)
    {
        for(int value : arr) {
            char c = board[(value - 1) / board.length][(value - 1) % 3];
            if (c == ' ') continue;
            if (c != shareOfPlayer)
                return false;
        }
        return true;
    }

    // Get the coditional of victory included index number.
    // If the {1,4,7} is can be condition of victory and index is 1, include it.
    // Please refer to `conditionOfVictory` if you want to know these it.
    public static int[][] getConditional(int index)
    {
        int lastOf = 0;
        int[][] victoryArr = new int[conditionOfVictory.length][3];
        for (int[] ints : conditionOfVictory) {
            for (int j = 0; j < 3; j++) {
                if (index == ints[j]) {
                    victoryArr[lastOf] = ints;
                    lastOf++;
                    break;
                }
            }
        }
        return victoryArr;
    }

    // Convert 2D array's location to index number.
    // For example:
    // board[0][2] => 3
    // board[0][0] => 1
    public static int getAreaNumber(int x, int y)
    {
        int indexOf = 1;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(i == x && j == y) return indexOf;
                else indexOf++;
            }
        }
        return -1;
    }
    // ===================== End additional descriptions ============================

    public static void main (String[] args) {

        System.out.println("Welcome to Tic Tac Toe");
        initializeBoard();
        firstDraw();
        char mark = 'X';
        while (true) {
            int square = getLegalMove(mark);
            move(square, mark);
            draw();
            if (is3InRow(mark)) {
                System.out.println(mark + " wins!");
                break;
            }

            if (isBoardFull()) {
                System.out.println("Tie game!");
                break;
            }

            // ===================== Additional descriptions ============================
            if(!containsConditionalOfVictory(mark)) {
                System.out.println("No longer more victory condition!");
                break;
            }
            // ===================== End additional descriptions ============================

            if (mark == 'X') {
                mark = 'O';
            }
            else {
                mark = 'X';
            }
        }
    }
    public static int getLegalMove (char mark) {
        java.util.Scanner console = new java.util.Scanner(System.in);
        while (true) {
            System.out.println(mark + "'s next move: ");
            int square = console.nextInt();
            if ((square >= 1) &&
                    (square <= 9) &&
                    (isSquareEmpty(square))) {
                return square;
            }
            System.out.println("\nIllegal move, try again\n");
        }
    }

    public static void initializeBoard () {
        spacesLeft = 9;
        board = new char[3][3];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                board[i][j]=' ';
            }
        }
   /* s1 = s2 = s3 = s4 = s5 = ' '; 
      s6 = s7 = s8 = s9 = ' ';     */
    }

    public static void firstDraw () {
        System.out.println();
        System.out.println("   |   |   ");
        System.out.println(" " + 1 + " | " + 2 + " | " + 3);
        System.out.println("   |   |   ");
        System.out.println("---+---+---");
        System.out.println("   |   |   ");
        System.out.println(" " + 4 + " | " + 5 + " | " + 6);
        System.out.println("   |   |   ");
        System.out.println("---+---+---");
        System.out.println("   |   |   ");
        System.out.println(" " + 7 + " | " + 8 + " | " + 9);
        System.out.println("   |   |   ");
        System.out.println();
    }

    public static void draw () {
        System.out.println();
        System.out.println("   |   |   ");
        System.out.println(" " + board[0][0] + " | "
                + board[0][1] + " | " + board[0][2]);
        System.out.println("   |   |   ");
        System.out.println("---+---+---");
        System.out.println("   |   |   ");
        System.out.println(" " + board[1][0] + " | "
                + board[1][1] + " | " + board[1][2]);
        System.out.println("   |   |   ");
        System.out.println("---+---+---");
        System.out.println("   |   |   ");
        System.out.println(" " + board[2][0] + " | "
                + board[2][1] + " | " + board[2][2]);
        System.out.println("   |   |   ");
        System.out.println();
    }

    public static void move (int square, char mark) {
        if (isSquareEmpty(square)) {
            spacesLeft = spacesLeft - 1;
        }

        if (square == 1) {
            board[0][0] = mark;
        }
        else if (square == 2) {
            board[0][1] = mark;
        }
        else if (square == 3) {
            board[0][2] = mark;
        }
        else if (square == 4) {
            board[1][0] = mark;
        }
        else if (square == 5) {
            board[1][1] = mark;
        }
        else if (square == 6) {
            board[1][2] = mark;
        }
        else if (square == 7) {
            board[2][0] = mark;
        }
        else if (square == 8) {
            board[2][1] = mark;
        }
        else if (square == 9) {
            board[2][2] = mark;
        }
    }

    public static boolean isSquareEmpty (int square) {
        if (square == 1) {
            return board[0][0]==' ';
        }
        else if (square == 2) {
            return board[0][1] == ' ';
        }
        else if (square == 3) {
            return board[0][2] == ' ';
        }
        else if (square == 4) {
            return board[1][0] == ' ';
        }
        else if (square == 5) {
            return board[1][1] == ' ';
        }
        else if (square == 6) {
            return board[1][2] == ' ';
        }
        else if (square == 7) {
            return board[2][0] == ' ';
        }
        else if (square == 8) {
            return board[2][1] == ' ';
        }
        else if (square == 9) {
            return board[2][2] == ' ';
        }
        else {
            return false;
        }
    }

    public static boolean is3InRow (char mark) {
        return
                (board[0][0] == mark && board[0][1] == mark && board[0][2] == mark) ||
                        (board[1][0] == mark && board[1][1] == mark && board[1][2] == mark) ||
                        (board[2][0] == mark && board[2][1] == mark && board[2][2] == mark) ||
                        (board[0][0] == mark && board[1][0] == mark && board[2][0] == mark) ||
                        (board[0][1] == mark && board[1][1] == mark && board[2][1] == mark) ||
                        (board[0][2] == mark && board[1][2] == mark && board[2][2] == mark) ||
                        (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) ||
                        (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark);
    }

    public static boolean isBoardFull () {
        return spacesLeft == 0;
    }

}

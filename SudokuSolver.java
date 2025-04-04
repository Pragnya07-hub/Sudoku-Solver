public class SudokuSolver {

    // Function to check if placing a number in a cell is valid
    public static boolean isValid(int[][] board, int row, int col, int num) {
        // Check if 'num' is not in the current row
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check if 'num' is not in the current column
        for (int x = 0; x < 9; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check if 'num' is not in the current 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Function to solve the Sudoku using backtracking
    public static boolean solveSudoku(int[][] board) {
        int[] emptyCell = findEmpty(board);
        if (emptyCell == null) {
            return true;  // Puzzle solved
        }

        int row = emptyCell[0];
        int col = emptyCell[1];

        // Try digits 1-9 for the empty cell
        for (int num = 1; num <= 9; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;  // Place the number

                if (solveSudoku(board)) {  // Recursively try to solve the next cells
                    return true;
                }

                board[row][col] = 0;  // Backtrack if no valid number found
            }
        }

        return false;  // Trigger backtracking if no number can be placed
    }

    // Function to find the next empty cell (represented by 0)
    public static int[] findEmpty(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};  // Row, Column
                }
            }
        }
        return null;  // No empty cell found, puzzle solved
    }

    // Function to print the board
    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Example Sudoku Puzzle (0 represents empty cells)
    public static void main(String[] args) {
        int[][] sudokuBoard = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        // Solving the Sudoku
        if (solveSudoku(sudokuBoard)) {
            System.out.println("Sudoku solved:");
            printBoard(sudokuBoard);
        } else {
            System.out.println("No solution exists");
        }
    }
}

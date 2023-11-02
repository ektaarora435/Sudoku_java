public class sudoko {
    public static void main(String[] args) {
        int[][] puzzle = {
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

        if (solveSudoku(puzzle)) {
            printSudoku(puzzle);
        } else {
            System.out.println("No solution exists.");
        }
    }

    public static boolean solveSudoku(int[][] puzzle) {
        int N = puzzle.length;

        int[] emptyCell = findEmptyCell(puzzle, N);
        int row = emptyCell[0];
        int col = emptyCell[1];

        if (row == -1) {
            return true; // No empty cells left; puzzle is solved
        }

        for (int num = 1; num <= 9; num++) {
            if (isSafe(puzzle, row, col, num)) {
                puzzle[row][col] = num;

                if (solveSudoku(puzzle)) {
                    return true;
                }

                puzzle[row][col] = 0; // Unset the cell if no solution is found
            }
        }

        return false; // No solution found at this point
    }

    public static int[] findEmptyCell(int[][] puzzle, int N) {
        int[] cell = {-1, -1};

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (puzzle[row][col] == 0) {
                    cell[0] = row;
                    cell[1] = col;
                    return cell;
                }
            }
        }

        return cell;
    }

    public static boolean isSafe(int[][] puzzle, int row, int col, int num) {
        return !usedInRow(puzzle, row, num) && !usedInColumn(puzzle, col, num) && !usedInBox(puzzle, row - row % 3, col - col % 3, num);
    }

    public static boolean usedInRow(int[][] puzzle, int row, int num) {
        for (int col = 0; col < puzzle.length; col++) {
            if (puzzle[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean usedInColumn(int[][] puzzle, int col, int num) {
        for (int row = 0; row < puzzle.length; row++) {
            if (puzzle[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean usedInBox(int[][] puzzle, int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (puzzle[row + boxStartRow][col + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printSudoku(int[][] puzzle) {
        int N = puzzle.length;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                System.out.print(puzzle[row][col] + " ");
            }
            System.out.println();
        }
    }
}

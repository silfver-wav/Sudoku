package model;

import java.io.Serializable;
import java.util.Random;

/**
 * Represents the logic for a Sudoku game
 */
public class SudokuLogic implements Serializable {
    private SudokuSquare[][] squares;
    private int nrOfAdded, nrOfHints;

    private SudokuUtilities.SudokuLevel level;

    /**
     * Creates the logic for a Sudoku game using difficulty level
     * @param level the difficulty level
     */
    public SudokuLogic(SudokuUtilities.SudokuLevel level) {
        squares = new SudokuSquare[9][9];
        this.level = level;
        int[][][] matrix = SudokuUtilities.generateSudokuMatrix(this.level);

        nrOfAdded = 0;
        nrOfHints = 0;

        // TODO: kolla vad han menar med "om rutan ska visas eller döljas från början"
        for (int r=0;r< 9;r++) {
            for (int c=0;c< 9;c++) {
                if (matrix[r][c][0] != 0) squares[r][c] = new SudokuSquare(matrix[r][c][1], true, false);
                else squares[r][c] = new SudokuSquare(matrix[r][c][1], false,true);
            }
        }
    }

    public String[][] createCopy() {
        String[][] copy = new String[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        for (int row=0;row < 9;row++) {
            for (int col=0;col < 9;col++) {
                copy[row][col] = this.getNr(row,col);
            }
        }
        return copy;
    }

    /**
     * Add a number on a given row / column
     * @param row the row
     * @param col the column
     * @param nr the number
     * @return true if the number can be added, otherwise false
     */
    public boolean addNr(int row, int col, int nr) {

        if (squares[row][col].isFixed()) { // Checks if the number is fixed
            //System.out.println("Number can not be placed here");
            return false;
        } else if (checkVertically(row, nr)) { // Checks if the number exists in the same column
            //System.out.println("Number already exists vertically");
            return false;
        } else if (checkHorizontally(col, nr)) { // Checks if the number exists in the same row
            //System.out.println("Number already exists horizontally");
            return false;
        } else if (checkSubgrid(row, col, nr)) { // Checks if the number exists in the same subgrid
            //System.out.println("Number already exists in the subgrid");
            return false;
        } else

        if (squares[row][col].getHide()) nrOfAdded++;

            squares[row][col].setNr(nr);
            squares[row][col].setHide(false);
        return true;
    }

    /**
     * Delete a number from a given position using row and column
     * @param row the row
     * @param col the column
     * @return true if the number can be deleted, otherwise false
     */
    public boolean deleteNr(int row, int col) {
        if (squares[row][col].isFixed()) { // Checks if the number is fixed
            return false;
        } else  squares[row][col].setNr(0);
        squares[row][col].setHide(true);

        nrOfAdded--;
        return true;
    }

    /**
     * Clear the board of a Sudoku game
     */
    public void clearBoard() {
        for (int row=0;row< 9;row++) {
            for (int col=0;col< 9;col++) {
                if (!squares[row][col].isFixed()) deleteNr(row, col);
            }
        }
        nrOfAdded=0;
    }

    /**
     * Get a random, correct number on a given square
     * @return the correct number
     */
    public boolean getHint() {
        // Checks if the hints have been used up


        Random rand=new Random();
        int row;
        int col;
        do {
            row = rand.nextInt(9);
            col = rand.nextInt(9);

            if (!squares[row][col].isFixed() && squares[row][col].getHide()) { // Makes sure a number doesn't getSquare added to a fixed number or players number
                addNr(row, col, squares[row][col].getRightAnswer());
                nrOfHints++;
                return true;
            }
        } while (true);

    }

    /**
     * Check if numbers are correct
     * @return true if numbers are correct, otherwise false
     */
    public boolean check() {
        int count=0;
        for (int row=0;row < 9;row++) {
            for (int col=0;col < 9;col++) {
                if (!squares[row][col].isFixed() && squares[row][col].getNr() == squares[row][col].getRightAnswer()) count++;
            }
        }

        return count == nrOfAdded;
    }

    /**
     * Check if board is solved
     * @return true if board is solved, otherwise false
     */
    public boolean isSolved() {
        int count=0;
        for (int row=0;row< 9;row++) {
            for (int col=0;col< 9;col++) {
                if (!squares[row][col].getHide() &&
                        squares[row][col].getNr() == squares[row][col].getRightAnswer())
                    count++;
            }
        }
        return count == 81;
    }

    /**
     * Get number on a given square using row and column
     * @param row the row
     * @param col the column
     * @return a string of a hidden number or a string of a predetermined number
     */
    public String getNr(int row, int col) {
        if (squares[row][col].getHide()) {
            return "";
        } else return Integer.toString(squares[row][col].getNr());
    }

    /**
     * Get specified square using row and column
     * @param row the row
     * @param col the column
     * @return the square position
     */
    public SudokuSquare getSquare(int row, int col) {
        return squares[row][col];
    }

    /**
     * Get the difficulty level
     * @return the difficulty level
     */
    public SudokuUtilities.SudokuLevel getLevel() {
        return level;
    }

    /**
     * Check if the board is full
     * @return true if the board is full, otherwise false
     */
    public boolean isFull() {
        int count=0;
        for (int row=0;row< 9;row++) {
            for (int col=0;col< 9;col++) {
                if (!squares[row][col].getHide()) count++;
            }
        }
        return count == 9*9;
    }

    private boolean checkSubgrid(int row, int col, int nr) {
        row = row - row % 3;
        col = col - col % 3;

        for (int r = row; r < row+3; r++) {
            for (int c = col; c < col+3; c++) {
                if (squares[r][c].getNr() == nr) return true;
            }
        }
        return false;
    }

    private boolean checkVertically(int row, int nr) {

        for (int col=0;col<9;col++) {
            if (squares[row][col].getNr() == nr) return true;
        }
        return false;
    }

    private boolean checkHorizontally(int col, int nr) {
        for (int row=0;row<9;row++) {
            if (squares[row][col].getNr() == nr) return true;
        }
        return false;
    }
}
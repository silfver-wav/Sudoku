package model;

import java.io.Serializable;

/**
 * Represents a square in a Sudoku game
 */
public class SudokuSquare implements Serializable {
    private int rightAnswer;
    private int nr;
    private boolean fixed;
    private boolean hide;

    /**
     * Creates a Sudoku square using a fixed- or hidden number
     * If the number is fixed the right answer will be displayed on the square
     * @param rightAnswer the right answer
     * @param fixed the fixed number
     * @param hide the hidden number
     */
    public SudokuSquare(int rightAnswer, boolean fixed, boolean hide) {
        this.rightAnswer=rightAnswer;
        this.fixed=fixed;

        if (fixed) this.nr = this.rightAnswer;
        else this.nr = 0;

        this.hide=hide;
    }

    /**
     * Get the right answer
     * @return the right answer
     */
    public int getRightAnswer() {
        return rightAnswer;
    }

    /**
     * Get a number
     * @return the number
     */
    public int getNr() {
        return nr;
    }

    /**
     * Check if number is fixed
     * @return true if number is fixed, otherwise false
     */
    public boolean isFixed() {
        return fixed;
    }

    /**
     * Set the number on a square
     * @param userChoice the number to set
     */
    public void setNr(int userChoice) {
        if (!fixed) this.nr = userChoice;
    }

    /**
     * Set a number on a square to be hidden
     * @param hide the number
     */
    public void setHide(boolean hide) {
        this.hide = hide;
    }

    /**
     * Get a hidden number
     * @return the hidden number
     */
    public boolean getHide() {
        return hide;
    }
}
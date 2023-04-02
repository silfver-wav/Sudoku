package View;

import model.SudokuIO;
import model.SudokuLogic;
import model.SudokuUtilities;

import java.io.*;

//TODO: Implement Alert class for exceptions
public class Controller{

    private SudokuLogic model;
    private View view;
    private int row, col;

    /**
     * Represents the controller for a Sudoku game
     */
    public Controller(SudokuLogic model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Saves the tile that has been pressed using row and column, and highlights the tile.
     * @param row the row
     * @param col the column
     */
    public void setPos(int row, int col) {
        //view.removeHighlight(this.row,this.col); // Removes previous highlight
        view.getBoard().removeHighlight(this.row, this.col);
        this.row=row; // Saves row
        this.col=col; // Saves column
        view.getBoard().setHighlight(row,col);
        //view.setHighlight(row, col); // Sets new highlight to tile
    }

    /**
     * Add a number to a given square
     * @param number the number to add
     */
    public void addNrToSquare(int number) {
        if (!model.addNr(row, col, number)) return; // Number cannot be placed
        else {
            view.getBoard().updateTiles(model.createCopy());
            if (model.isFull()) {
                if (model.isSolved()) {
                    view.getUi().viewAlert("Game over", "You solved the puzzle");
                    onGameNewGame();
                }
                else view.getUi().viewAlert("Game in progress", "You did not solve the puzzle");
            }
        }
    }

    /**
     * Add a random correct number to a square
     */
    public void addHintToSquare() {
        if (model.getHint()) view.getBoard().updateTiles(model.createCopy()); //view.updateTiles();
        else view.getUi().viewAlert("Warning", "You have no more hints");
        if (model.isFull()) {
            if (model.isSolved()) {
                view.getUi().viewAlert("Game over", "You solved the puzzle");
                onGameNewGame();
            }
            else view.getUi().viewAlert("Game in progress", "You did not solve the puzzle");
        }
    }

    /**
     * Clear a square
     */
    public void clearSquare() {
        if (model.deleteNr(row, col)) view.getBoard().updateTiles(model.createCopy()); // view.updateTiles();
        else view.getUi().viewAlert("Warning", "This number cannot be deleted");
    }

    /**
     * Check if numbers are correct
     */
    public void check() {
        if(model.check()) view.getUi().viewAlert("Check", "All numbers are correct");
        else view.getUi().viewAlert("Check", "Some numbers are incorrect");
    }

    /**
     * Load a saved game from file
     */
    public void onFileLoadGame() {
        try {
            String path = view.getUi().getPath();
            model = SudokuIO.deSerializeFromFile(path);
            //view.updateBoard(model);
            view.getBoard().updateBoard(model, model.createCopy());
        } catch (IOException | ClassNotFoundException e) {
            view.getUi().viewAlert("IOException","Unable to read file");
        } catch (Exception e) {
            view.getUi().viewAlert("Load Game","No file was entered");
        }
    }

    /**
     * Save a game to file
     */
    public void onFileSaveGame() {
        try {
            String name = view.getUi().setPath();
            SudokuIO.serializeToFile(name, model);
        } catch (IOException e) {
            view.getUi().viewAlert("IOException","Unable to save file");
        }
        catch (Exception e) {
            view.getUi().viewAlert("Save Game","No file was saved");
        }
    }

    /**
     * Exit a game session
     */
    public void onFileExit() {

        System.exit(0);
    }

    /**
     * Generate a new game with the same level of difficulty as before
     */
    public void onGameNewGame() {
        model = new SudokuLogic(model.getLevel());
        view.getBoard().updateBoard(model, model.createCopy());
        //view.updateBoard(model);
        view.getUi().viewAlert("New Game","The game will have same difficulty as before");
    }

    /**
     * Select a game's difficulty
     * @param level the game's difficulty
     */
    public void onGameSelectDifficulty(SudokuUtilities.SudokuLevel level) {
        model = new SudokuLogic(level);
        view.getBoard().updateBoard(model, model.createCopy());
        //view.updateBoard(model);
    }

    /**
     * Display the rules of the game
     */
    public void onHelpGameRules() {
        view.getUi().gameRulesAlert();
    }

    /**
     * Clear all numbers a user has placed on the board
     */
    public void onHelpClearAll() {
        model.clearBoard();
        view.getBoard().updateTiles(model.createCopy());
    }

}
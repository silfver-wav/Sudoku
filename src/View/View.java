package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.SudokuLogic;
import model.SudokuUtilities;

/**
 * Represents the visual display of a Sudoku game
 */
public class View extends BorderPane {

    private SudokuLogic model;

    private MenuView menu;
    private ButtonsLeftView buttonsLeft;
    private ButtonsRightView buttonsRight;
    private ViewUI ui;
    private BoardView board;

    /**
     * Creates the layout of the Sudoku game from a model
     * @param model the model
     */
    public View(SudokuLogic model) {
        super();
        this.model = model;
        Controller controller = new Controller(model, this);

        this.buttonsLeft = new ButtonsLeftView(controller);
        this.setLeft(buttonsLeft);
        this.buttonsRight = new ButtonsRightView(controller);
        this.setRight(buttonsRight);
        this.board = new BoardView(controller, model);
        this.setCenter(board);
        this.menu = new MenuView(controller);
        this.setTop(menu);

        this.ui = new ViewUI();
    }

    /**
     * Get the visual representation of a board
     * @return the board
     */
    public BoardView getBoard() {
        return board;
    }

    /**
     * Get the UI for the visual representation
     * @return the UI
     */
    public ViewUI getUi() {
        return ui;
    }
}
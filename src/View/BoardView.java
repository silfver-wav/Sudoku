package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.SudokuLogic;
import model.SudokuUtilities;


/**
 * Represents the visual representation of a board for a sudoku game
 */
public class BoardView extends TilePane {
    private Label[][] numberTiles;
    private SudokuLogic model;

    /**
     * Creates a board using a controller and model
     * @param controller the controller
     * @param model the model
     */
    public BoardView(Controller controller, SudokuLogic model) {
        super();
        this.model=model;

        numberTiles = new Label[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        initNumberTiles(controller);

        this.getChildren().add(makeNumberPane());

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5));
        this.setPrefColumns(0);
        this.setPrefRows(0);
        this.setStyle("-fx-border-color: black; -fx-border-width: 1.0px; -fx-background-color: black;");

    }

    // called by constructor (only)
    private final void initNumberTiles(Controller controller) {
        Font font = Font.font("Monospaced", FontWeight.NORMAL, 20);
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                Label tile = new Label(model.getNr(row, col));
                tile.setPrefWidth(32);
                tile.setPrefHeight(32);
                tile.setFont(font);
                tile.setAlignment(Pos.CENTER);
                tile.setStyle("-fx-border-color: black; -fx-border-width: 0.5px; -fx-background-color: grey;"); // css style
                tile.setOnMouseClicked(mouseEvent -> {
                    for(int row2 = 0; row2 < SudokuUtilities.GRID_SIZE; row2++) {
                        for(int col2 = 0; col2 < SudokuUtilities.GRID_SIZE; col2++) {
                            if(mouseEvent.getSource() == numberTiles[row2][col2]) {

                                controller.setPos(row2, col2);

                                return;
                            }
                        }
                    }
                });
                // add new tile to grid
                numberTiles[row][col] = tile;
            }
        }
    }

    private final TilePane makeNumberPane() {
        // create the root tile pane
        TilePane root = new TilePane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(5));
        root.setPrefColumns(SudokuUtilities.SECTIONS_PER_ROW);
        root.setPrefRows(SudokuUtilities.SECTIONS_PER_ROW);
        root.setStyle("-fx-border-color: black; -fx-border-width: 1.0px; -fx-background-color: black;");

        // create the 3*3 sections and add the number tiles
        TilePane[][] sections = new TilePane[SudokuUtilities.SECTIONS_PER_ROW][SudokuUtilities.SECTIONS_PER_ROW];
        int i = 0;
        for (int srow = 0; srow < SudokuUtilities.SECTIONS_PER_ROW; srow++) {
            for (int scol = 0; scol < SudokuUtilities.SECTIONS_PER_ROW; scol++) {
                TilePane section = new TilePane();
                section.setPrefColumns(SudokuUtilities.SECTION_SIZE);
                section.setPrefRows(SudokuUtilities.SECTION_SIZE);
                section.setStyle( "-fx-border-color: black; -fx-border-width: 0.5px;");

                // add number tiles to this section
                for (int row = 0; row < SudokuUtilities.SECTION_SIZE; row++) {
                    for (int col = 0; col < SudokuUtilities.SECTION_SIZE; col++) {
                        // calculate which tile and add
                        section.getChildren().add(
                                numberTiles[srow * SudokuUtilities.SECTION_SIZE + row][scol * SudokuUtilities.SECTION_SIZE + col]);
                    }
                }

                // add the section to the root tile pane
                root.getChildren().add(section);
            }
        }
        return root;
    }

    /**
     * Update the board state using a model and a copy of the same model
     * @param model the model
     * @param copyOfModel the copy of the model
     */
    public void updateBoard(SudokuLogic model, String[][] copyOfModel) {
        this.model=model; // Updates model
        updateTiles(copyOfModel); // Updates all tiles
    }

    /**
     * Update the tiles of a Sudoku board using a copy of the model
     * @param copyOfModel the copy of the model
     */
    public void updateTiles(String[][] copyOfModel) {
        for (int row=0;row<9;row++) {
            for (int col=0;col<9;col++) {
                // Makes all fixed numbers black
                if (model.getSquare(row, col).isFixed()) {
                    numberTiles[row][col].setText(copyOfModel[row][col]);
                    numberTiles[row][col].setStyle("-fx-border-color: black; -fx-border-width: 0.5px; -fx-background-color: grey;"); // css style
                }
                // Makes all numbers added by user blue
                else {
                    numberTiles[row][col].setText(copyOfModel[row][col]);
                    numberTiles[row][col].setStyle("-fx-border-color: black; -fx-border-width: 0.5px; -fx-background-color: grey;  -fx-text-fill: blue;"); // css style
                }
            }
        }
    }

    /**
     * Remove highlight from a square using row and column
     * @param row the row
     * @param col the column
     */
    public void removeHighlight(int row, int col) {
        numberTiles[row][col].setStyle("-fx-border-color: black; -fx-border-width: 0.5px; -fx-background-color: grey;");
    }

    /**
     * Set highlight for a square using row and column
     * @param row the row
     * @param col the column
     */
    public void setHighlight(int row, int col) {
        numberTiles[row][col].setStyle("-fx-border-color: blue; -fx-border-width: 0.5px; -fx-background-color: grey;  -fx-text-fill: blue;");
    }
}

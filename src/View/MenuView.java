package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.SudokuIO;
import model.SudokuUtilities;

import java.awt.*;
import java.io.File;

/**
 * Represents the menu for a Sudoku game
 */
public class MenuView extends MenuBar {

    // MenuItems for file
    private MenuItem load_game, save_game, exit;
    // MenuItems for game
    private MenuItem new_game;
    private Menu select_difficulty;
    private MenuItem easy, medium, hard;
    // MenuItems for help
    private MenuItem game_rules, check, clear;


    /**
     * Create the menu for a Sudoku game using a controller
     * @param controller the controller
     */
    public MenuView(Controller controller) {
        super();
        initMenuBar();

        addEventHandlersForHelp(controller);
        addEventHandlersForGame(controller);
        addEventHandlersForFile(controller);
    }
    private void initMenuBar() {
        // Creates file for game
        Menu file = new Menu("File");
        // Creates menu items for file
        load_game = new MenuItem("Load Game");
        save_game = new MenuItem("Save Game");
        exit = new MenuItem("Exit");
        // Adds menu items to file menu
        file.getItems().addAll(load_game, save_game, exit);

        // Creates menu for game
        Menu game = new Menu("Game");
        // Creates menu items for game
        new_game = new MenuItem("New Game");
        select_difficulty = new Menu("Select Difficulty");

        easy = new MenuItem("Easy");
        medium = new MenuItem("Medium");
        hard = new MenuItem("Hard");
        select_difficulty.getItems().addAll(easy, medium, hard);

        // Adds menu items to game menu
        game.getItems().addAll(new_game, select_difficulty);

        // Creates menu for help
        Menu help = new Menu("Help");
        // Creates menu items for help
        game_rules = new MenuItem("Game rules");
        check = new MenuItem("Check if the number so far is right");
        clear = new MenuItem("Clear all numbers");
        // Adds menu items to help menu
        help.getItems().addAll(game_rules, check, clear);

        // Adds menus to menuBar
        this.getMenus().addAll(file, game, help);

        this.setStyle("-fx-border-color: black; -fx-border-width: 0.5px; -fx-background-color: grey;");
    }

    // EventHandlers for Help
    private void addEventHandlersForHelp(Controller controller) {

        EventHandler<ActionEvent> gameRulesClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.onHelpGameRules();
            }
        }; game_rules.addEventHandler(ActionEvent.ACTION, gameRulesClicked);

        EventHandler<ActionEvent> checkClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.check();
            }
        }; check.addEventHandler(ActionEvent.ACTION, checkClicked);

        EventHandler<ActionEvent> clearClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.onHelpClearAll();
            }
        }; clear.addEventHandler(ActionEvent.ACTION, clearClicked);
    }

    // EventHandlers for Game
    private void addEventHandlersForGame(Controller controller) {

        EventHandler<ActionEvent> newGameClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.onGameNewGame();
            }
        }; new_game.addEventHandler(ActionEvent.ACTION, newGameClicked);

        // Select difficulty items
        EventHandler<ActionEvent> difficultyEasyClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.onGameSelectDifficulty(SudokuUtilities.SudokuLevel.EASY);
            }
        }; easy.addEventHandler(ActionEvent.ACTION, difficultyEasyClicked);

        EventHandler<ActionEvent> difficultyMediumClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.onGameSelectDifficulty(SudokuUtilities.SudokuLevel.MEDIUM);
            }
        }; medium.addEventHandler(ActionEvent.ACTION, difficultyMediumClicked);

        EventHandler<ActionEvent> difficultyHardClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.onGameSelectDifficulty(SudokuUtilities.SudokuLevel.HARD);
            }
        }; hard.addEventHandler(ActionEvent.ACTION, difficultyHardClicked);
    }

    // EventHandlers for File
    private void addEventHandlersForFile(Controller controller) {

        EventHandler<ActionEvent> loadGameClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.onFileLoadGame();
            }
        }; load_game.addEventHandler(ActionEvent.ACTION, loadGameClicked);

        EventHandler<ActionEvent> saveGameClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.onFileSaveGame();
            }
        }; save_game.addEventHandler(ActionEvent.ACTION, saveGameClicked);

        EventHandler<ActionEvent> exitClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.onFileExit();
            }
        }; exit.addEventHandler(ActionEvent.ACTION, exitClicked);

    }

}
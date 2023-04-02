package se.kth.vpero.sudoku;


import View.View;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import model.SudokuLogic;
import model.SudokuUtilities;


public class Main extends Application{

    private SudokuLogic model;

    public void start(Stage primaryStage) {
        model = new SudokuLogic(SudokuUtilities.SudokuLevel.MEDIUM);

        View view = new View(model);

        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);

        primaryStage.setTitle("Sudoku");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

package View;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

/**
 * Represents the visual UI for a Sudoku game
 */
public class ViewUI {

    private FileChooser fileChooser;
    private Stage stage;

    private String path;

    /**
     * Creates the UI
     */
    public ViewUI() {
        fileChooser = new FileChooser();
        stage = new Stage();
    }

    /**
     * Get file path
     * @return the file path
     */
    public String getPath() {

        fileChooser.setTitle("Load Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("serialization file", "*.ser"));

        File file = fileChooser.showOpenDialog(stage);
        fileChooser.setInitialDirectory(file.getParentFile());
        path = file.getPath();

        System.out.println("Path to load: " + path);
        return path;
    }

    /**
     * Set the file path
     * @return the file path
     */
    public String setPath() {

        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("Sudoku");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("serialization file", "*.ser"));

        File file = fileChooser.showSaveDialog(stage);
        fileChooser.setInitialDirectory(file.getParentFile()); // save the chosen directory

        path = file.getPath();

        System.out.println("Path: "+ path);
        return path;
    }

    /**
     * Display information on the screen using alert
     * @param title the title of the alert
     * @param context the context of the alert
     */
    public void viewAlert(String title, String context){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(context);

        alert.showAndWait();
    }

    /**
     * Display the game rules for a sudoku game using alert
     */
    public void gameRulesAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Rules");
        alert.setHeaderText(null);
        alert.setContentText(   "* Every square has to contain a single number\n" +
                "* Only the numbers from 1 through 9 can be used\n" +
                "* Each 3x3 box can only contain each number from 1 to 9 once\n" +
                "* Each vertical column can only conatin each number from 1 to 9 once\n" +
                "* Each horizontal row can only contain each number from 1 to 9 once\n" +
                "* Only 3 hints are allowed\n");
        alert.showAndWait();
    }
}

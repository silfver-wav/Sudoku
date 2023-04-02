package View;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Represents the buttons on the left side of a Sudoku board
 */
public class ButtonsLeftView extends AnchorPane {
    private Button check, hint;

    /**
     * Creates the buttons and adds event handlers from a controller
     * @param controller the controller
     */
    public ButtonsLeftView(Controller controller) {
        super();

        initLeftButtons();

        addEventHandlers(controller);
    }

    private void initLeftButtons() {
        check = new Button("Check");
        check.setPrefWidth(60.0);
        AnchorPane.setTopAnchor(check, 120.00);
        AnchorPane.setLeftAnchor(check, 10.00);


        hint = new Button("Hint");
        hint.setPrefWidth(40.0);
        AnchorPane.setTopAnchor(hint, 170.00);
        AnchorPane.setLeftAnchor(hint, 20.00);


        this.getChildren().addAll(check, hint);
        this.setStyle("-fx-border-color: black; -fx-background-color: black;");

    }

    private void addEventHandlers(Controller controller) {

        EventHandler<MouseEvent> checkClicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.check();
            }
        };
        check.addEventHandler(MouseEvent.MOUSE_CLICKED, checkClicked);

        EventHandler<MouseEvent> hintClicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.addHintToSquare();
            }
        };
        hint.addEventHandler(MouseEvent.MOUSE_CLICKED, hintClicked);

    }
}


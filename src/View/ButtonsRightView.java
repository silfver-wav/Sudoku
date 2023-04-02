package View;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Represents the buttons on the right side of a Sudoku board
 */
public class ButtonsRightView extends AnchorPane {

    private Button nr1, nr2, nr3, nr4, nr5, nr6, nr7, nr8, nr9, c;

    /**
     * Creates buttons and adds event handlers from a controller
     * @param controller the controller
     */
    public ButtonsRightView(Controller controller) {
        super();
        initRightButtons();

        addEventHandlers(controller);
    }

    private void initRightButtons() {
        nr1 = new Button("1");
        nr1.setPrefWidth(26.0);
        AnchorPane.setTopAnchor(nr1, 10.00);
        AnchorPane.setRightAnchor(nr1, 10.00);

        nr2 = new Button("2");
        nr2.setPrefWidth(26.0);
        AnchorPane.setTopAnchor(nr2, 40.00);
        AnchorPane.setRightAnchor(nr2, 10.00);

        nr3 = new Button("3");
        nr3.setPrefWidth(26.0);
        AnchorPane.setTopAnchor(nr3, 70.00);
        AnchorPane.setRightAnchor(nr3, 10.00);

        nr4 = new Button("4");
        nr4.setPrefWidth(26.0);
        AnchorPane.setTopAnchor(nr4, 100.00);
        AnchorPane.setRightAnchor(nr4, 10.00);

        nr5 = new Button("5");
        nr5.setPrefWidth(26.0);
        AnchorPane.setTopAnchor(nr5, 130.00);
        AnchorPane.setRightAnchor(nr5, 10.00);

        nr6 = new Button("6");
        nr6.setPrefWidth(26.0);
        AnchorPane.setTopAnchor(nr6, 160.00);
        AnchorPane.setRightAnchor(nr6, 10.00);

        nr7 = new Button("7");
        nr7.setPrefWidth(26.0);
        AnchorPane.setTopAnchor(nr7, 190.00);
        AnchorPane.setRightAnchor(nr7, 10.00);

        nr8 = new Button("8");
        nr8.setPrefWidth(26.0);
        AnchorPane.setTopAnchor(nr8, 220.00);
        AnchorPane.setRightAnchor(nr8, 10.00);

        nr9 = new Button("9");
        nr9.setPrefWidth(26.0);
        AnchorPane.setTopAnchor(nr9, 250.00);
        AnchorPane.setRightAnchor(nr9, 10.00);

        c = new Button("C");
        c.setPrefWidth(26.0);
        AnchorPane.setTopAnchor(c, 280.00);
        AnchorPane.setRightAnchor(c, 10.00);


        this.getChildren().addAll(nr1, nr2, nr3, nr4, nr5, nr6, nr7, nr8, nr9, c);
        this.setStyle("-fx-border-color: black; -fx-background-color: black;");
    }

    private void addEventHandlers(Controller controller) {

        EventHandler<MouseEvent> nr1Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.addNrToSquare(1);
            }
        }; nr1.addEventHandler(MouseEvent.MOUSE_CLICKED, nr1Clicked);

        EventHandler<MouseEvent> nr2Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.addNrToSquare(2);
            }
        }; nr2.addEventHandler(MouseEvent.MOUSE_CLICKED, nr2Clicked);

        EventHandler<MouseEvent> nr3Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.addNrToSquare(3);
            }
        }; nr3.addEventHandler(MouseEvent.MOUSE_CLICKED, nr3Clicked);

        EventHandler<MouseEvent> nr4Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.addNrToSquare(4);
            }
        }; nr4.addEventHandler(MouseEvent.MOUSE_CLICKED, nr4Clicked);

        EventHandler<MouseEvent> nr5Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.addNrToSquare(5);
            }
        }; nr5.addEventHandler(MouseEvent.MOUSE_CLICKED, nr5Clicked);

        EventHandler<MouseEvent> nr6Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.addNrToSquare(6);
            }
        }; nr6.addEventHandler(MouseEvent.MOUSE_CLICKED, nr6Clicked);

        EventHandler<MouseEvent> nr7Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.addNrToSquare(7);
            }
        }; nr7.addEventHandler(MouseEvent.MOUSE_CLICKED, nr7Clicked);

        EventHandler<MouseEvent> nr8Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.addNrToSquare(8);
            }
        }; nr8.addEventHandler(MouseEvent.MOUSE_CLICKED, nr8Clicked);

        EventHandler<MouseEvent> nr9Clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.addNrToSquare(9);
            }
        }; nr9.addEventHandler(MouseEvent.MOUSE_CLICKED, nr9Clicked);

        EventHandler<MouseEvent> cClicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.clearSquare();
            }
        }; c.addEventHandler(MouseEvent.MOUSE_CLICKED, cClicked);
    }
}
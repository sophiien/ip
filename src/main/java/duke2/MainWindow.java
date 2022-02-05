package duke2;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow {
        @FXML
        private ScrollPane scrollPane;
        @FXML
        private VBox dialogContainer;
        @FXML
        private TextField userInput;
        @FXML
        private Button sendButton = new Button("Send");

        private Duke duke;

        private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

        @FXML
        public void initialize() {
            scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        }

        public void setDuke(Duke d) {
            duke = d;
        }

        /**
         * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
         * the dialog container. Clears the user input after processing.
         */
        @FXML
        private void handleUserInput() {
            String input = userInput.getText();
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }

//        scrollPane.setContent(dialogContainer);
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//    //ensures that vbox reacts to own size changing and scrolling
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


    // event handlers


//
//    HBox hb = new HBox();
//        HBox.setHgrow(userInput, Priority.ALWAYS);
//        hb.getChildren().addAll(userInput, sendButton);
//
//    AnchorPane mainLayout = new AnchorPane();
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 10.0);
//        AnchorPane.setBottomAnchor(scrollPane, 50.0);
//        AnchorPane.setRightAnchor(scrollPane, 10.0);
//        AnchorPane.setLeftAnchor(scrollPane, 10.0);
//        AnchorPane.setLeftAnchor(hb, 10.0);
//        AnchorPane.setRightAnchor(hb, 10.0);
//        AnchorPane.setBottomAnchor(hb, 10.0);
//
//        mainLayout.getChildren().addAll(scrollPane, hb);
    }


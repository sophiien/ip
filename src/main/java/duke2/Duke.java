package duke2;// package duke1;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke  {

    private Storage storage;
    private Tasklist tasks;
    private Ui ui;
    public static int j = 0;

    public Duke(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = new Tasklist(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e.getMessage());
            tasks = new Tasklist();
        }
    }

    public Duke() {

    }
    public void run() {
        ui.welcomeMsg();
        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split(" ");
        while (!(input[0].toLowerCase().equals("bye"))) {
            String command = input[0].toLowerCase();
            Parser p = new Parser();
            p.commandToTask(command, this.tasks.getTasks(), input, this.storage.getPath());
            input = s.nextLine().split(" ");
        }
        ui.endMsg();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }
    public String getResponse(String input) {
        return "Repeat: " + input;
    }

    public static void main(String[] args) {
        new Duke("/Users/Sony/Downloads/ip1/src/data/duke.txt").run();
    }
}
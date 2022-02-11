package duke2;// package duke1;

import java.util.Scanner;
import java.io.FileNotFoundException;
//import javafx.scene.control.Label;

public class Duke  {

    private Storage storage;
    private Tasklist tasks;
    private Ui ui;
    public static int j = 0;

    /**
     * Constructor for Duke
     *
     * @param path
     * @returns new Duke object
     */
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

    /**
     * Constructor for Duke
     *
     * @returns new Deadline object
     */
    public Duke() {

    }

    /**
     * To run Duke UI
     */
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
     * Get response from other duke
     *
     * @param input for response
     * @returns response to be echoed
     */
    public String getResponse(String input) {
        return "Repeat: " + input;
    }

    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
}
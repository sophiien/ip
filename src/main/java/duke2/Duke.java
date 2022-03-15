package duke2;// package duke1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileNotFoundException;
//import javafx.scene.control.Label;

public class Duke  {

    private Storage storage;
    private Tasklist tasks;
    private Ui ui;
    public static int totalCount = 0;

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
     * @returns new Duke object
     */
    public Duke() {

    }

    /**
     * To run Duke UI
     */
    public void run() {
        ui.printWelcomeMsg();
        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split(" ");
        while (!(input[0].toLowerCase().equals("bye"))) {
            String command = input[0].toLowerCase();
            Parser p = new Parser();
            p.commandToTask(command, this.tasks.getTasks(), input, this.storage.getPath());
            input = s.nextLine().split(" ");
        }
        ui.printEndMsg();
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

    public static void main(String[] args) throws IOException {
        String currDir = System.getProperty("user.dir");
        String filePath = "src/data/duke.txt";
        File f = new File(currDir+"/duke.txt");
        System.out.println(f.createNewFile());
//        new Duke("src/data/duke.txt").run();
        if (f.createNewFile()) {
            Path dp = Paths.get("/src/data/");
            Files.createDirectories(dp);
            System.out.println(dp);
            Path p = Paths.get(currDir+"/src/data/duke.txt");
            System.out.println(p);
            Files.createFile(p);
            new Duke(currDir+"/src/data/duke.txt").run();
        } else {
            new Duke(filePath).run();
        }
    }
}
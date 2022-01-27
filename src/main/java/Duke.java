import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

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

    public void run() {
        ui.welcomeMsg();
        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split(" ");
        while (!(input[0].toLowerCase().equals("bye"))) {
            String command = input[0].toLowerCase();
            Parser p = new Parser();
            p.commandToTask(command, this.tasks.getTasks(), input, this.storage.getPath());
            input = s.nextLine().split(" ");
            continue;
        }
        ui.endMsg();
    }

    public static void main(String[] args) {
        new Duke("../../data/duke.txt").run();
    }
}

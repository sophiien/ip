package duke2;// package duke1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private String path;

    /**
     * Constructor for storage
     * 
     * @param path of storage file
     */
    Storage(String path) {
        this.path = path;
    }

    /**
     * Write text to file in path
     * 
     * @param text
     * @throws IOException
     */
    public void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(this.path, true);
        fw.write(text);
        fw.write(System.getProperty("line.separator"));
        fw.close();

    }

    /**
     * Loads data from storage to current tasklist
     * 
     * @return ArrayList of the tasks in the storage db
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(this.path); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            Parser p = new Parser();
            Task t = p.parseStringToTask(s.nextLine());
            tasks.add(t);
        }
        return tasks;
    }

    /**
     * Get path of storage
     * 
     * @return string ot the storage path
     */
    public String getPath() {
        return this.path;
    }
}

package duke2;// package duke1;

import static duke2.Parser.dateFormat;
import static duke2.Parser.timeFormat;

public class Deadline extends Task {
    private String date;

    /**
     * Constructor for Deadline
     * 
     * @param name
     * @param date
     * @returns new Deadline object
     */
    Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    public void createDeadlineFromString(String[] input) {
        for (int i = 1; i < input.length; i++) {
            if (!input[i].equals("(by:")) {
                if (input[i + 1].equals("(by:")) {
                    this.name += input[i];
                    continue;
                }
                this.name += input[i] + " ";
            } else {
                this.date += input[i + 1] + "-" + input[i + 2] + "-" + input[i + 3] + " " + input[i + 4];
                break;
            }
        }
    }


    public void createDeadlineFromCommand(String[] input) {
        for (int i = 1; i < input.length; i++) {
            if (input[i].equals("/by:")) {
                String date = input[i + 1];
                String time = input[i + 2];
                this.date += dateFormat(date) + timeFormat(time);
                break;
            }
            this.name += input[i] + " ";
        }
    }

    /**
     * Deadline toString()
     * 
     * @return Formatted string for deadline object
     */
    public String toString() {
        if (done) {
            return "[D][X] " + this.name + " (by: " + this.date + ")";
        }
        return "[D][] " + this.name + " (by: " + this.date + ")";
    }
}
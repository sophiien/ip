package duke2;// package duke1;

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
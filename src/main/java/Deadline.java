package Duke;

public class Deadline extends Task {
    private String date;

    Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    public String toString() {
        if (done) {
            return "[D][X]" + this.name + " (by: " + this.date + ")";
        }
        return "[D][]" + this.name + " (by: " + this.date + ")";
    }
}
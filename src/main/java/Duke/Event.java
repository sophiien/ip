package Duke;

public class Event extends Task {

    private String event;

    Event(String name, String event) {
        super(name);
        this.event = event;
    }

    public String toString() {
        if (done) {
            return "[E][X] " + this.name + " (at: " + this.event + ")";
        }
        return "[E][] " + this.name + " (at: " + this.event + ")";
    }
}
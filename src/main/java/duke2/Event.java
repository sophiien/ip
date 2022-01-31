package duke2;// package duke1;

public class Event extends Task {

    private String event;

    /**
     * Constructor for Event object
     * 
     * @param name
     * @param event
     */
    Event(String name, String event) {
        super(name);
        this.event = event;
    }

    /**
     * Event toString()
     * 
     * @return Formatted string for event object
     */
    public String toString() {
        if (done) {
            return "[E][X] " + this.name + " (at: " + this.event + ")";
        }
        return "[E][] " + this.name + " (at: " + this.event + ")";
    }
}
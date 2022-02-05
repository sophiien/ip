package Duke;

public class Task {
    protected boolean done = false;
    protected String name;

    /**
     * Constructor for task
     * @param name of task
     */
    Task(String name) {
        this.name = name;
    }

    /**
     * Mark task as done
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Unmark task as undone
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Formats string of task object
     * @return formatted string
     */
    public String toString() {
        if (done) {
            return "[X] " + this.name;
        }
        return "[] " + this.name;
    }
}
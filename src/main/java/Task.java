public class Task {
    private boolean done = false;
    private String name;

    Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String toString() {
        if (done) {
            return "[X] " + this.name;
        }
        return "[] " + this.name;
    }
}
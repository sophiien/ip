package duke2;// package duke1;

public class Task {
    protected boolean done = false;
    protected String name;

    Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done:\n" + this.name);

    }

    public void unmark() {
        this.done = false;
        System.out.println("OK, I've marked this task as not done yet:\n" + this.name);

    }

    public String toString() {
        if (done) {
            return "[X] " + this.name;
        }
        return "[] " + this.name;
    }
}
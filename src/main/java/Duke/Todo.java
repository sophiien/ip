package Duke;

public class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    public String toString() {
        if (done) {
            return "[T][X] " + this.name;
        }
        return "[T][] " + this.name;
    }
}
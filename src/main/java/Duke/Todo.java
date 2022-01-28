package Duke;

public class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    /**
     * toString() method for todo
     * @return string
     */
    public String toString() {
        if (done) {
            return "[T][X] " + this.name;
        }
        return "[T][] " + this.name;
    }
}
// package duke1;

import java.util.*;

public class Tasklist {
    private ArrayList<Task> tasks = new ArrayList<>();

    Tasklist() {

    }

    Tasklist(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public void addTask(String command, String name, String date) {
        if (command.equals("event")) {
            this.tasks.add(new Event(name, date));
        } else if (command.equals("deadline")) {
            this.tasks.add(new Deadline(name, date));
        } else {
            this.tasks.add(new Todo(name));
        }
    }

    public void addTaskObject(Task t) {
        this.tasks.add(t);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    @Override
    public String toString() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= tasks.size() && tasks.get(i - 1) != null; i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
        return "";
    }
}

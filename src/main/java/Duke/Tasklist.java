package Duke;

import java.util.*;

public class Tasklist {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for empty tasklist
     */
    Tasklist() {

    }

    /**
     * Adds inputted tasks to the existing tasklist
     * @param tasks to add
     */
    Tasklist(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Add task to the list
     * @param command on how to manipulate list
     * @param name of task
     * @param date of task
     */
    public void addTask(String command, String name, String date) {
        if (command.equals("event")) {
            this.tasks.add(new Event(name, date));
        } else if (command.equals("deadline")) {
            this.tasks.add(new Deadline(name, date));
        } else {
            this.tasks.add(new Todo(name));
        }
    }

    /**
     * Get lists of task
     * @return arraylist of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * tostring method for tasklist
     * @return string of tasklist
     */
    @Override
    public String toString() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= tasks.size() && tasks.get(i - 1) != null; i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
        return "";
    }
}

package duke2;// package duke1;

import java.util.ArrayList;

public class Ui {

    /**
     * Prints welcome msg of chatbot
     */
    public void printWelcomeMsg() {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }

    /**
     * Prints ending msg of chatbot
     */
    public void printEndMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printAddedTask(ArrayList<Task> list) {
        System.out.println("Got it. I have added this task: " + list.get(list.size() - 1));
        Duke.totalCount += 1;
        System.out.println("Now you have " + list.size() + " item in the list");
    }

    public static void printDeletedTask(ArrayList<Task> list, Task t) {
        Duke.totalCount--;
        System.out.println("Noted. I've removed this task: \n" + t);
        System.out.println("Now you have " + list.size() + " item in the list");
    }

    public static void printIdk() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void printEmptyError(String[] input) {
        System.out.println("OOPS!!! The description of a " + input[0] + " cannot be empty.");
    }

    public static void printList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= list.size() && list.get(i - 1) != null; i++) {
            System.out.println(i + ". " + list.get(i - 1));
        }
    }

    public static void printUndo() {
        System.out.println("I have undone the previous command");
    }
}

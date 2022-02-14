package duke2;// package duke1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


public class Parser {

    /**
     * Writes given text to file in given path
     * 
     * @param path of file
     * @param text to add
     * @throws IOException
     * @return void
     */
    public static void writeToFile(String path, String text) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(text);
        fw.write(System.getProperty("line.separator"));
        fw.close();

    }

    /**
     * Formats date to mmm-dd-yyyy
     * 
     * @param date to format
     * @return formatted date in mmm-dd-yyy
     */
    public static String dateFormat(String date) {
        LocalDate d1 = LocalDate.parse(date);
        return d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy "));
    }

    /**
     * Formats time to 12h
     * 
     * @param time to format
     * @return formatted time in 12h
     */
    public static String timeFormat(String time) {
        int i = Integer.parseInt(time);
        if (i > 0000 && i < 1100) {
            return i + "am";
        } else if (i >= 1300 && i < 2359) {
            char[] c = time.toCharArray();
            String min = new StringBuilder().append(c[2]).append(c[3]).toString();
            String hour = new StringBuilder().append(c[0]).append(c[1]).toString();

            int h = Integer.parseInt(hour) - 12;
            return String.valueOf(h) + min + "pm";
        } else {
            return i + "pm";
        }

    }

    public void parseFindCommand(String[] input, ArrayList<Task> list) {
        String find = input[1];
        Tasklist tasks = new Tasklist();
        for (Task t : list) {
            String[] s = t.toString().split(" ");
            ArrayList<String> ls = new ArrayList<>(Arrays.asList(s));
            if (ls.contains(find)) {
                tasks.addTaskObject(t);
            }
        }
        System.out.println(tasks);
    }


    /**
     * Parses string to create new task object
     * 
     * @param task to create
     * @return new task from string
     */
    public static Task parseStringToTask(String task) {
        String taskcopy = task;
        String[] input = task.split(" ");
        String[] inputcopy = taskcopy.split("\\[");

        if (inputcopy[1].equals("E]")) {
            Event e = new Event("", "");
            e.createEventFromString(input);
            return e;

        } else if (inputcopy[1].equals("D]")) {
            Deadline d = new Deadline("", "");
            d.createDeadlineFromString(input);
            return d;

        } else {
            String name = "";
            for (int i = 1; i < input.length; i++) {
                name += input[i] + " ";
            }
            return new Todo(name);
        }
    }

    /**
     * Parses command inputted by user
     * 
     * @param command from user
     * @param list    existing tasklist
     * @param input   full input from user
     * @param path    of file from storage
     */
    public void commandToTask(String command, ArrayList<Task> list, String[] input, String path) {

        if (command.equals("list")) {
            Ui.printList(list);
        } else if (command.equals("mark")) {
            int i = Integer.parseInt(input[1]);
            list.get(i - 1).mark();
        } else if (command.equals("unmark")) {
            int i = Integer.parseInt(input[1]);
            list.get(i - 1).unmark();
        } else if (command.equals("undo")) {
            int lastIdx = list.size() -1;
            list.remove(lastIdx);
            Ui.printUndo();
        } else {
            assert input.length > 1 : "Description cannot be empty";
            if (input.length == 1) {
                Ui.printEmptyError(input);
            }
            if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                if (command.equals("todo")) {
                    Todo t1 = new Todo("");
                    t1.createTodoFromCommand(input);
                    list.add(t1);
                    Error.error(path, t1);
                } else if (command.equals("deadline")) {
                    Deadline d1 = new Deadline("", "");
                    d1.createDeadlineFromCommand(input);
                    list.add(d1);
                    Error.error(path, d1);
                } else if (command.equals("event")) {
                    Event e1 = new Event("", "");
                    e1.createEventFromCommand(input);
                    list.add(e1);
                    Error.error(path, e1);
                }
                Ui.printAddedTask(list);
            } else if (command.equals("delete")) {
                int i = Integer.parseInt(input[1]);
                Task t = list.remove(i - 1);
                Ui.printDeletedTask(list, t);

            } else if (command.equals("find")) {
                parseFindCommand(input, list);
            } else {
                Ui.printIdk();
            }
        }
    }
}

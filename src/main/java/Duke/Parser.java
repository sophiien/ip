package Duke;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

// TODO FIX THE FORMATTING OF THE LIST BUT I THINK OTHER THAN THAT EVERYTHINGS FINE

public class Parser {

    /**
     * Writes given text to file in given path
     * @param path of file
     * @param text to add
     * @throws IOException
     * @returns void
     */
    public static void writeToFile(String path, String text) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(text);
        fw.write(System.getProperty("line.separator"));
        fw.close();

    }

    /**
     * Formats date to mmm-dd-yyyy
     * @param date to format
     * @return formatted date in mmm-dd-yyy
     */
    public static String dateFormat(String date) {
        LocalDate d1 = LocalDate.parse(date);
        return d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy "));
    }

    /**
     * Formats time to 12h
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

    /**
     * Parses string to create new task object
     * @param task to create
     * @return new task from string
     */
    public static Task parseStringToTask(String task) {
        String taskcopy = task;
        String[] input = task.split(" ");
        String[] inputcopy = taskcopy.split("\\[");
        if (inputcopy[1].equals("E]")) {
            String name = "";
            String date = "";
            for (int i = 1; i < input.length; i++) {
                if (input[i] != "(at:") {
                    name += input[i];
                } else {
                    date += input[i + 1] + "-" + input[i + 2] + "-" + input[i + 3];
                    break;
                }
            }
            return new Event(name, date);

        } else if (inputcopy[1].equals("D]")) {
            String name = "";
            String date = "";
            for (int i = 1; i < input.length; i++) {
                if (!input[i].equals("(by:")) {
                    name += input[i];
                } else {
                    date += input[i + 1] + "-" + input[i + 2] + "-" + input[i + 3];
                    break;
                }
            }
            return new Deadline(name, date);

        } else {
            String name = "";
            for (int i = 1; i < input.length; i++) {
                name += input[i];
            }
            return new Todo(name);
        }
    }

    /**
     * Parses command inputted by user
     * @param command from user
     * @param list existing tasklist
     * @param input full input from user
     * @param path of file from storage
     */
    public void commandToTask(String command, ArrayList<Task> list, String[] input, String path) {
        if (command.equals("list")) {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 1; i <= list.size() && list.get(i - 1) != null; i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
        } else if (command.equals("mark")) {
            int i = Integer.parseInt(input[1]);
            list.get(i - 1).mark();
            System.out.println("Nice! I've marked this task as done:\n" + list.get(i - 1));
        } else if (command.equals("unmark")) {
            int i = Integer.parseInt(input[1]);
            list.get(i - 1).unmark();
            System.out.println("OK, I've marked this task as not done yet:\n" + list.get(i - 1));
        } else {
            String name = "";
            if (input.length == 1) {
                System.out.println("OOPS!!! The description of a " + input[0] + " cannot be empty.");
            }
            if (command.equals("todo")) {
                for (int i = 1; i < input.length; i++) {
                    name += " " + input[i];
                }
                Todo t1 = new Todo(name);
                list.add(t1);
                try {
                    writeToFile(path, t1.toString());
                } catch (IOException e) {
                    System.out.println("error: " + e.getMessage());
                }
                int total = Duke.j + list.size();
                System.out.println("Got it. I have added this task: " + list.get(total - 1));
                Duke.j += 1;
                System.out.println("Now you have " + total + " items in the list");
            } else if (command.equals("deadline") || command.equals("event")) {
                String deadline = "";
                if (command.equals("deadline")) {
                    for (int i = 1; i < input.length; i++) {
                        if (input[i].equals("/by:")) {
                            String date = input[i + 1];
                            String time = input[i + 2];
                            deadline += dateFormat(date) + timeFormat(time);
                            break;
                        }
                        name += " " + input[i];
                    }
                    Deadline d1 = new Deadline(name, deadline);
                    list.add(d1);
                    try {
                        writeToFile(path, d1.toString());
                    } catch (IOException e) {
                        System.out.println("error: " + e.getMessage());
                    }

                } else if (command.equals("event")) {
                    for (int i = 1; i < input.length; i++) {
                        if (input[i].equals("/at:")) {
                            String date = input[i + 1];
                            String time = input[i + 2];
                            deadline += dateFormat(date) + timeFormat(time);
                            break;
                        }
                        name += " " + input[i];
                    }
                    Event e1 = new Event(name, deadline);
                    list.add(e1);
                    try {
                        writeToFile(path, e1.toString());
                    } catch (IOException e) {
                        System.out.println("error: " + e.getMessage());
                    }
                }
                System.out.println("Got it. I have added this task: " + list.get(list.size() - 1));
                Duke.j += 1;
                System.out.println("Now you have " + list.size() + " item in the list");
            } else if (command.equals("delete")) {
                int i = Integer.parseInt(input[1]);
                Task t = list.remove(i - 1);
                Duke.j--;
                System.out.println("Noted. I've removed this task: \n" + t);
                System.out.println("Now you have " + list.size() + " item in the list");
            } else if (command.equals("find")) {
                String find = input[1];
                Tasklist tasks = new Tasklist();
                for (Task t: list) {
                    String[] s = t.toString().split(" ");
                    ArrayList<String> ls = new ArrayList<>(Arrays.asList(s));
                    if (ls.contains(find)) {
                        tasks.addTask(t);
                    }
                }
                System.out.println(tasks);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}

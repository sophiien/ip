import java.util.Scanner;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    public static String dateFormat(String date) {
        LocalDate d1 = LocalDate.parse(date);
        return d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy "));
    }

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

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner s = new Scanner(System.in);
        int j = 0;
        ArrayList<Task> list = new ArrayList<>();
        String[] input = s.nextLine().split(" ");
        while (!(input[0].toLowerCase().equals("bye"))) {
            String command = input[0].toLowerCase();
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i <= list.size() && list.get(i - 1) != null; i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                input = s.nextLine().split(" ");
                continue;
            } else if (command.equals("mark")) {
                int i = Integer.parseInt(input[1]);
                list.get(i - 1).mark();
                System.out.println("Nice! I've marked this task as done:\n" + list.get(i - 1));
                input = s.nextLine().split(" ");
                continue;
            } else if (command.equals("unmark")) {
                int i = Integer.parseInt(input[1]);
                list.get(i - 1).unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + list.get(i - 1));
                input = s.nextLine().split(" ");
                continue;
            } else {
                String name = "";
                if (input.length == 1) {
                    System.out.println("OOPS!!! The description of a " + input[0] + " cannot be empty.");
                    input = s.nextLine().split(" ");
                    continue;
                }
                if (command.equals("todo")) {
                    for (int i = 1; i < input.length; i++) {
                        name += " " + input[i];
                    }
                    list.add(new Todo(name));
                    System.out.println("Got it. I have added this task: " + list.get(j));
                    j += 1;
                    System.out.println("Now you have " + j + " items in the list");
                    input = s.nextLine().split(" ");
                    continue;
                } else if (command.equals("deadline") || command.equals("event")) {
                    String deadline = "";
                    if (command.equals("deadline")) {
                        for (int i = 1; i < input.length; i++) {
                            if (input[i].equals("/by")) {
                                // for (int k = i + 1; k < input.length; k++) {
                                // deadline += " " + input[k];
                                // }
                                String date = input[i + 1];
                                String time = input[i + 2];
                                deadline += dateFormat(date) + timeFormat(time);
                                break;
                            }
                            name += " " + input[i];
                        }
                        list.add(new Deadline(name, deadline));

                    } else if (command.equals("event")) {
                        for (int i = 1; i < input.length; i++) {
                            if (input[i].equals("/at")) {
                                String date = input[i + 1];
                                String time = input[i + 2];
                                deadline += dateFormat(date) + timeFormat(time);
                                break;
                            }
                            name += " " + input[i];
                        }
                        list.add(new Event(name, deadline));
                    }
                    System.out.println("Got it. I have added this task: " + list.get(j));
                    j += 1;
                    System.out.println("Now you have " + j + " item in the list");
                    input = s.nextLine().split(" ");
                    continue;
                } else if (command.equals("delete")) {
                    int i = Integer.parseInt(input[1]);
                    Task t = list.remove(i - 1);
                    j--;
                    System.out.println("Noted. I've removed this task: \n" + t);
                    System.out.println("Now you have " + j + " item in the list");
                    input = s.nextLine().split(" ");
                    continue;
                } else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    input = s.nextLine().split(" ");
                    continue;
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

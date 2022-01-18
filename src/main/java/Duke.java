import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner s = new Scanner(System.in);
        int j = 0;
        Task[] list = new Task[100];
        String input = s.next();
        while (!(input.toLowerCase().equals("bye"))) {
            if (input.toLowerCase().equals("list")){
                for (int i = 1; i < list.length && list[i-1] != null; i++) {
                    System.out.println(i + ". " + list[i-1]);
                }
                input = s.next();
                continue;
            } else if (input.toLowerCase().equals("mark")) {
                int i = Integer.parseInt(s.next());
                list[i-1].mark();
                System.out.println("Nice! I've marked this task as done:\n" + list[i-1]);
                input = s.next();
                continue;
            } else if (input.toLowerCase().equals("unmark")) {
                int i = Integer.parseInt(s.next());
                list[i-1].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + list[i-1]);
                input = s.next();
                continue;
            }
            System.out.println("added: " + input);
            list[j] = new Task(input);
            j +=1;
            input = s.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

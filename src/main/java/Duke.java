import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner s = new Scanner(System.in);
        int j = 0;
        String[] list = new String[100];
        String input = s.nextLine();
        while (!(input.toLowerCase().equals("bye"))) {
            if (input.toLowerCase().equals("list")){
                for (int i = 1; i < list.length && list[i-1] != null; i++) {
                    System.out.println(i + ". " + list[i-1]);
                }
                input = s.nextLine();
                continue;
            }
            System.out.println("added: " + input);
            list[j] = input;
            j +=1;
            input = s.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

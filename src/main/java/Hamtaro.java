import java.util.Scanner;
import java.util.ArrayList;

public class Hamtaro {

    public static void printBanner(){
        System.out.println("____________________________________________________________");
        String banner = "    __  __                __                 \n"
                + "   / / / /___ _____ ___  / /_____ __________ \n"
                + "  / /_/ / __ `/ __ `__ \\/ __/ __ `/ ___/ __ \\\n"
                + " / __  / /_/ / / / / / / /_/ /_/ / /  / /_/ /\n"
                + "/_/ /_/\\__,_/_/ /_/ /_/\\__/\\__,_/_/   \\____/ \n";
        System.out.println(banner);
        System.out.println("Hello! I'm Hamtaro!.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        printBanner();
        ArrayList<Task> list = new ArrayList<>();

        while(flag){
            String userInput = scanner.nextLine();
            String[] words = userInput.split(" ");

            if(userInput.startsWith("mark")) {
                if (isInteger(words[1])) {
                    int taskNumber = Integer.parseInt(words[1]);
                    list.get(taskNumber - 1).mark();

                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(list.get(taskNumber - 1).toString());
                    System.out.println("____________________________________________________________");
                }
            }

            else if(userInput.startsWith("unmark")){
                if(isInteger(words[1])){
                    int taskNumber = Integer.parseInt(words[1]);
                    list.get(taskNumber - 1).unmark();

                    System.out.println("____________________________________________________________");
                    System.out.println("Ok! I've marked this task as undone: ");
                    System.out.println(list.get(taskNumber - 1).toString());
                    System.out.println("____________________________________________________________");
                }
            }

            else if(userInput.startsWith("todo")){
                String desc = userInput.substring(5);
                Todo todo = new Todo(desc);
                list.add(todo);

                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(todo.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
                System.out.println("____________________________________________________________");
            }

            else if(userInput.startsWith("deadline")){
                String details = userInput.substring(9);
                String[] parts = details.split("/by", 2);
                Deadline deadline = new Deadline(parts[0],parts[1]);
                list.add(deadline);

                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
                System.out.println("____________________________________________________________");
            }

            else if (userInput.startsWith("event")){
                String details = userInput.substring(6); // remove "event "

                String[] parts = details.split(" /from | /to ");

                String description = parts[0];
                String from = parts[1];
                String to = parts[2];

                Event event = new Event(description,from,to);
                list.add(event);

                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(event.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
                System.out.println("____________________________________________________________");
            }

            else if(userInput.startsWith("list")){
                for(int i = 0; i < list.size();i++){
                    System.out.println( (i+1) + ". " + list.get(i).toString());
                }
            }

            else if(userInput.startsWith("bye")){
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                flag = false;
            }
        }
    }
}

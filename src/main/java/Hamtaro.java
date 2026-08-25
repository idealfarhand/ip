import java.util.Scanner;
import java.util.ArrayList;

public class Hamtaro {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        Utils.printBanner();
        ArrayList<Task> list = new ArrayList<>();

        while(flag){
            String userInput = scanner.nextLine();
            String[] words = userInput.split(" ");

            if(userInput.startsWith("mark")) {
                try{
                    Utils.parseMark(userInput, list.size());

                    int taskNumber = Integer.parseInt(words[1]);
                    list.get(taskNumber - 1).mark();

                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(list.get(taskNumber - 1).toString());
                    System.out.println("____________________________________________________________");
                } catch (HamtaroException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if(userInput.startsWith("unmark")){
                try{
                    Utils.parseUnmark(userInput, list.size());
                    int taskNumber = Integer.parseInt(words[1]);
                    list.get(taskNumber - 1).unmark();

                    System.out.println("____________________________________________________________");
                    System.out.println("Ok! I've marked this task as undone: ");
                    System.out.println(list.get(taskNumber - 1).toString());
                    System.out.println("____________________________________________________________");

                } catch (HamtaroException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if(userInput.startsWith("todo")){
                try{
                    Utils.parseTodo(userInput);

                    String desc = userInput.substring(5);
                    Todo todo = new Todo(desc);
                    list.add(todo);

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                    System.out.println("____________________________________________________________");

                } catch (HamtaroException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if(userInput.startsWith("deadline")){
                try{
                    Utils.parseDeadline(userInput);

                    String details = userInput.substring(9);
                    String[] parts = details.split("/by", 2);
                    Deadline deadline = new Deadline(parts[0],parts[1]);
                    list.add(deadline);

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } catch (HamtaroException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (userInput.startsWith("event")){
                try{
                    Utils.parseEvent(userInput);
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
                } catch (HamtaroException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if(userInput.startsWith("list")){

                try{
                    Utils.parseList(userInput, list.size());
                    for(int i = 0; i < list.size();i++){
                        System.out.println( (i+1) + ". " +  list.get(i).toString());
                    }
                } catch (HamtaroException e) {
                    System.out.println(e.getMessage());
                }

            }

            else if(userInput.startsWith("bye")){
                try{
                    Utils.parseBye(userInput);
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    flag = false;
                } catch (HamtaroException e) {
                    System.out.println(e.getMessage());
                }
            }

            else{
                System.out.println("____________________________________________________________");
                System.out.println("Command doesn't exist!");
                System.out.println("____________________________________________________________");
            }
        }
    }
}

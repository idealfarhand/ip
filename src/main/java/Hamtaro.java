import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Hamtaro {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        Utils.printBanner();
        ArrayList<Task> list = loadTasks();

        while(flag){
            String userInput = scanner.nextLine();
            String[] words = userInput.split(" ");

            if(userInput.startsWith("mark")) {
                try{
                    Utils.parseMark(userInput, list.size());

                    int taskNumber = Integer.parseInt(words[1]);
                    list.get(taskNumber - 1).mark();
                    saveTasks(list);

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
                    saveTasks(list);

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
                    saveTasks(list);

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
                    try{
                        LocalDate by = Utils.parseDate(parts[1].stripLeading());
                        Deadline deadline = new Deadline(parts[0],by);
                        list.add(deadline);
                        saveTasks(list);

                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadline.toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                        System.out.println("____________________________________________________________");
                    }
                    catch(IllegalArgumentException i){
                        System.out.println(i.getMessage());
                    }

                } catch (HamtaroException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (userInput.startsWith("event")){
                try{
                    Utils.parseEvent(userInput);
                    String details = userInput.substring(6); // remove "event "

                    String[] parts = details.split(" /from | /to ");


                    try{
                        String description = parts[0];
                        LocalDateTime from = Utils.parseDateTime(parts[1].stripLeading().stripTrailing());
                        LocalDateTime to = Utils.parseDateTime(parts[2].stripLeading().stripTrailing());
                        Event event = new Event(description,from,to);
                        list.add(event);
                        saveTasks(list);

                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(event.toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                        System.out.println("____________________________________________________________");
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

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

            else if(userInput.startsWith("delete")){
                try{
                    Utils.parseDelete(userInput, list.size());

                    int taskNumber = Integer.parseInt(words[1]);
                    Task temp = list.get(taskNumber - 1);
                    list.remove(taskNumber - 1);
                    saveTasks(list);

                    System.out.println("____________________________________________________________");
                    System.out.println("Okay! I've deleted this task: ");
                    System.out.println(temp.toString());
                    System.out.println("____________________________________________________________");
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


    private static ArrayList<Task> loadTasks() {
        try {
            return Storage.loadTasks();
        } catch (IOException e) {
            System.out.println("Unable to load saved tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }

 
    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            Storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Unable to save tasks: " + e.getMessage());
        }
    }
}



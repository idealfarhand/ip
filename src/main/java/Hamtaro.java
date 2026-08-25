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
            //bye, mark number, unmark number, list, anything else add to task list
            String userInput = scanner.nextLine();
            String[] words = userInput.split(" ");

            if(words.length > 1 && isInteger(words[1])){
                int taskNumber = Integer.parseInt(words[1]);
                if(words[0].equals("mark")){
                    list.get(taskNumber - 1).mark();

                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("[" +list.get(taskNumber - 1).getStatusIcon()+ "] " + list.get(taskNumber - 1).getDescription());
                    System.out.println("____________________________________________________________");

                }

                else if(words[0].equals("unmark")){
                    list.get(taskNumber - 1).unmark();

                    System.out.println("____________________________________________________________");
                    System.out.println("Ok! I've marked this task as undone: ");
                    System.out.println("[" +list.get(taskNumber - 1).getStatusIcon()+ "] " + list.get(taskNumber - 1).getDescription());
                    System.out.println("____________________________________________________________");

                }
            }

            else{
                if(userInput.equals("bye")){
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    flag = false;
                }

                else if(userInput.equals("list")){
                    for(int i = 0; i < list.size();i++){
                        System.out.println( (i+1) + ". [" + list.get(i).getStatusIcon()+"] " + list.get(i).getDescription());
                    }
                }

                else {
                    list.add(new Task(userInput, false));
                    System.out.println("____________________________________________________________");
                    System.out.println("Added: " + userInput);
                    System.out.println("____________________________________________________________");
                }
            }
        }
    }
}

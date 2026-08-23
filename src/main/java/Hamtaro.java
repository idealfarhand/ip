import java.util.Scanner;
import java.util.ArrayList;

public class Hamtaro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
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

        ArrayList<String> list = new ArrayList<>();

        while(flag){
            String userInput = scanner.nextLine();
            if(userInput.equals("bye")){
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                flag = false;
                continue;
            }

            else if(userInput.equals("list")){
                for(int i = 0; i < list.size();i++){
                    System.out.println( (i+1) + ". " + list.get(i));
                }
            }

            list.add(userInput);
            System.out.println("____________________________________________________________");
            System.out.println("Added: " + userInput);
            System.out.println("____________________________________________________________");
        }
    }
}

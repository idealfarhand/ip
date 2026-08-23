import java.util.Scanner;

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

        while(flag){
            String userInput = scanner.nextLine();
            if(userInput.equals("bye")){
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                flag = false;
                continue;
            }

            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
        }
    }
}

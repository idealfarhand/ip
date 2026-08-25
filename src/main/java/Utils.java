public class Utils {
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

    public static void parseMark(String userInput, int arrayLen) throws HamtaroException{
        String[] words = userInput.split(" ");
        if(words.length == 1){
            throw new HamtaroException("Invalid Argument Length! Usage: mark [task number]");
        }

        if(words.length > 2){
            throw new HamtaroException("Invalid Argument Length! Usage: mark [task number]");
        }

        if(!isInteger(words[1])){
            throw new HamtaroException(words[1] + " is not a number! Usage: mark [task number]");
        }

        if(Integer.parseInt(words[1]) > arrayLen || Integer.parseInt(words[1]) < 0){
            throw new HamtaroException("Task number " + words[1] + " does not exist!");
        }
    }

    public static void parseUnmark(String userInput,int arrayLen) throws HamtaroException{
        String[] words = userInput.split(" ");
        if(words.length == 1){
            throw new HamtaroException("Invalid Argument Length! Usage: unmark [task number]");
        }

        if(words.length > 2){
            throw new HamtaroException("Invalid Argument Length! Usage: unmark [task number]");
        }

        if(!isInteger(words[1])){
            throw new HamtaroException(words[1] + " is not a number! Usage: unmark [task number]");
        }

        if(Integer.parseInt(words[1]) > arrayLen || Integer.parseInt(words[1]) < 0){
            throw new HamtaroException("Task number " + words[1] + " does not exist!");
        }
    }

    public static void parseTodo(String userInput) throws HamtaroException {
        String[] words = userInput.split(" ");
        if(words.length == 1){
            throw new HamtaroException("Invalid Argument Format! Usage: todo [task description]");
        }
    }


    public static void parseDeadline(String userInput) throws HamtaroException{
        String[] parts = userInput.split("/by");

        if (parts.length != 2) {
            throw new HamtaroException("Invalid Argument Format! Usage: deadline [task description] /by [deadline]");
        }
    }

    public static void parseEvent(String userInput) throws HamtaroException{
        String[] parts = userInput.split("/from | /to");

        if (parts.length != 3) {
            throw new HamtaroException("Invalid Argument Format! Usage: event [task description] /from [start] /to [end]");
        }

        String details = userInput.substring(6);

        int fromIndex = details.indexOf(" /from ");
        int toIndex = details.indexOf(" /to ");

        if (fromIndex == -1 || toIndex == -1) {
            throw new HamtaroException("An event must have /from and /to. Usage: event [task description] /from [start] /to [end]");
        }

        if (fromIndex > toIndex) {
            throw new HamtaroException("The /from must come before /to. Usage: event [task description] /from [start] /to [end]");
        }

        if (details.indexOf(" /from ", fromIndex + 1) != -1) {
            throw new HamtaroException("An event can only have one /from. Usage: event [task description] /from [start] /to [end]");
        }

        if (details.indexOf(" /to ", toIndex + 1) != -1) {
            throw new HamtaroException("An event can only have one /to. Usage: event [task description] /from [start] /to [end]");
        }
    }

    public static void parseList(String userInput, int size) throws HamtaroException{
        String[] parts = userInput.split(" ");
        if(size == 0){
            throw new HamtaroException("Task list is empty! Add some tasks first!");
        }
        if(parts.length > 1){
            throw new HamtaroException("Invalid Argument Length! Usage: list");
        }
    }

    public static void parseBye(String userInput) throws HamtaroException{
        String[] parts = userInput.split(" ");
        if(parts.length > 1){
            throw new HamtaroException("Invalid Argument Length! Usage: bye");
        }
    }
}

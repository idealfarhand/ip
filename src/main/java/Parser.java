public class Parser {

    /** Converts one line of input into the matching command object. */
    public Command parseCommand(String userInput) throws HamtaroException {
        String input = userInput.trim();
        if (input.isEmpty()) {
            throw new HamtaroException("Command doesn't exist!");
        }

        String[] parts = input.split("\\s+", 2);
        return switch (parts[0]) {
        case "mark" -> new MarkCommand(parseCommandTaskNumber(parts, "mark"), true);
        case "unmark" -> new MarkCommand(parseCommandTaskNumber(parts, "unmark"), false);
        case "delete" -> new DeleteCommand(parseCommandTaskNumber(parts, "delete"));
        case "todo" -> new AddTaskCommand(createTodo(parts));
        case "deadline" -> new AddTaskCommand(createDeadline(parts));
        case "event" -> new AddTaskCommand(createEvent(parts));
        case "list" -> createListCommand(parts);
        case "bye" -> createExitCommand(parts);
        default -> throw new HamtaroException("Command doesn't exist!");
        };
    }

    private int parseCommandTaskNumber(String[] parts, String command) throws HamtaroException {
        if (parts.length == 1 || parts[1].contains(" ")) {
            throw new HamtaroException("Invalid Argument Length! Usage: " + command + " [task number]");
        }
        if (!Utils.isInteger(parts[1])) {
            throw new HamtaroException(parts[1] + " is not a number! Usage: " + command + " [task number]");
        }
        return Integer.parseInt(parts[1]);
    }

    private Todo createTodo(String[] parts) throws HamtaroException {
        if (parts.length == 1 || parts[1].isBlank()) {
            throw new HamtaroException("Invalid Argument Format! Usage: todo [task description]");
        }
        return new Todo(parts[1]);
    }

    private Deadline createDeadline(String[] parts) throws HamtaroException {
        if (parts.length == 1) {
            throw new HamtaroException("Invalid Argument Format! Usage: deadline [task description] /by [deadline]");
        }
        String[] details = parts[1].split(" /by ", -1);
        if (details.length != 2 || details[0].isBlank() || details[1].isBlank()) {
            throw new HamtaroException("Invalid Argument Format! Usage: deadline [task description] /by [deadline]");
        }
        try {
            return new Deadline(details[0], Utils.parseDate(details[1].trim()));
        } catch (IllegalArgumentException e) {
            throw new HamtaroException(e.getMessage());
        }
    }

    private Event createEvent(String[] parts) throws HamtaroException {
        if (parts.length == 1) {
            throw new HamtaroException("Invalid Argument Format! Usage: event [task description] /from [start] /to [end]");
        }
        String details = parts[1];
        int fromIndex = details.indexOf(" /from ");
        int toIndex = details.indexOf(" /to ");
        if (fromIndex < 1 || toIndex < fromIndex || details.indexOf(" /from ", fromIndex + 1) != -1
                || details.indexOf(" /to ", toIndex + 1) != -1) {
            throw new HamtaroException("Invalid Argument Format! Usage: event [task description] /from [start] /to [end]");
        }
        try {
            String description = details.substring(0, fromIndex);
            String from = details.substring(fromIndex + 7, toIndex).trim();
            String to = details.substring(toIndex + 5).trim();
            return new Event(description, Utils.parseDateTime(from), Utils.parseDateTime(to));
        } catch (IllegalArgumentException e) {
            throw new HamtaroException(e.getMessage());
        }
    }

    private Command createListCommand(String[] parts) throws HamtaroException {
        if (parts.length > 1) {
            throw new HamtaroException("Invalid Argument Length! Usage: list");
        }
        return new ListCommand();
    }

    private Command createExitCommand(String[] parts) throws HamtaroException {
        if (parts.length > 1) {
            throw new HamtaroException("Invalid Argument Length! Usage: bye");
        }
        return new ExitCommand();
    }

    public void parseMark(String userInput, int arrayLen) throws HamtaroException {
        String[] words = userInput.split(" ");
        if (words.length == 1) {
            throw new HamtaroException("Invalid Argument Length! Usage: mark [task number]");
        }

        if (words.length > 2) {
            throw new HamtaroException("Invalid Argument Length! Usage: mark [task number]");
        }

        if (!Utils.isInteger(words[1])) {
            throw new HamtaroException(words[1] + " is not a number! Usage: mark [task number]");
        }

        if (Integer.parseInt(words[1]) > arrayLen || Integer.parseInt(words[1]) < 0) {
            throw new HamtaroException("Task number " + words[1] + " does not exist!");
        }
    }

    public void parseUnmark(String userInput, int arrayLen) throws HamtaroException {
        String[] words = userInput.split(" ");
        if (words.length == 1) {
            throw new HamtaroException("Invalid Argument Length! Usage: unmark [task number]");
        }

        if (words.length > 2) {
            throw new HamtaroException("Invalid Argument Length! Usage: unmark [task number]");
        }

        if (!Utils.isInteger(words[1])) {
            throw new HamtaroException(words[1] + " is not a number! Usage: unmark [task number]");
        }

        if (Integer.parseInt(words[1]) > arrayLen || Integer.parseInt(words[1]) < 0) {
            throw new HamtaroException("Task number " + words[1] + " does not exist!");
        }
    }

    public void parseTodo(String userInput) throws HamtaroException {
        String[] words = userInput.split(" ");
        if (words.length == 1) {
            throw new HamtaroException("Invalid Argument Format! Usage: todo [task description]");
        }
    }


    public void parseDeadline(String userInput) throws HamtaroException {
        String[] parts = userInput.split("/by");

        if (parts.length != 2) {
            throw new HamtaroException("Invalid Argument Format! Usage: deadline [task description] /by [deadline]");
        }
    }

    public void parseEvent(String userInput) throws HamtaroException {
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

    public void parseList(String userInput, int size) throws HamtaroException {
        String[] parts = userInput.split(" ");
        if (size == 0) {
            throw new HamtaroException("Task list is empty! Add some tasks first!");
        }
        if (parts.length > 1) {
            throw new HamtaroException("Invalid Argument Length! Usage: list");
        }
    }

    public void parseBye(String userInput) throws HamtaroException {
        String[] parts = userInput.split(" ");
        if (parts.length > 1) {
            throw new HamtaroException("Invalid Argument Length! Usage: bye");
        }
    }

    public void parseDelete(String userInput, int arrayLen) throws HamtaroException {
        String[] words = userInput.split(" ");
        if (words.length == 1) {
            throw new HamtaroException("Invalid Argument Length! Usage: delete [task number]");
        }

        if (words.length > 2) {
            throw new HamtaroException("Invalid Argument Length! Usage: delete [task number]");
        }

        if (!Utils.isInteger(words[1])) {
            throw new HamtaroException(words[1] + " is not a number! Usage: delete [task number]");
        }

        if (Integer.parseInt(words[1]) > arrayLen || Integer.parseInt(words[1]) < 0) {
            throw new HamtaroException("Task number " + words[1] + " does not exist!");
        }
    }
}


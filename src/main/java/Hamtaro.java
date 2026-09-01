import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Hamtaro {

    /**
     * Runs the command-object based command loop.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList tasks = Utils.loadTasks();

        boolean isRunning = true;

        ui.showWelcome();
        while (isRunning) {
            try {
                Command command = parser.parseCommand(ui.readCommand());
                command.execute(tasks, ui);
                if (command.changesTasks()) {
                    Utils.saveTasks(tasks);
                }
                isRunning = !command.isExit();
            } catch (HamtaroException e) {
                ui.showError(e.getMessage());
            }
        }
    }

}



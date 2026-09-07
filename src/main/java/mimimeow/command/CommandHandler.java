package mimimeow.command;

import mimimeow.task.Deadline;
import mimimeow.task.Event;
import mimimeow.task.Task;
import mimimeow.task.TaskList;
import mimimeow.task.Todo;
import mimimeow.ui.MimiMeowUi;

/** Executes parsed MimiMeow commands and updates the task list. */
public class CommandHandler {
    private final TaskList taskList;
    private final CommandParser commandParser;
    private final MimiMeowUi ui;

    /** Creates a command handler with the supplied application components. */
    public CommandHandler(TaskList taskList, CommandParser commandParser, MimiMeowUi ui) {
        this.taskList = taskList;
        this.commandParser = commandParser;
        this.ui = ui;
    }

    /** Executes a command and returns whether MimiMeow should exit. */
    public boolean execute(String userInput) {
        ui.showSeparator();
        try {
            Command command = commandParser.parse(userInput);
            switch (command.getWord()) {
            case "bye":
                ui.showGoodbyeMessage();
                ui.showSeparator();
                return true;
            case "list":
                ui.showTaskList(taskList);
                break;
            case "mark":
                updateTaskStatus(command.getArguments(), true);
                break;
            case "unmark":
                updateTaskStatus(command.getArguments(), false);
                break;
            case "todo":
                addTodo(command.getArguments());
                break;
            case "deadline":
                addDeadline(command.getArguments());
                break;
            case "event":
                addEvent(command.getArguments());
                break;
            default:
                throw new MimiMeowException("Mimi does not recognise that command. Try todo, list, mark, or bye.");
            }
        } catch (MimiMeowException exception) {
            ui.showError(exception.getMessage());
        }
        ui.showSeparator();
        return false;
    }

    private void addTodo(String commandArguments) {
        String description = commandArguments.trim();
        if (description.isEmpty()) {
            throw new MimiMeowException("This todo is as empty as Mimi's food bowl. Add a description, meow.");
        }
        addTaskAndReply(new Todo(description));
    }

    private void addDeadline(String commandArguments) {
        String[] deadlineParts = commandArguments.split("\\s*/by\\s+", 2);
        if (deadlineParts.length < 2) {
            throw new MimiMeowException("Mimi needs a deadline description and date. Try: deadline description /by date.");
        }
        String description = deadlineParts[0].trim();
        String deadlineDate = deadlineParts[1].trim();
        if (description.isEmpty() || deadlineDate.isEmpty()) {
            throw new MimiMeowException("A deadline needs both a description and a date, meow.");
        }
        addTaskAndReply(new Deadline(description, deadlineDate));
    }

    private void addEvent(String commandArguments) {
        String[] eventParts = commandArguments.split("\\s*/from\\s+", 2);
        if (eventParts.length < 2) {
            throw new MimiMeowException("Mimi needs an event description, start, and end. Try: event description /from start /to end.");
        }
        String description = eventParts[0].trim();
        String[] timeParts = eventParts[1].split("\\s*/to\\s+", 2);
        if (timeParts.length < 2) {
            throw new MimiMeowException("Mimi needs both /from and /to times for this event.");
        }
        String startTime = timeParts[0].trim();
        String endTime = timeParts[1].trim();
        if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new MimiMeowException("This event needs a description, start time, and end time");
        }
        addTaskAndReply(new Event(description, startTime, endTime));
    }

    private void addTaskAndReply(Task task) {
        taskList.add(task);
        ui.showTaskAdded(task, taskList.size());
    }

    private void updateTaskStatus(String taskNumberText, boolean isDone) {
        if (taskNumberText.isEmpty()) {
            throw new MimiMeowException("Mimi needs a task number, meow.");
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberText);
        } catch (NumberFormatException exception) {
            throw new MimiMeowException("The task number must be a positive whole number.");
        }
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            throw new MimiMeowException("Mimi cannot find task " + taskNumber + ". Check the task number");
        }
        Task task = taskList.get(taskNumber - 1);
        if (isDone) {
            task.setAsDone();
        } else {
            task.setAsNotDone();
        }
        ui.showTaskStatus(task, isDone);
    }
}

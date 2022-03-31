package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.TASK;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.tasks.Task;

public class AddTaskCommand extends AddCommand{

    public static final String MESSAGE_USAGE = COMMAND_WORD + "[t] : Adds a todo task to the list of tasks."
    + "Parameters: ";

    public static final String MESSAGE_SUCCESS = "New todo task added successfully!";
    public static final String MESSAGE_DUPLICATE_TODO = "This task already exists in the task list";

    public final Task toAdd;

    /**
     * Constructor for TaskTodoCommand.
     * @param task
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), getType());
    }
    
    @Override
    public Type getType() {
        return TASK;
    }

    @Override
    public boolean equals(Object other) {
        // TODO Auto-generated method stub
        return false;
    }
}

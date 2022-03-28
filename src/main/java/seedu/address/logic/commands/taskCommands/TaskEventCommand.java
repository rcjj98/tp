package seedu.address.logic.commands.taskCommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.TASK;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.tasks.Events;

/**
 * This class will contain the execute method for the execution of the given Event command.
 */
public class TaskEventCommand extends TaskCommands {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "todo : Adds an event task to the list of tasks."
    + "Parameters: ";

    public static final String MESSAGE_SUCCESS = "New event task added successfully!";
    public static final String MESSAGE_DUPLICATE_TODO = "This task already exists in the task list";

    public final Events toAdd;

    public TaskEventCommand(Events event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskEventCommand // instanceof handles nulls
                && toAdd.equals(((TaskEventCommand) other).toAdd));
    }

    @Override 
    public Type getType() {
        return TASK;
    }
    
}

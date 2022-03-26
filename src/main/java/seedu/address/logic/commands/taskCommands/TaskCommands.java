package seedu.address.logic.commands.taskCommands;

import java.util.ArrayList;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tasks.Task;

/**
 * Overarching abstract class that holds all the required methods of children
 * command classes.
 */
public abstract class TaskCommands extends Command {

    public static final String COMMAND_WORD = "task";

    protected ArrayList<Task> tasks;    

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
    
    @Override
    public abstract boolean equals(Object other);

}

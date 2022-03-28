package seedu.address.logic.commands.taskCommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.TASK;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tasks.Deadlines;

public class TaskDeadlineCommand extends TaskCommands {
    public static final String COMMAND_WORD = "deadline";


    public static final String MESSAGE_USAGE = COMMAND_WORD + "todo : Adds a todo task to the list of tasks."
            + "Parameters: ";

    public static final String MESSAGE_SUCCESS = "New todo task added successfully!";
    public static final String MESSAGE_DUPLICATE_TODO = "This task already exists in the task list";

    // private final String description;
    // private final Deadlines toAdd;
    private final String args; 

    public TaskDeadlineCommand(String args) {
        requireNonNull(args);
        this.args = args;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);


        return new CommandResult(String.format(MESSAGE_SUCCESS, args));
    }

    @Override
    public boolean equals(Object other) {
        // TODO Auto-generated method stub
        return false;
    }
}

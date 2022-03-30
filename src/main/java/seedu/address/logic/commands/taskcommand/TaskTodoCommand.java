package seedu.address.logic.commands.taskcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.TASK;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.tasks.Todos;

public class TaskTodoCommand extends TaskCommands {
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "todo : Adds a todo task to the list of tasks."
            + "Parameters: ";

    public static final String MESSAGE_SUCCESS = "New todo task added successfully!";
    public static final String MESSAGE_DUPLICATE_TODO = "This task already exists in the task list";

    public final Todos toAdd;

    /**
     * Constructor for TaskTodoCommand.
     * @param todo
     */
    public TaskTodoCommand(Todos todo) {
        requireNonNull(todo);
        toAdd = todo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // You should be allowed to add the same todo multiple times
        // because you may need to do the same taks multiple times. eg eat 3 times a day
        // if (model.hasTodo(toAdd)) {
        //     throw new CommandException(MESSAGE_DUPLICATE_TODO);
        // }

        model.addTodo(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), getType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskTodoCommand // instanceof handles nulls
                && toAdd.equals(((TaskTodoCommand) other).toAdd));
    }

    @Override
    public Type getType() {
        return TASK;
    }
}

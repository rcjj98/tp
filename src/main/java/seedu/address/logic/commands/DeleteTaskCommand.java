package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.TASK;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tasks.Task;

public class DeleteTaskCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + " [t] : Deletes the Task identified by the index number used in the displayed task list,\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Task: %1$s";

    private final Index targetIndex;

    /**
     * Constructor for DeletePersonCommand.
     * @param targetIndex
     */
    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        Task taskToDelete = lastShownTaskList.get(targetIndex.getZeroBased());

        try {
            model.deleteTask(taskToDelete);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, taskToDelete), TASK);
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

}

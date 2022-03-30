package seedu.address.logic.commands.taskcommand;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class TaskFindCommand extends TaskCommands {
    public static final String COMMAND_WORD = "find";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

}

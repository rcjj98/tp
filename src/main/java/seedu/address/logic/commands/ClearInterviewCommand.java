package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.Type;
import seedu.address.model.Model;

public class ClearInterviewCommand extends ClearCommand {
    public static final String MESSAGE_SUCCESS = "Interviews have all been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetInterviews();
        return new CommandResult(MESSAGE_SUCCESS, Type.INTERVIEW);
    }
}


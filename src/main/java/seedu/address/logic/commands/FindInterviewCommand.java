package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.interview.InterviewContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.INTERVIEW;

public class FindInterviewCommand extends FindCommand {

    private InterviewContainsKeywordsPredicate predicate;

    public FindInterviewCommand(InterviewContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInterviewList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_INTERVIEWS_LISTED_OVERVIEW,
                model.getFilteredInterviewList().size()), Type.INTERVIEW);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindInterviewCommand)) {
            return false;
        }

        // state check
        FindInterviewCommand e = (FindInterviewCommand) other;
        return predicate.equals(e.predicate);
    }

    @Override
    public Type getType() {
        return INTERVIEW;
    }
}

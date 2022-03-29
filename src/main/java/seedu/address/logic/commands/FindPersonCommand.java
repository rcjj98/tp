package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

public class FindPersonCommand extends FindCommand {

    private PersonContainsKeywordsPredicate predicate;

    public FindPersonCommand(PersonContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()), Type.PERSON);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindPersonCommand)) {
            return false;
        }

        // state check
        FindPersonCommand e = (FindPersonCommand) other;
        return predicate.equals(e.predicate);
    }
}

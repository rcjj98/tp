package seedu.address.logic.parser;

import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

import java.util.ArrayList;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    @Override
    public FindPersonCommand parse(String args) throws ParseException {

        ArgumentMultimap groups = ArgumentTokenizer.tokenize(args, PREFIX_GROUP);

        if (groups.getAllValues(PREFIX_GROUP).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "No groups found"));
        }



        return new FindPersonCommand(new PersonContainsKeywordsPredicate(new ArrayList<>()));
    }
}

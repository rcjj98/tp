package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    public FindCommand parse(String input) throws ParseException {
        String type = ArgumentTokenizer.getType(input.trim());
        String args = input.trim().substring(3);

        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        switch (type) {
        case TYPE_PERSON:
            return new FindPersonCommandParser().parse(args);
        case TYPE_INTERVIEW:
            return new FindInterviewCommandParser().parse(args);
        default:
            return null;
        }
    }

    /*

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GROUP);

        if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
            List<String> terms = argMultimap.getAllValues(PREFIX_GROUP);
            assert !terms.isEmpty() : "No values found in g/flag";
            boolean isEmpty = terms.stream().anyMatch(x -> x.equals(""));

            if (isEmpty) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, "g/ is empty"));
            }

            return new FindCommand(new PersonContainsKeywordsPredicate(terms));
        } else {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, "g/ cannot be found"));
        }
    }
    */

}

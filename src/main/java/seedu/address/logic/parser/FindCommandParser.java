package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GROUP);

        if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
            List<String> terms = argMultimap.getAllValues(PREFIX_GROUP);
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

}

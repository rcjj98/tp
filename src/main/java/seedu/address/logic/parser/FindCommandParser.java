package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.HashMap;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.person.Phone;

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

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_APPLICATION);

        HashMap<Prefix, String> searchDict = new HashMap<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            if (!Name.isValidName(argMultimap.getValue(PREFIX_NAME).get())) {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
            String searchName = argMultimap.getValue(PREFIX_NAME).get();
            searchDict.put(PREFIX_NAME, searchName);
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            if (!Phone.isValidPhone(argMultimap.getValue(PREFIX_PHONE).get())) {
                throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
            }
            String searchPhone = argMultimap.getValue(PREFIX_PHONE).get();
            searchDict.put(PREFIX_PHONE, searchPhone);
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            if (!Email.isValidEmail(argMultimap.getValue(PREFIX_EMAIL).get())) {
                throw new ParseException(Email.MESSAGE_CONSTRAINTS);
            }
            String searchEmail = argMultimap.getValue(PREFIX_EMAIL).get();
            searchDict.put(PREFIX_EMAIL, searchEmail);
        }

        /*
        if (argMultimap.getValue(PREFIX_APPLICATION).isPresent()) {
            List<String> applications = argMultimap.getAllValues(PREFIX_APPLICATION);
            searchDict.put(PREFIX_APPLICATION, String.join(" ", applications));
        }
         */

        return new FindCommand(new PersonContainsKeywordsPredicate(searchDict));
    }

}

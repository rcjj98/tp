package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;

import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

public class FindPersonCommandParser extends FindCommandParser {

    /**
     * Checks for any data that does not follow format.
     *
     * @param groups All groups captured by the g/ flag.
     * @return A new FindPersonCommand Object ready for execution.
     * @throws ParseException An invalid input was found.
     */
    public FindPersonCommand parse(List<String> groups) throws ParseException {

        for (String group : groups) {

            if (areCorrectPrefixesPresent(group, PREFIX_DATE, PREFIX_TIME, PREFIX_GROUP, PREFIX_INFORMATION)) {
                throw new ParseException("[" + group + "] Invalid flags are found.");
            }

            checkInvalidName(group);
            checkInvalidPhone(group);
            checkInvalidEmail(group);
            checkInvalidAddress(group);
            checkInvalidJob(group);
            checkInvalidStage(group);
        }

        return new FindPersonCommand(new PersonContainsKeywordsPredicate(groups));
    }
}


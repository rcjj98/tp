package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAGE;

import java.util.List;

import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.InterviewContainsKeywordsPredicate;

public class FindInterviewCommandParser extends FindCommandParser {

    /**
     * Checks for any data that does not follow format.
     *
     * @param groups All groups captured by the g/ flag.
     * @return A new FindInterViewCommand Object ready for execution.
     * @throws ParseException An invalid input was found.
     */
    public FindInterviewCommand parse(List<String> groups) throws ParseException {

        for (String group : groups) {

            if (areCorrectPrefixesPresent(group, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STAGE,
                    PREFIX_GROUP, PREFIX_INFORMATION)) {
                throw new ParseException("[" + group + "] Invalid flags are found.");
            }

            checkInvalidDates(group);
            checkInvalidTime(group);
            checkInvalidName(group);
            checkInvalidJob(group);
        }

        return new FindInterviewCommand(new InterviewContainsKeywordsPredicate(groups));
    }
}

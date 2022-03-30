package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

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
            ArgumentMultimap fields = ArgumentTokenizer.tokenize(" " + group, PREFIX_DATE,
                    PREFIX_TIME, PREFIX_NAME, PREFIX_JOB);

            checkInvalidDates(fields, group);
            checkInvalidTime(fields, group);
            checkInvalidName(fields, group);
            checkInvalidJob(fields, group);
        }

        return new FindInterviewCommand(new InterviewContainsKeywordsPredicate(groups));
    }
}

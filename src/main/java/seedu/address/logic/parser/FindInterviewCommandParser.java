package seedu.address.logic.parser;

import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.InterviewContainsKeywordsPredicate;

import java.util.List;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

public class FindInterviewCommandParser extends FindCommandParser {

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

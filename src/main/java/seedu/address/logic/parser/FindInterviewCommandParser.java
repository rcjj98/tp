package seedu.address.logic.parser;

import seedu.address.commons.core.Messages;
import seedu.address.model.interview.Date;
import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.InterviewContainsKeywordsPredicate;
import seedu.address.model.interview.Time;
import seedu.address.model.person.Job;
import seedu.address.model.person.Name;


import java.util.List;

import static seedu.address.logic.parser.CliSyntax.*;

public class FindInterviewCommandParser implements Parser<FindInterviewCommand> {

    @Override
    public FindInterviewCommand parse(String args) throws ParseException {

        ArgumentMultimap groupTokens = ArgumentTokenizer.tokenize(" " + args, PREFIX_GROUP);
        List<String> groupAND = groupTokens.getAllValues(PREFIX_GROUP);

        // check for any empty groups
        if (groupAND.stream().anyMatch(String::isEmpty)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, "Empty g/ flags"));
        }

        // check if it is valid token
        for (String group : groupAND) {
            ArgumentMultimap fields = ArgumentTokenizer.tokenize(" " + group, PREFIX_DATE, PREFIX_TIME, PREFIX_NAME, PREFIX_JOB);

            List<String> dates = fields.getAllValues(PREFIX_DATE);
            if (dates.stream().anyMatch(d -> !Date.isValidDate(d))) {
                throw new ParseException("Group: " + group + " contains invalid date\n" + Date.MESSAGE_CONSTRAINTS);
            }

            List<String> times = fields.getAllValues(PREFIX_TIME);
            if (times.stream().anyMatch(t -> !Time.isValidTime(t))) {
                throw new ParseException("Group: " + group + " contains invalid time\n" + Time.MESSAGE_CONSTRAINTS);
            }

            List<String> names = fields.getAllValues(PREFIX_NAME);
            if (names.stream().anyMatch(n -> !Name.isValidName(n))) {
                throw new ParseException("Group: " + group + " contains invalid name\n" + Name.MESSAGE_CONSTRAINTS);
            }

            List<String> jobs = fields.getAllValues(PREFIX_JOB);
            if (jobs.stream().anyMatch(j -> !Job.isValidJob(j))) {
                throw new ParseException("Group: " + group + " contains invalid job\n" + Job.MESSAGE_CONSTRAINTS);
            }
        }

        return new FindInterviewCommand(new InterviewContainsKeywordsPredicate(groupAND));
    }
}

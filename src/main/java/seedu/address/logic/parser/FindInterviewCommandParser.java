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
        checkGroupsValidity(groupAND);

        return new FindInterviewCommand(new InterviewContainsKeywordsPredicate(groupAND));
    }

    private void checkGroupsValidity(List<String> groups) throws ParseException {

        // Check if any flags are empty
        if (groups.stream().anyMatch(String::isEmpty)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, "Empty g/ flags"));
        }

        for (String group : groups) {
            ArgumentMultimap fields = ArgumentTokenizer.tokenize(" " + group, PREFIX_DATE, PREFIX_TIME, PREFIX_NAME, PREFIX_JOB);

            List<String> dates = fields.getAllValues(PREFIX_DATE);
            if (dates.stream().anyMatch(d -> !Date.isValidDate(d.strip()))) {
                throw new ParseException("Group: " + group + " contains invalid date\n" + Date.MESSAGE_CONSTRAINTS);
            }

            List<String> times = fields.getAllValues(PREFIX_TIME);
            if (times.stream().anyMatch(t -> !Time.isValidTime(t.strip()))) {
                throw new ParseException("Group: " + group + " contains invalid time\n" + Time.MESSAGE_CONSTRAINTS);
            }

            List<String> names = fields.getAllValues(PREFIX_NAME);
            if (names.stream().anyMatch(n -> !Name.isValidName(n.strip()))) {
                throw new ParseException("Group: " + group + " contains invalid name\n" + Name.MESSAGE_CONSTRAINTS);
            }

            List<String> jobs = fields.getAllValues(PREFIX_JOB);
            if (jobs.stream().anyMatch(j -> !Job.isValidJob(j.strip()))) {
                throw new ParseException("Group: " + group + " contains invalid job\n" + Job.MESSAGE_CONSTRAINTS);
            }
        }
    }
}

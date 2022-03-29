package seedu.address.logic.parser;

import seedu.address.model.interview.Date;
import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.InterviewContainsKeywordsPredicate;
import seedu.address.model.interview.Time;
import seedu.address.model.person.Job;
import seedu.address.model.person.Name;

import java.util.*;

import static seedu.address.logic.parser.CliSyntax.*;

public class FindInterviewCommandParser implements Parser<FindInterviewCommand> {

    @Override
    public FindInterviewCommand parse(String args) throws ParseException {

        //Map<Prefix, ArrayList<String>> mappings = initialiseMappings();
        Map<Prefix, List<String>> mappings = new HashMap<>();
        ArgumentMultimap groups = ArgumentTokenizer.tokenize(" " + args, PREFIX_DATE, PREFIX_TIME, PREFIX_NAME, PREFIX_JOB);

        List<String> dates = groups.getAllValues(PREFIX_DATE);
        List<String> times = groups.getAllValues(PREFIX_TIME);
        List<String> names = groups.getAllValues(PREFIX_NAME);
        List<String> jobs = groups.getAllValues(PREFIX_JOB);

        for (String d : dates) {
            if (!Date.isValidDate(d)) {
                throw new ParseException("That is not a date.");
            }
        }

        for (String t : times) {
            if (!Time.isValidTime(t)) {
                throw new ParseException("That is not a time.");
            }
        }

        for (String n : names) {
            if (!Name.isValidName(n)) {
                throw new ParseException("That is not a name.");
            }
        }

        for (String j : jobs) {
            if (!Job.isValidJob(j)) {
                throw new ParseException("That is not a job.");
            }
        }

        mappings.put(PREFIX_DATE, dates);
        mappings.put(PREFIX_NAME, names);
        mappings.put(PREFIX_TIME, times);
        mappings.put(PREFIX_JOB, jobs);

        System.out.println(List.of(mappings));
        return new FindInterviewCommand(new InterviewContainsKeywordsPredicate(mappings));
    }
}

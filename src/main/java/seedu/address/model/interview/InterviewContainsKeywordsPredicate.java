package seedu.address.model.interview;

import seedu.address.logic.parser.Prefix;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static seedu.address.logic.parser.CliSyntax.*;

public class InterviewContainsKeywordsPredicate implements Predicate<Interview> {
    private final Map<Prefix, List<String>> keywords;

    public InterviewContainsKeywordsPredicate(Map<Prefix, List<String>> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Interview interview) {

        List<String> dates = keywords.get(PREFIX_DATE);
        List<String> names = keywords.get(PREFIX_NAME);
        List<String> times = keywords.get(PREFIX_TIME);
        List<String> jobs = keywords.get(PREFIX_JOB);

        if (dates.stream().anyMatch(date -> interview.getDate().value.contains(date))) {
            return true;
        }

        if (names.stream().anyMatch(name -> interview.getPerson().getName().fullName.toLowerCase().contains(name.toLowerCase()))) {
            return true;
        }

        if (times.stream().anyMatch(time -> interview.getTime().value.contains(time))) {
            return true;
        }

        if (jobs.stream().anyMatch(job -> interview.getPerson().getJob().jobTitle.toLowerCase().contains(job.toLowerCase()))) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewContainsKeywordsPredicate)) {
            return false;
        }

        // state check
        InterviewContainsKeywordsPredicate e = (InterviewContainsKeywordsPredicate) other;
        return keywords.equals(e.keywords);
        }
    }

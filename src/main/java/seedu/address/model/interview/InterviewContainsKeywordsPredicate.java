package seedu.address.model.interview;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;

import java.util.List;
import java.util.function.Predicate;

import static seedu.address.logic.parser.CliSyntax.*;

public class InterviewContainsKeywordsPredicate implements Predicate<Interview> {
    private List<String> keywords;

    public InterviewContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Interview interview) {

        for (String group : keywords) {
            boolean containsAllGroupTerms = true;
            ArgumentMultimap fields = ArgumentTokenizer.tokenize(" " + group, PREFIX_DATE, PREFIX_TIME, PREFIX_NAME, PREFIX_JOB);

            List<String> dates = fields.getAllValues(PREFIX_DATE);
            if (!dates.isEmpty() && dates.stream().noneMatch(d -> interview.getDate().toString().contains(d.strip()))) {
                containsAllGroupTerms = false;
            }

            List<String> times = fields.getAllValues(PREFIX_TIME);
            if (!times.isEmpty() && times.stream().noneMatch(t-> interview.getTime().toString().contains(t.strip()))) {
                containsAllGroupTerms = false;
            }

            List<String> names = fields.getAllValues(PREFIX_NAME);
            System.out.println(names);
            if (!names.isEmpty() && names.stream().noneMatch(n -> interview.getPerson().getName().contains(n.strip()))) {
                containsAllGroupTerms = false;
            }

            List<String> jobs = fields.getAllValues(PREFIX_JOB);
            if (!jobs.isEmpty() && jobs.stream().noneMatch(j -> interview.getPerson().getJob().contains(j.strip()))) {
                containsAllGroupTerms = false;
            }

            if (containsAllGroupTerms) {
                return true;
            }
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

package seedu.address.model.interview;

import java.util.List;
import java.util.function.Predicate;

public class InterviewContainsKeywordsPredicate implements Predicate<Interview> {
    private final List<String> keywords;

    public InterviewContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Interview interview) {
        return true;
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

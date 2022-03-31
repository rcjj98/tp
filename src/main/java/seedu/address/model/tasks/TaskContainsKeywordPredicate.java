package seedu.address.model.tasks;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TaskContainsKeywordPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        for (String group : keywords) {
            boolean containsAllGroupTerms = true;
            ArgumentMultimap fields = ArgumentTokenizer.tokenize(" " + group, PREFIX_INFORMATION);

            List<String> infos = fields.getAllValues(PREFIX_INFORMATION);

            if (!infos.isEmpty() && !infos.stream().allMatch(t -> task.getInformation().contains(t))) {
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
        if (!(other instanceof TaskContainsKeywordPredicate)) {
            return false;
        }

        // state check
        TaskContainsKeywordPredicate e = (TaskContainsKeywordPredicate) other;
        return keywords.equals(e.keywords);
    }
}

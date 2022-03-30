package seedu.address.model.person;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;

import java.util.List;
import java.util.function.Predicate;

import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        for (String group : keywords) {
            boolean containsAllGroupTerms = true;
            ArgumentMultimap fields = ArgumentTokenizer.tokenize(" " + group, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_JOB, PREFIX_STAGE);

            List<String> names = fields.getAllValues(PREFIX_NAME);
            if (!names.isEmpty() && names.stream().noneMatch(n -> person.getName().contains(n))) {
                containsAllGroupTerms = false;
            }

            List<String> phone = fields.getAllValues(PREFIX_PHONE);
            if (!phone.isEmpty() && phone.stream().noneMatch(p -> person.getPhone().contains(p))) {
                containsAllGroupTerms = false;
            }

            List<String> emails = fields.getAllValues(PREFIX_EMAIL);
            if (!emails.isEmpty() && emails.stream().noneMatch(e -> person.getEmail().contains(e))) {
                containsAllGroupTerms = false;
            }

            List<String> addresses = fields.getAllValues(PREFIX_ADDRESS);
            if (!addresses.isEmpty() && addresses.stream().noneMatch(a -> person.getAddress().contains(a))) {
                containsAllGroupTerms = false;
            }

            List<String> jobs = fields.getAllValues(PREFIX_JOB);
            if (!jobs.isEmpty() && jobs.stream().noneMatch(j -> person.getJob().contains(j))) {
                containsAllGroupTerms = false;
            }

            List<String> stages = fields.getAllValues(PREFIX_STAGE);
            if (!stages.isEmpty() && stages.stream().noneMatch(s -> person.getStage().contains(s))) {
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
        if (!(other instanceof PersonContainsKeywordsPredicate)) {
            return false;
        }

        // state check
        PersonContainsKeywordsPredicate e = (PersonContainsKeywordsPredicate) other;
        return keywords.equals(e.keywords);
    }
}

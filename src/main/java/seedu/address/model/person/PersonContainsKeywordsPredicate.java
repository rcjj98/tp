package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

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
        requireNonNull(person);

        for (String group : keywords) {
            boolean containsGroup = true;
            String[] terms = group.strip().split("\\s+");
            assert terms.length > 0 : "No values in group";

            for (String term : terms) {
                if (!person.contains(term.toLowerCase())) {
                    containsGroup = false;
                }
            }

            if (containsGroup) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }

}

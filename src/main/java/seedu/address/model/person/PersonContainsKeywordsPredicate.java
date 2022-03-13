package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.HashMap;
import java.util.function.Predicate;

import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final HashMap<Prefix, String> keywords;

    public PersonContainsKeywordsPredicate(HashMap<Prefix, String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {

        if (keywords.containsKey(PREFIX_NAME)) {
            Name searchName = new Name(keywords.get(PREFIX_NAME));

            if (!person.getName().contains(searchName)) {
                return false;
            }
        }

        if (keywords.containsKey(PREFIX_PHONE)) {
            Phone searchPhone = new Phone(keywords.get(PREFIX_PHONE));

            if (!searchPhone.equals(person.getPhone())) {
                return false;
            }
        }

        if (keywords.containsKey(PREFIX_EMAIL)) {
            Email searchEmail = new Email(keywords.get(PREFIX_EMAIL));

            if (!searchEmail.equals(person.getEmail())) {
                return false;
            }
        }

        if (keywords.containsKey(PREFIX_APPLICATION)) {
            System.out.println("<placeholder>");
        }

        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }

}

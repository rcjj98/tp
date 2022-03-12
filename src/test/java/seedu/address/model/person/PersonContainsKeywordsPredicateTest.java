package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {

        HashMap<Prefix, String> first = new HashMap<>();
        HashMap<Prefix, String> second = new HashMap<>();
        first.put(PREFIX_NAME, "first");
        second.put(PREFIX_NAME, "second");

        PersonContainsKeywordsPredicate firstPredicate = new PersonContainsKeywordsPredicate(first);
        PersonContainsKeywordsPredicate secondPredicate = new PersonContainsKeywordsPredicate(second);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy = new PersonContainsKeywordsPredicate(first);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        HashMap<Prefix, String> name = new HashMap<>();

        // One keyword
        name.put(PREFIX_NAME, "Alice");
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(name);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        name.put(PREFIX_NAME, "Alice Bob");
        predicate = new PersonContainsKeywordsPredicate(name);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        name.put(PREFIX_NAME, "Bob Carol");
        predicate = new PersonContainsKeywordsPredicate(name);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        name.put(PREFIX_NAME, "aLIce bOB");
        predicate = new PersonContainsKeywordsPredicate(name);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {

        HashMap<Prefix, String> name = new HashMap<>();

        /* already checks for zero keywords
        // Zero keywords
        name.put(PREFIX_NAME, "");
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(name);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));
        */

        // Non-matching keyword
        name.put(PREFIX_NAME, "Carol");
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(name);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }
}

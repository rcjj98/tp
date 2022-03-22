package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {

        List<String> first = new ArrayList<>();
        first.add("first");

        List<String> second = new ArrayList<>();
        second.add("second");

        PersonContainsKeywordsPredicate firstPredicate = new PersonContainsKeywordsPredicate(first);
        PersonContainsKeywordsPredicate secondPredicate = new PersonContainsKeywordsPredicate(second);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);
        assertEquals(secondPredicate, secondPredicate);

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy = new PersonContainsKeywordsPredicate(first);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        List<String> keywords = new ArrayList<>();

        // One keyword
        keywords.add("alice");
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(keywords);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        keywords.add("bob");
        predicate = new PersonContainsKeywordsPredicate(keywords);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        keywords.add("john");
        predicate = new PersonContainsKeywordsPredicate(keywords);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        keywords.clear();
        keywords.add("aLIce bOB");
        predicate = new PersonContainsKeywordsPredicate(keywords);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {

        List<String> keywords = new ArrayList<>();

        /* already checks for zero keywords
        // Zero keywords
        name.put(PREFIX_NAME, "");
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(name);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));
        */

        // Non-matching keyword
        keywords.add("Carol");
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(keywords);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }
}

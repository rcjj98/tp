package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NO_TYPE_GIVEN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.model.interview.InterviewContainsKeywordsPredicate;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", MESSAGE_NO_TYPE_GIVEN);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<String> groups = new ArrayList<>();
        groups.add("n/test1 j/test2");
        groups.add("n/test3 j/test4");

        // finding interview
        FindInterviewCommand findInterview = new FindInterviewCommand(new InterviewContainsKeywordsPredicate(groups));
        assertParseSuccess(parser, " [i] g/n/test1 j/test2 g/n/test3 j/test4", findInterview);

        // finding persons
        FindPersonCommand findPerson = new FindPersonCommand(new PersonContainsKeywordsPredicate(groups));
        assertParseSuccess(parser, " [p] g/n/test1 j/test2 g/n/test3 j/test4", findPerson);
    }
}

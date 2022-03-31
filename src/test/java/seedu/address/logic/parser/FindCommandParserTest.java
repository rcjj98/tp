package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NO_TYPE_GIVEN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.model.interview.InterviewContainsKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindPersonCommandParser person = new FindPersonCommandParser();
    private FindInterviewCommandParser interview = new FindInterviewCommandParser();

    @Test
    public void parse_throwsParseException() {

        // check for empty string
        assertParseFailure(person, "", MESSAGE_NO_TYPE_GIVEN);

        // check for no g/ flags
        assertParseFailure(person, " [p]",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        assertParseFailure(interview, " [i]",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInterviewCommand.MESSAGE_USAGE));

        // check for empty g/ flags
        assertParseFailure(person, " [p] g/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        assertParseFailure(interview, " [i] g/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInterviewCommand.MESSAGE_USAGE));
        assertParseFailure(person, " [p] g/\n",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        assertParseFailure(interview, " [i] g/\t",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInterviewCommand.MESSAGE_USAGE));
        assertParseFailure(person, " [p] g/n/test g/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        assertParseFailure(interview, " [i] g/n/test g/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInterviewCommand.MESSAGE_USAGE));
        assertParseFailure(person, " [p] g/n/test g/ g/j/software",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        assertParseFailure(interview, " [i] g/n/test g/ g/j/software",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInterviewCommand.MESSAGE_USAGE));

        // check for invalid input
        assertParseFailure(person, " [p] g/n/<>",
                String.format("Group n/<>: contains invalid name\n" + Name.MESSAGE_CONSTRAINTS));
        assertParseFailure(interview, " [i] g/n/<>",
                String.format("Group n/<>: contains invalid name\n" + Name.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<String> groups = new ArrayList<>();
        groups.add("n/test1 j/test2");
        groups.add("n/test3 j/test4");

        // finding interview
        FindInterviewCommand findInterview = new FindInterviewCommand(new InterviewContainsKeywordsPredicate(groups));
        assertParseSuccess(interview, " [i] g/n/test1 j/test2 g/n/test3 j/test4", findInterview);

        // finding persons
        FindPersonCommand findPerson = new FindPersonCommand(new PersonContainsKeywordsPredicate(groups));
        assertParseSuccess(person, " [p] g/n/test1 j/test2 g/n/test3 j/test4", findPerson);
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces

        HashMap<Prefix, String> terms = new HashMap<>();
        terms.put(PREFIX_NAME, "Alice");
        terms.put(PREFIX_PHONE, "12345678");
        terms.put(PREFIX_EMAIL, "alice@example.com");

        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate(terms));
        assertParseSuccess(parser, "find n/Alice p/12345678 e/alice@example.com", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "find n/Alice\n\t p/12345678 \t e/alice@example.com", expectedFindCommand);
    }

}

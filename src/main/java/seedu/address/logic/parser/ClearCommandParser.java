package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.TYPE_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ClearInterviewCommand;
import seedu.address.logic.commands.ClearPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ClearCommandParser {
    /**
     * Parse ClearCommand
     */
    public ClearCommand parse(String args) throws ParseException {
        String type = ArgumentTokenizer.getType(args.trim());
        String removedType = args.trim().substring(3);

        if (removedType.length() != 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        } else if (type.equals(TYPE_PERSON)) {
            return new ClearPersonCommand();
        } else if (type.equals(TYPE_INTERVIEW)) {
            return new ClearInterviewCommand();
        } else {
            return null;
        }
    }
}


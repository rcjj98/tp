package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.TYPE_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListInterviewCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parse ListCommand
     */
    public ListCommand parse(String args) throws ParseException {
        String type = ArgumentTokenizer.getType(args.trim());
        String removedType = args.trim().substring(3);

        if (removedType.length() != 0) {
            throw new ParseException("Invalid list command format");
        } else if (type.equals(TYPE_PERSON)) {
            return new ListPersonCommand();
        } else if (type.equals(TYPE_INTERVIEW)) {
            return new ListInterviewCommand();
        } else {
            return null;
        }
    }
}

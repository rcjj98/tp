package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.TYPE_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        String type = ArgumentTokenizer.getType(args.trim());
        String removedType = args.trim().substring(3);

        if (type.equals(TYPE_PERSON)) {
            return new DeletePersonCommandParser().parse(removedType);
        } else if (type.equals(TYPE_INTERVIEW)) {
            return new DeleteInterviewCommandParser().parse(removedType);
        } else {
            return null;
        }
    }
}

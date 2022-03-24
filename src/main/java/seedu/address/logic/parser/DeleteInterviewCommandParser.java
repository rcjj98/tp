package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteInterviewCommandParser implements Parser<DeleteInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteInterviewCommand
     * and returns a DeleteInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteInterviewCommand parse(String args) throws ParseException {
        // trim method to delete the whitespace
        boolean isStringInt = isStringInt(args.trim());

        if (!isStringInt) {
            // args is not an Integer, will send to method in DeleteInterviewCommand that deletes via
            // name.
            // Set index value for the targetIndex of DeleteInterviewCommand to null
            return new DeleteInterviewCommand(args, null);
        } else {
            // args is an integer value
            try {
                Index index = ParserUtil.parseIndex(args);
                return new DeleteInterviewCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInterviewCommand.MESSAGE_USAGE), pe);
            }
        }
    }
    /**
     * Checks if the input is a string or an integer.
     * @param args
     * @return boolean is string argument an integer.
     */
    private boolean isStringInt(String args) {
        try {
            Integer.parseInt(args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}





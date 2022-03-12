package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
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

        /**
         * The way i think this will work is to check if args is an integer.
         * Convert the input string to an integer, and then see if integer, else, move
         * on with the else block.
         */
        boolean isStringInt = isStringInt(args);

        if (!isStringInt) {
            // args is not an Integer, will send to method in DeleteCommand that deletes via
            // name.
            // Instantiate an index value for the targetIndex of DeleteCommand, as it
            // is a final attribute
            Index index = ParserUtil.parseIndex("0");
            return new DeleteCommand(args, index);
        } else {
            // args is an integer value
            try {
                Index index = ParserUtil.parseIndex(args);
                return new DeleteCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
            }
        }
    }

    /**
     * Checks if the input is a string or an integer.
     * 
     * @param args
     * @return boolean
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

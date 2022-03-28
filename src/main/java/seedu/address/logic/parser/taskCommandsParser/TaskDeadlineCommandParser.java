package seedu.address.logic.parser.taskCommandsParser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BY;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;
import seedu.address.logic.commands.taskCommands.TaskDeadlineCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tasks.By;
import seedu.address.model.tasks.Deadlines;
import seedu.address.model.tasks.Description;

public class TaskDeadlineCommandParser implements Parser<TaskDeadlineCommand>{

    // Example of deadline commmand : {task deadline des/(description) by/(by)}
    public TaskDeadlineCommand parse(String args) throws ParseException {
        // incoming args will not have the "task" tag
        // deadline des/(description) by(by)
        // final int removeCharacters = 13;
        // String removedType = args.trim().substring(removeCharacters);

        // need to break down the inputs into the relevant parts,
        // and then return a new TaskDeadlineCommand 

        // these are the inputs to the deadline command 
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_BY);

        if (!arePrefixesPresent(argMultiMap, PREFIX_DESCRIPTION, PREFIX_BY) || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskDeadlineCommand.MESSAGE_USAGE));
        } 
        
        // implement the Description class, and paeserUtil method for parseDescription
        Description description = ParserUtil.parseDescription(argMultiMap.getValue(PREFIX_DESCRIPTION).get());
        // Is there a way to just use the by as a String value, and not create a whole By class for this
        By by = ParserUtil.parseBy(argMultiMap.getValue(PREFIX_BY).get());
        Deadlines deadline = new Deadlines(description, by);

        return new TaskDeadlineCommand(deadline);
    }  

    
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}


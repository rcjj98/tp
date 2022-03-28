package seedu.address.logic.parser.taskCommandsParser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;
import seedu.address.logic.commands.taskCommands.TaskEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tasks.Events;

public class TaskEventCommandParser {

    // 	{task event des/(description) at/(at)}
public TaskEventCommand parse(String args) throws ParseException {
    // String type = ArgumentTokenizer.getType(args.trim());
    // removeCharacters will remove the first 13 characters
    // final int removeCharacters = 9;
    // String removedType = args.trim().substring(removeCharacters);

    ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION);

    if (!arePrefixesPresent(argMultiMap, PREFIX_DESCRIPTION, PREFIX_BY) || !argMultiMap.getPreamble().isEmpty()) {
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskEventCommand.MESSAGE_USAGE));
    } 

    // its guaranteed to be a deadline type, so immediately call the TaskDeadlineCommand class
    // create the Description type first, then the Todo, and the call to TodoCommand
    Description description = ParserUtil.parseDescription(argMultiMap.getValue(PREFIX_DESCRIPTION).get());
    At at = ParserUtil.parseAt(argMultiMap.getValue(PREFIX_AT).get());
    
    Events event = new Events(description, at);

    return new TaskEventCommand(event);
}

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

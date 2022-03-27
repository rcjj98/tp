package seedu.address.logic.parser.taskCommandsParser;

import seedu.address.logic.commands.taskCommands.TaskTodoCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * This class will manage the arguments of the todo command, and will go on to call TaskTodoCommand
 */
public class TaskTodoCommandParser implements Parser<TaskTodoCommand> {

    // public DeleteCommand parse(String args) throws ParseException {
    //     String type = ArgumentTokenizer.getType(args.trim());
    //     String removedType = args.trim().substring(3);

    //     if (type.equals(TYPE_PERSON)) {
    //         System.out.println(removedType);
    //         return new DeletePersonCommandParser().parse(removedType);
    //     } else if (type.equals(TYPE_INTERVIEW)) {
    //         return new DeleteInterviewCommandParser().parse(removedType);
    //     } else {
    //         return null;
    //     }
    // }

    // an example of the input would be {todo des/(description)}
    public TaskTodoCommand parse(String args) throws ParseException {
        // String type = ArgumentTokenizer.getType(args.trim());
        // removeCharacters will remove the first 13 characters
        final int removeCharacters = 13;
        String removedType = args.trim().substring(removeCharacters);
    
        // its guaranteed to be a deadline type, so immediately call the TaskDeadlineCommand class
        return new TaskTodoCommand(removedType);
    }

}

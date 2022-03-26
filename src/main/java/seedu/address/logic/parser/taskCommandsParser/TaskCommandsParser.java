package seedu.address.logic.parser.taskCommandsParser;

import seedu.address.logic.commands.taskCommands.TaskCommands;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class TaskCommandsParser implements Parser<TaskCommands> {

    //  /**
    //  * Parses the given {@code String} of arguments in the context of the AddCommand
    //  * and returns an AddCommand object for execution.
    //  * @throws ParseException if the user input does not conform the expected format
    //  */
    // public AddCommand parse(String args) throws ParseException {

    //     String type = ArgumentTokenizer.getType(args.trim());
    //     String removedType = args.trim().substring(3);

    //     if (type.equals(TYPE_PERSON)) {
    //         return new AddPersonCommandParser().parse(removedType);
    //     } else if (type.equals(TYPE_INTERVIEW)) {
    //         return new AddInterviewCommandParser().parse(removedType);
    //     } else {
    //         return null;
    //     }
    // }

    /**
     * Parses input arguments and creates a new TaskCommand object.
     * 
     * ORRR this could just be another filter for you to disperse to other commands.
     * You have ADD, DEADLINE, DELETE, EVENT, FIND, TODO, LIST commands.
     * Why not convert this class to handle all the different commands of taskCommand.
     * Abstract the methods out here, and then create other classes to help handle such commands.
     */
    @Override
    public TaskCommands parse(String args) throws ParseException {
        String type = ArgumentTokenizer.getType(args.trim());
        
        // what does removedType do? what does this represent?
        String removedType = args.trim().substring(3);


    }
    
}

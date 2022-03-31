// package seedu.address.logic.parser.taskcommandsparser;

// import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
// import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

// import seedu.address.logic.commands.HelpCommand;
// import seedu.address.logic.commands.taskcommand.TaskCommands;
// import seedu.address.logic.commands.taskcommand.TaskTodoCommand;
// import seedu.address.logic.parser.Parser;
// import seedu.address.logic.parser.exceptions.ParseException;

// public class TaskCommandsParser implements Parser<TaskCommands> {

//     // /**
//     // * Parses the given {@code String} of arguments in the context of the
//     // AddCommand
//     // * and returns an AddCommand object for execution.
//     // * @throws ParseException if the user input does not conform the expected
//     // format
//     // */
//     // public AddCommand parse(String args) throws ParseException {

//     // String type = ArgumentTokenizer.getType(args.trim());
//     // String removedType = args.trim().substring(3);

//     // if (type.equals(TYPE_PERSON)) {
//     // return new AddPersonCommandParser().parse(removedType);
//     // } else if (type.equals(TYPE_INTERVIEW)) {
//     // return new AddInterviewCommandParser().parse(removedType);
//     // } else {
//     // return null;
//     // }
//     // }

//     private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

//     /**
//      * Parses input arguments and creates a new TaskCommand object.
//      */
//     @Override
//     public TaskCommands parse(String args) throws ParseException {
//         // String type = ArgumentTokenizer.getType(args.trim());

//         // input examples
//         // 1. task event des/(description) at/(at)
//         // 2. task deadline des/(description) by(by)
//         // 3. task todo des/(description)

//         // so all inputs have task infront, which will be deleted before coming here,
//         // will be left with event deadline todo onwards,
//         // then new we need to replicate what happened in the previous parser

//         // String removedType = args.trim().substring(3);

//         final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
//         if (!matcher.matches()) {
//             throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
//         }

//         // todo, deadline or event
//         final String commandWord = matcher.group("commandWord");
//         // everything after the commandWord
//         final String arguments = matcher.group("arguments");
//         switch (commandWord) {

//         case TaskTodoCommand.COMMAND_WORD:
//             return new TaskTodoCommandParser().parse(arguments);

//         default:
//             throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
//         }
//     }
// }

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.TYPE_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;

import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.Date;
import seedu.address.model.interview.Time;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Job;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Stage;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the initial input taken from the application
     *
     * @param input The input passed from the application.
     * @return A FindPersonCommand or FindInterviewCommand ready for execution or an error stating that no type is found
     * @throws ParseException A search term has an invalid format.
     */
    public FindCommand parse(String input) throws ParseException {
        String type = ArgumentTokenizer.getType(input.trim());
        String args = input.trim().substring(3);

        // splits tokens by its groups
        ArgumentMultimap groupTokens = ArgumentTokenizer.tokenize(" " + args, PREFIX_GROUP);
        List<String> allGroups = groupTokens.getAllValues(PREFIX_GROUP);

        if (type.equals(TYPE_PERSON)) {
            checkIfEmpty(allGroups, args, FindPersonCommand.MESSAGE_USAGE);
            return new FindPersonCommandParser().parse(allGroups);
        } else if (type.equals(TYPE_INTERVIEW)) {
            checkIfEmpty(allGroups, args, FindInterviewCommand.MESSAGE_USAGE);
            return new FindInterviewCommandParser().parse(allGroups);
        } else {
            return null;
        }
    }

    // ------ Checks input for any invalid data ------ //

    private void checkIfEmpty(List<String> allGroups, String args, String msg) throws ParseException {
        if (args.isEmpty() || allGroups.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, msg));
        }
    }

    public static void checkInvalidDates(ArgumentMultimap fields, String group) throws ParseException {
        List<String> dates = fields.getAllValues(PREFIX_DATE);
        if (dates.stream().anyMatch(d -> !Date.isValidDate(d.strip()))) {
            throw new ParseException("Group: " + group + " contains invalid date\n" + Date.MESSAGE_CONSTRAINTS);
        }
    }

    public static void checkInvalidTime(ArgumentMultimap fields, String group) throws ParseException {
        List<String> times = fields.getAllValues(PREFIX_TIME);
        if (times.stream().anyMatch(t -> !Time.isValidTime(t.strip()))) {
            throw new ParseException("Group: " + group + " contains invalid time\n" + Time.MESSAGE_CONSTRAINTS);
        }
    }

    public static void checkInvalidName(ArgumentMultimap fields, String group) throws ParseException {
        List<String> names = fields.getAllValues(PREFIX_NAME);
        if (names.stream().anyMatch(n -> !Name.isValidName(n.strip()))) {
            throw new ParseException("Group: " + group + " contains invalid name\n" + Name.MESSAGE_CONSTRAINTS);
        }
    }

    public static void checkInvalidJob(ArgumentMultimap fields, String group) throws ParseException {
        List<String> jobs = fields.getAllValues(PREFIX_JOB);
        if (jobs.stream().anyMatch(j -> !Job.isValidJob(j.strip()))) {
            throw new ParseException("Group: " + group + " contains invalid job\n" + Job.MESSAGE_CONSTRAINTS);
        }
    }

    public static void checkInvalidPhone(ArgumentMultimap fields, String group) throws ParseException {
        List<String> phoneNumbers = fields.getAllValues(PREFIX_PHONE);
        if (phoneNumbers.stream().anyMatch(p -> !Phone.isValidPhone(p))) {
            throw new ParseException("Group: " + group + " contains invalid phone number\n"
                    + Phone.MESSAGE_CONSTRAINTS);
        }
    }
    public static void checkInvalidEmail(ArgumentMultimap fields, String group) throws ParseException {
        List<String> emails = fields.getAllValues(PREFIX_EMAIL);
        if (emails.stream().anyMatch(e -> !Email.isValidEmail(e))) {
            throw new ParseException("Group: " + group + " contains invalid email\n" + Email.MESSAGE_CONSTRAINTS);
        }
    }

    public static void checkInvalidAddress(ArgumentMultimap fields, String group) throws ParseException {
        List<String> addresses = fields.getAllValues(PREFIX_ADDRESS);
        if (addresses.stream().anyMatch(a -> !Address.isValidAddress(a))) {
            throw new ParseException("Group: " + group + " contains invalid address\n" + Address.MESSAGE_CONSTRAINTS);
        }
    }

    public static void checkInvalidStage(ArgumentMultimap fields, String group) throws ParseException {
        List<String> stages = fields.getAllValues(PREFIX_STAGE);
        if (stages.stream().anyMatch(s -> !Stage.isValidStage(s))) {
            throw new ParseException("Group: " + group + " contains invalid stage\n" + Stage.MESSAGE_CONSTRAINTS);
        }
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.TYPE_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;
import static seedu.address.logic.parser.CliSyntax.TYPE_TASK;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.Date;
import seedu.address.model.interview.Time;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Job;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Stage;
import seedu.address.model.tasks.Information;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static String padding = " ";
    private static Logger logger = LogsCenter.getLogger(FindCommandParser.class);

    /**
     * Parses the initial input taken from the application
     *
     * @param input The input passed from the application.
     * @return A Find(Person/Interview/Tasks)Command object ready for execution or an error when no type is found
     * @throws ParseException A search term has an invalid format.
     */
    public FindCommand parse(String input) throws ParseException {
        logger.info("Parsing Input for Find -> \"" + input + "\"");

        String type = ArgumentTokenizer.getType(input.trim());
        String args = input.trim().substring(3);

        // splits tokens by its groups
        ArgumentMultimap groupTokens = ArgumentTokenizer.tokenize(args, PREFIX_GROUP);
        List<String> allGroups = groupTokens.getAllValues(PREFIX_GROUP);


        switch (type) {
        case TYPE_PERSON:
            checkValidGroups(allGroups, args, FindPersonCommand.MESSAGE_USAGE);
            return new FindPersonCommandParser().parse(allGroups);
        case TYPE_INTERVIEW:
            checkValidGroups(allGroups, args, FindInterviewCommand.MESSAGE_USAGE);
            return new FindInterviewCommandParser().parse(allGroups);
        case TYPE_TASK:
            checkValidGroups(allGroups, args, FindTaskCommand.MESSAGE_USAGE);
            return new FindTaskCommandParser().parse(allGroups);
        default:
            return null;
        }
    }

    // ------ Checks input for any invalid data ------ //

    private void checkValidGroups(List<String> allGroups, String args, String msg) throws ParseException {
        boolean isCompletelyEmpty = args.isEmpty();
        boolean hasNoSpaceBetweenInitialFlags = allGroups.isEmpty();
        boolean hasNoKeywords = allGroups.stream().anyMatch(grp -> grp.strip().equals(""));
        boolean stillHasGroupFlag = allGroups.stream()
                .anyMatch(grp -> grp.strip().startsWith(String.valueOf(PREFIX_GROUP)));

        if (isCompletelyEmpty || hasNoKeywords || hasNoSpaceBetweenInitialFlags || stillHasGroupFlag) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, msg));
        }

        // check for cases where there are no flags after the 'g/' flag
        for (String group : allGroups) {
            String firstArg = group.strip().split("\\s+")[0];

            if (!areCorrectPrefixesPresent(padding + firstArg, PREFIX_NAME, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_JOB, PREFIX_STAGE, PREFIX_DATE, PREFIX_TIME,
                    PREFIX_GROUP, PREFIX_INFORMATION)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "No flags found in group"));
            }
        }
    }

    /**
     * Checks the current group for any invalid dates.
     *
     * @param group Current group of tokens.
     * @throws ParseException A date was found to have invalid format.
     */
    protected static void checkInvalidDates(String group) throws ParseException {
        List<String> dates = ArgumentTokenizer.tokenize(
                padding + group, PREFIX_DATE).getAllValues(PREFIX_DATE);

        if (dates.stream().anyMatch(d -> !Date.isValidDate(d.strip()))) {
            throw new ParseException("[" + group + "] contains invalid date\n" + Date.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid time.
     *
     * @param group Current group of tokens.
     * @throws ParseException A time was found to have invalid format.
     */
    protected static void checkInvalidTime(String group) throws ParseException {
        List<String> times = ArgumentTokenizer.tokenize(
                padding + group, PREFIX_TIME).getAllValues(PREFIX_TIME);

        if (times.stream().anyMatch(t -> !Time.isValidTime(t.strip()))) {
            throw new ParseException("[" + group + "] contains invalid time\n" + Time.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid names.
     *
     * @param group Current group of tokens.
     * @throws ParseException A name was found to have invalid format.
     */
    protected static void checkInvalidName(String group) throws ParseException {
        List<String> names = ArgumentTokenizer.tokenize(
                padding + group, PREFIX_NAME).getAllValues(PREFIX_NAME);

        if (names.stream().anyMatch(n -> !Name.isValidName(n.strip()))) {
            throw new ParseException("[" + group + "] contains invalid name\n" + Name.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid jobs.
     *
     * @param group Current group of tokens.
     * @throws ParseException A job was found to have invalid format.
     */
    protected static void checkInvalidJob(String group) throws ParseException {
        List<String> jobs = ArgumentTokenizer.tokenize(
                padding + group, PREFIX_JOB).getAllValues(PREFIX_JOB);

        if (jobs.stream().anyMatch(j -> !Job.isValidJob(j.strip()))) {
            throw new ParseException("[" + group + "] contains invalid job\n" + Job.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid phone numbers.
     *
     * @param group Current group of tokens.
     * @throws ParseException A phone number was found to have invalid format.
     */
    protected static void checkInvalidPhone(String group) throws ParseException {
        List<String> phones = ArgumentTokenizer.tokenize(
                padding + group, PREFIX_PHONE).getAllValues(PREFIX_PHONE);

        if (phones.stream().anyMatch(p -> !Phone.isValidPhone(p))) {
            throw new ParseException("[" + group + "] contains invalid phone number\n"
                    + Phone.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid emails.
     *
     * @param group Current group of tokens.
     * @throws ParseException An email was found to have invalid format.
     */
    protected static void checkInvalidEmail(String group) throws ParseException {
        List<String> emails = ArgumentTokenizer.tokenize(
                padding + group, PREFIX_EMAIL).getAllValues(PREFIX_EMAIL);

        if (emails.stream().anyMatch(e -> !Email.isValidEmail(e))) {
            throw new ParseException("[" + group + "] contains invalid email\n" + Email.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid addresses.
     *
     * @param group Current group of tokens.
     * @throws ParseException An address was found to have invalid format.
     */
    protected static void checkInvalidAddress(String group) throws ParseException {
        List<String> addresses = ArgumentTokenizer.tokenize(
                padding + group, PREFIX_ADDRESS).getAllValues(PREFIX_ADDRESS);

        if (addresses.stream().anyMatch(a -> !Address.isValidAddress(a))) {
            throw new ParseException("[" + group + "] contains invalid address\n" + Address.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid stages.
     *
     * @param group Current group of tokens.
     * @throws ParseException A stage was found to have invalid format.
     */
    protected static void checkInvalidStage(String group) throws ParseException {
        List<String> stages = ArgumentTokenizer.tokenize(
                padding + group, PREFIX_STAGE).getAllValues(PREFIX_STAGE);

        if (stages.stream().anyMatch(s -> !Stage.isValidStage(s))) {
            throw new ParseException("[" + group + "] contains invalid stage\n" + Stage.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid information.
     *
     * @param group Current group of tokens.
     * @throws ParseException A stage was found to have invalid format.
     */
    protected static void checkInvalidInformation(String group) throws ParseException {
        List<String> infos = ArgumentTokenizer.tokenize(
                padding + group, PREFIX_INFORMATION).getAllValues(PREFIX_INFORMATION);

        if (infos.stream().anyMatch(i -> !Information.isValidInformation(i))) {
            throw new ParseException(
                    "[" + group + "] contains invalid information\n" + Information.MESSAGE_CONSTRAINTS);
        }
    }

    protected static boolean areCorrectPrefixesPresent(String group, Prefix... prefixes) {
        ArgumentMultimap allFlags = ArgumentTokenizer.tokenize(padding + group, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_JOB, PREFIX_STAGE, PREFIX_DATE, PREFIX_TIME,
                PREFIX_GROUP, PREFIX_INFORMATION);

        return Stream.of(prefixes).anyMatch(prefix -> allFlags.getValue(prefix).isPresent());
    }
}

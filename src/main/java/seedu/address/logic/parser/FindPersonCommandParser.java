package seedu.address.logic.parser;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.Date;
import seedu.address.model.person.*;

import java.util.ArrayList;
import java.util.List;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    @Override
    public FindPersonCommand parse(String args) throws ParseException {

        ArgumentMultimap groupTokens = ArgumentTokenizer.tokenize(" " + args, PREFIX_GROUP);
        List<String> groupAND = groupTokens.getAllValues(PREFIX_GROUP);
        checkGroupsValidity(groupAND);

        return new FindPersonCommand(new PersonContainsKeywordsPredicate(groupAND));
    }

    private void checkGroupsValidity(List<String> groups) throws ParseException {

        // Check if any flags are empty
        if (groups.stream().anyMatch(String::isEmpty)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Empty g/ flags"));
        }

        for (String group : groups) {
            ArgumentMultimap fields = ArgumentTokenizer.tokenize(" " + group, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_JOB, PREFIX_STAGE);

            List<String> names = fields.getAllValues(PREFIX_NAME);
            if (names.stream().anyMatch(n -> !Name.isValidName(n))) {
                throw new ParseException("Group: " + group + " contains invalid name\n" + Name.MESSAGE_CONSTRAINTS);
            }

            List<String> phoneNumbers = fields.getAllValues(PREFIX_PHONE);
            if (phoneNumbers.stream().anyMatch(p -> !Phone.isValidPhone(p))) {
                throw new ParseException("Group: " + group + " contains invalid phone numbers\n" + Phone.MESSAGE_CONSTRAINTS);
            }

            List<String> emails = fields.getAllValues(PREFIX_EMAIL);
            if (emails.stream().anyMatch(e -> !Email.isValidEmail(e))) {
                throw new ParseException("Group: " + group + " contains invalid email\n" + Email.MESSAGE_CONSTRAINTS);
            }

            List<String> addresses = fields.getAllValues(PREFIX_ADDRESS);
            if (addresses.stream().anyMatch(a -> !Address.isValidAddress(a))) {
                throw new ParseException("Group: " + group + " contains invalid address\n" + Address.MESSAGE_CONSTRAINTS);
            }

            List<String> jobs = fields.getAllValues(PREFIX_JOB);
            if (jobs.stream().anyMatch(j -> !Job.isValidJob(j))) {
                throw new ParseException("Group: " + group + " contains invalid job\n" + Job.MESSAGE_CONSTRAINTS);
            }

            List<String> stages = fields.getAllValues(PREFIX_STAGE);
            System.out.println(stages);
            if (stages.stream().anyMatch(s -> !Stage.isValidStage(s))) {
                throw new ParseException("Group: " + group + " contains invalid stage\n" + Stage.MESSAGE_CONSTRAINTS);
            }
        }
    }
}

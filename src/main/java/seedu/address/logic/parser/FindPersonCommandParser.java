package seedu.address.logic.parser;

import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

import java.util.List;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAGE;

public class FindPersonCommandParser extends FindCommandParser {

    public FindPersonCommand parse(List<String> groups) throws ParseException {

        for (String group : groups) {
            ArgumentMultimap fields = ArgumentTokenizer.tokenize(" " + group, PREFIX_NAME,
                    PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_JOB, PREFIX_STAGE);

            checkInvalidName(fields, group);
            checkInvalidJob(fields, group);
            checkInvalidPhone(fields, group);
            checkInvalidEmail(fields, group);
            checkInvalidAddress(fields, group);
            checkInvalidStage(fields, group);
        }

        return new FindPersonCommand(new PersonContainsKeywordsPredicate(groups));
    }
}


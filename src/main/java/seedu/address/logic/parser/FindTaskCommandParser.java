package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;

import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tasks.TaskContainsKeywordPredicate;

public class FindTaskCommandParser extends FindCommandParser {

    /**
     * Checks for any data that does not follow format.
     *
     * @param groups All groups captured by the g/ flag.
     * @return A new FindTaskCommand Object ready for execution.
     * @throws ParseException An invalid input was found.
     */
    public FindTaskCommand parse(List<String> groups) throws ParseException {
        for (String group : groups) {
            ArgumentMultimap fields = ArgumentTokenizer.tokenize(" " + group, PREFIX_HEADER, PREFIX_INFORMATION,
                    PREFIX_DATE, PREFIX_TIME);

            checkInvalidHeader(fields, group);
            checkInvalidInformation(fields, group);
            checkInvalidDates(fields, group);
            checkInvalidTime(fields, group);
        }
        return new FindTaskCommand(new TaskContainsKeywordPredicate(groups));
    }
}


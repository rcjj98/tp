package seedu.address.logic.parser;

import java.util.List;

import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tasks.TaskContainsKeywordPredicate;

public class FindTaskCommandParser extends FindCommandParser {

    /**
     * Checks for any data that does not follow format.
     *
     * @param groups All groups captured by the g/ flag.
     * @return A new FindPersonCommand Object ready for execution.
     * @throws ParseException An invalid input was found.
     */
    public FindTaskCommand parse(List<String> groups) throws ParseException {


        return new FindTaskCommand(new TaskContainsKeywordPredicate(groups));
    }
}


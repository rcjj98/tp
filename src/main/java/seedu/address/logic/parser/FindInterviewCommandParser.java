package seedu.address.logic.parser;

import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.InterviewContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

public class FindInterviewCommandParser implements Parser<FindInterviewCommand> {

    @Override
    public FindInterviewCommand parse(String args) throws ParseException {

        ArgumentMultimap groups = ArgumentTokenizer.tokenize(args, PREFIX_GROUP);










        return new FindInterviewCommand(new InterviewContainsKeywordsPredicate(new ArrayList<>()));
    }
}

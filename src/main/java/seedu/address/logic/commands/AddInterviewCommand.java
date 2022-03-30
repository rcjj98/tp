package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.Type.INTERVIEW;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.interview.Date;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Time;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddInterviewCommand extends AddCommand {


    public static final String MESSAGE_USAGE = COMMAND_WORD + " [i] : Adds a interview to the interview list. "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "26-May-2021"
            + PREFIX_TIME + "05:29";


    public static final String MESSAGE_SUCCESS = "New interview added!";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists in the interview list";

    private final Index index;
    private final Date date;
    private final Time time;

    /**
     * Creates an AddInterviewCommand to add the specified {@code Interview}
     */
    public AddInterviewCommand(Index index, Date date, Time time) {
        requireNonNull(index);
        requireNonNull(date);
        requireNonNull(time);
        this.index = index;
        this.date = date;
        this.time = time;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToInterview = lastShownList.get(index.getZeroBased());
        Interview toAdd = createInterview(personToInterview, date, time);

        if (model.hasInterview(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        model.addInterview(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), INTERVIEW);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Interview createInterview(Person personToInterview, Date date, Time time) {
        assert personToInterview != null;

        return new Interview(personToInterview, date, time);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInterviewCommand // instanceof handles nulls
                && index.equals(((AddInterviewCommand) other).index)
                && date.equals(((AddInterviewCommand) other).date)
                && time.equals(((AddInterviewCommand) other).time));
    }

    @Override
    public Type getType() {
        return INTERVIEW;
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
* Deletes a person identified using it's displayed index from the address book.
*/
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;
    private final String targetApplicant;

    /**
    * Constructor for DeleteCommand.
    * @param targetIndex
    */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.targetApplicant = "";
    }

    /**
     * Constructor to faciliate deletion of Job Applicant via name.
     * @param args the name of the user
     */
    public DeleteCommand(String targetApplicant, Index targetIndex) {
        this.targetApplicant = targetApplicant;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // send to method that deletes applicant via name
        if (!targetApplicant.isEmpty()) {
            return deleteByName(model, lastShownList, targetApplicant);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    /**
     * Method that handles deletion of a person from the list via name.
     * Need to find the position of the person in the list who matches the
     * targetName
     * @param model
     * @param lastShownList   A List of type Person
     * @param targetApplicant
     * @return CommandResult
     */
    private CommandResult deleteByName(Model model, List<Person> lastShownList, String targetApplicant)
            throws CommandException {

        int pos = 0;
        int len = lastShownList.size();

        for (Person person : lastShownList) {
            String name = person.getName().getfullName().toLowerCase();

            if (name.equals(targetApplicant.toLowerCase())) {
                break;
            } else {
                ++pos;
                continue;
            }
        }

        // have gone past the limits of given list
        if (pos >= len) {
            throw new CommandException(Messages.MESSAGE_INVALID_NAME_PROVIDED);
        }

        // person to be deleted
        Person personToDelete = lastShownList.get(pos);
        model.deletePerson(personToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                        && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}

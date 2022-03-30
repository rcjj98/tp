package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_HAS_INTERVIEW;
import static seedu.address.logic.parser.Type.PERSON;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;

public class DeletePersonCommand extends DeleteCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " [p] : Deletes the applicant identified by the index number used in the displayed applicant list,\n"
            + "Or deletes by identifying the applicant with their name\n"
            + "Parameters: INDEX (must be a positive integer),\n"
            + "Example: " + COMMAND_WORD + " [p] 1\n"
            + "Or, Parameters: NAME (must be a string), \n"
            + "Example: " + COMMAND_WORD + " [p] Jeremy\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Applicant has been deleted!";

    private final Index targetIndex;
    private final String targetApplicant;

    /**
     * Constructor for DeletePersonCommand.
     * @param targetIndex
     */
    public DeletePersonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.targetApplicant = "";
    }

    /**
     * Constructor to faciliate deletion of Job Applicant via name.
     * @param targetApplicant the name of the user
     */
    public DeletePersonCommand(String targetApplicant, Index targetIndex) {
        this.targetApplicant = targetApplicant;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        // send to method that deletes applicant via name
        if (!targetApplicant.isEmpty() || targetIndex == null) {
            return deleteByName(model, lastShownPersonList, targetApplicant);
        }

        if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownPersonList.get(targetIndex.getZeroBased());

        if (personHasInterview(personToDelete, model)) {
            throw new CommandException(MESSAGE_PERSON_HAS_INTERVIEW);
        }

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete), PERSON);
    }

    /**
     * Check if person that we wish to delete has interview
     *
     * @param person person
     * @param model model
     * @return true if person has interview
     */
    public boolean personHasInterview(Person person, Model model) {
        List<Interview> lastShownInterviewList = model.getFilteredInterviewList();
        for (int i = 0; i < lastShownInterviewList.size(); i++) {
            if (lastShownInterviewList.get(i).getPerson().equals(person)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that handles deletion of a person from the list via name.
     * Need to find the position of the person in the list who matches the
     * targetName
     * @param model
     * @param lastShownList A List of type Person
     * @param targetApplicant
     * @return CommandResult
     */
    private CommandResult deleteByName(Model model, List<Person> lastShownList, String targetApplicant)
            throws CommandException {

        if (targetApplicant.trim().equals("")) {
            throw new CommandException(Messages.MESSAGE_INVALID_NAME_PROVIDED);
        }

        int pos = 0;
        int len = lastShownList.size();

        for (Person person : lastShownList) {
            String name = person.getName().getFullName().toLowerCase();

            if (name.equals(targetApplicant.toLowerCase().trim())) {
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

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete), getType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePersonCommand) other).targetIndex)); // state check
    }

    @Override
    public Type getType() {
        return PERSON;
    }
}



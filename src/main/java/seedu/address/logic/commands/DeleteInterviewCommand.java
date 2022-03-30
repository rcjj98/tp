package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.INTERVIEW;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

public class DeleteInterviewCommand extends DeleteCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " [i] : Deletes the Interview identified by the index number used in the displayed Interview list,\n"
            + "Or deletes by identifying the user with their name\n"
            + "Parameters: INDEX (must be a positive integer),\n"
            + "Example: " + COMMAND_WORD + " [i] 1\n"
            + "Or, Parameters: NAME (must be a string), \n"
            + "Example: " + COMMAND_WORD + " [i] Jeremy\n";

    public static final String MESSAGE_DELETE_INTERVIEW_SUCCESS = "Interview has been deleted!";

    private final Index targetIndex;
    private final String targetApplicant;

    /**
     * Constructor for DeleteInterviewCommand.
     * @param targetIndex
     */
    public DeleteInterviewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.targetApplicant = "";
    }

    /**
     * Constructor to faciliate deletion of Job Applicant via name.
     * @param targetApplicant the name of the user
     */
    public DeleteInterviewCommand(String targetApplicant, Index targetIndex) {
        this.targetApplicant = targetApplicant;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Interview> lastShownList = model.getFilteredInterviewList();

        // send to method that deletes applicant via name
        if (!targetApplicant.isEmpty() || targetIndex == null) {
            return deleteByName(model, lastShownList, targetApplicant);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }
        Interview interviewToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteInterview(interviewToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INTERVIEW_SUCCESS, interviewToDelete), INTERVIEW);
    }

    /**
     * Method that handles deletion of a interview from the list via name of interviewee.
     * Need to find the position of the interview in the list who matches the
     * targetName
     * @param model
     * @param lastShownList A List of type interview
     * @param targetApplicant
     * @return CommandResult
     */
    private CommandResult deleteByName(Model model, List<Interview> lastShownList, String targetApplicant)
            throws CommandException {

        int pos = 0;
        int len = lastShownList.size();

        for (Interview interview : lastShownList) {
            String name = interview.getPerson().getName().getFullName().toLowerCase();

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

        // Interview to be deleted
        Interview interviewToDelete = lastShownList.get(pos);
        model.deleteInterview(interviewToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_INTERVIEW_SUCCESS, interviewToDelete), getType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteInterviewCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteInterviewCommand) other).targetIndex)); // state check
    }

    @Override
    public Type getType() {
        return INTERVIEW;
    }
}


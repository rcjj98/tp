package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Exports all data from all job applicants to a CSV file\n";

    private final String strCsvFilePath;

    /**
     * Constructor for Export Command.
     *
     * @param strCsvFilePath File path of CSV file to export job applicant data to.
     */
    public ExportCommand(String strCsvFilePath) {
        requireNonNull(strCsvFilePath);
        this.strCsvFilePath = strCsvFilePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Exported job applicants' data to " + strCsvFilePath, getType());
    }

}

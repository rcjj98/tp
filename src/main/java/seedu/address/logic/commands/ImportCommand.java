package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + "Imports all job applicants data from csv/json file\n"
        + "Parameters: filepath to csv/json file";

    private List<Person> personList;

    /**
     * Constructor for Import Command.
     *
     * @param personList List of persons to be added.
     */
    public ImportCommand(List<Person> personList) {
        requireNonNull(personList);
        this.personList = personList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // checks if any persons is duplicated
        for (int i = 0; i < personList.size(); i++) {
            if (model.hasPerson(personList.get(i))) {
                int lineNo = i + 1;
                throw new CommandException("Entry " + lineNo + ": is already in address book.\nAborting now.");
            }
        }

        personList.forEach(model::addPerson);
        return new CommandResult("Added " + personList.size() + " people to address book.", Type.PERSON);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        personList.forEach(person -> s.append(person.toString() + "\n"));
        return s.toString().strip();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ImportCommand)) {
            return false;
        }

        // state check
        ImportCommand e = (ImportCommand) other;
        return this.toString().equals(e.toString());
    }
}

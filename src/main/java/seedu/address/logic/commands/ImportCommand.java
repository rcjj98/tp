package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports all data from csv or json file";

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
                throw new CommandException("Entry " + i + " is already in address book.\nAborting now.");
            }
        }

        // adds persons to address book
        for (Person p : personList) {
            model.addPerson(p);
        }

        return new CommandResult("Added " + personList.size() + " people to address book.", Type.PERSON);
    }

}

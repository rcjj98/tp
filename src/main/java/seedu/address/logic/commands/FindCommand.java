package seedu.address.logic.commands;


import seedu.address.model.Model;

/**
 * Finds and lists all persons or interviews in address book who contains  the argument keywords.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    @Override
    public abstract CommandResult execute(Model model);

    @Override
    public abstract boolean equals(Object other);
}

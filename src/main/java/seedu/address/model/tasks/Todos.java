package seedu.address.model.tasks;

import seedu.address.logic.parser.taskCommandsParser.Description;

public class Todos extends Task {

    public Todos(Description description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.getDescription();
    }
}

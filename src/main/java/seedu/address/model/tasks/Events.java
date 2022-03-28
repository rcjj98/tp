package seedu.address.model.tasks;

import seedu.address.logic.parser.taskCommandsParser.At;
import seedu.address.logic.parser.taskCommandsParser.Description;

/**
 * Task that occurs on a specified date.
 */
public class Events extends Task {
    
    private At at;

    public Events(Description description, At at) {
        super(description);
        this.at = at;
    }

    public At getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.getDescription() + " (at: " + at + ")";
    }
}

package seedu.address.model.tasks;

import seedu.address.logic.parser.taskCommandsParser.By;
import seedu.address.logic.parser.taskCommandsParser.Description;

/**
 * Task that needs to be done before a specific date.
 */
public class Deadlines extends Task {
    
    private By by;

    /**
     * Deadline will take in a description and a by deadline.
     */
    public Deadlines(Description description, By by) {
        super(description);
        this.by = by;
    }

    @Override 
    public String toString() {
        return "[D]" + super.getDescription() + " (by: " + by + ")";
    }
}

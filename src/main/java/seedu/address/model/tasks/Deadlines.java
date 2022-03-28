package seedu.address.model.tasks;

import seedu.address.logic.parser.taskCommandsParser.By;
import seedu.address.logic.parser.taskCommandsParser.Description;

/**
 * Task that needs to be done before a specific date.
 */
public class Deadlines extends Task {
    
    private String by;

    /**
     * Deadline will take in a description and a by deadline.
     */
    public Deadlines(Description description, By by2) {
        super(description);
        this.by = by2;
    }

    @Override 
    public String toString() {
        return "[D]" + super.getDescription() + " (by: " + by + ")";
    }
}

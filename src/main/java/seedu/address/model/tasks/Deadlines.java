package seedu.address.model.tasks;

/**
 * Task that needs to be done before a specific date.
 */
public class Deadlines extends Task {
    
    private String by;

    /**
     * Deadline will take in a description and a by deadline.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override 
    public String toString() {
        return "[D]" + super.getDescription() + " (by: " + by + ")";
    }
}

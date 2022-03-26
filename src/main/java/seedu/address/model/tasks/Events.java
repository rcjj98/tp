package seedu.address.model.tasks;

/**
 * Task that occurs on a specified date.
 */
public class Events extends Task {
    
    private String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt(String at) {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.getDescription() + " (at: " + at + ")";
    }
}

package seedu.address.model.application;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/** Represents an Application in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Application {

    //fields
    private final Job job;
    private final Stage stage;


    /**
     * Every field must be present and not null.
     */
    public Application(Job job, Stage stage) {
        requireAllNonNull(job, stage);
        this.job = job;
        this.stage = stage;
    }

    public Job getJob() {
        return job;
    }

    public Stage getStage() {
        return stage;
    }

    /**
     * Returns true if both applications are the same.
     * This defines a stronger notion of equality between two applications.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        Application otherApplication = (Application) other;
        return otherApplication == this;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(job, stage);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[Job: ")
                .append(getJob())
                .append(", Stage: ")
                .append(getStage())
                .append("]");
        return builder.toString();
    }

}

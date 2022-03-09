package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Job {

    public static final String MESSAGE_CONSTRAINTS = "Job ID should be numeric";
    public static final String VALIDATION_REGEX = "\\d+";

    public final String jobId;


    /**
     * Constructs a {@code Job}.
     * @param jobId A valid job Id.
     */
    public Job(String jobId) {
        requireNonNull(jobId);
        checkArgument(isValidJobId(jobId), MESSAGE_CONSTRAINTS);
        this.jobId = jobId;
    }

    /**
     * Returns true if a given string is a valid job id.
     */
    public static boolean isValidJobId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Job // instanceof handles nulls
                && jobId.equals(((Job) other).jobId)); // state check
    }

    @Override
    public int hashCode() {
        return jobId.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return jobId;
    }

}

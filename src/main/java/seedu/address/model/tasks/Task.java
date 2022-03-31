package seedu.address.model.tasks;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Task {

    private Information information;

    /**
     * Every field must be present and not null.
     */
    public Task(Information information) {
        requireAllNonNull(information);
        this.information = information;
    }

    public Information getInformation() {
        return information;
    }

    /**
     * Returns true if both Tasks have the same Information.
     * This defines a weaker notion of equality between two Tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && (otherTask.getInformation().equals(getInformation()));
    }

    /**
     * Returns true if both interviews have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getInformation().equals(getInformation());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getInformation());

        return builder.toString();
    }
}

package seedu.address.model.tasks;

public class Task {

    private Description description;

    public Task(Description description) {
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if both Tasks have the same description.
     * This defines a weaker notion of equality between two Tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        String currDescription = this.getDescription().fullDescription;

        return otherTask != null
                && (currDescription.equals(otherTask.getDescription().fullDescription));
    }

}

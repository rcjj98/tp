package seedu.address.model.tasks;

public class Task {

    private Description description;

    public Task(Description description) {
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }
    
}

package seedu.address.model.tasks;

import seedu.address.logic.parser.taskCommandsParser.Description;

public class Task {

    private Description description;

    public Task(Description description) {
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }
    
}

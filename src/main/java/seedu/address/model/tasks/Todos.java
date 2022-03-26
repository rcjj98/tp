package seedu.address.model.tasks;

public class Todos extends Task {

    public Todos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.getDescription();
    }
}

package seedu.address.model.tasks;

public class Todos extends Task {

    public Todos(Description description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.getDescription();
    }
}

package seedu.address.model.tasks;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list containing Tasks that has only the add and delete methods.
 */
public class UniqueTaskList  implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    // what is this for?
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    /**
     * Adds a Task to the list.
     * The Task must not already exist in the list.
     * @throws Exception
     */
    public void add(Task toAdd) throws Exception {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent Task from the list.
     * The Task must exist in the list.
     * May have to edit argument to accept int index, 
     * and delete that particular element in the list. 
     * @throws Exception
     */
    public void remove(Task toRemove) throws Exception {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new Exception();
        }
    }

    
    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }
    
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
    
}

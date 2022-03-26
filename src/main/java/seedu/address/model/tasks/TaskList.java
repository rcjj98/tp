package seedu.address.model.tasks;

import java.util.ArrayList;

public class TaskList {
    
    private ArrayList<Task> tasks;

    /**
     * Note that from Duke.java, the storage.load() argument should return an
     * ArrayList<Task> so that we can copy that list directly to this class.
     * @param tasks - the tasks we get from duke.txt
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Second constructor for a blank ArrayList, indicating theres nothing in the
     * storage.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    // will need to get the arrayList here
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * The CRUD operations to be performed on task.
     */

    /**
     * Deletes a task from the list.
     * @return ArrayList tasks
     */
    public void deleteFromTasks(int number) {
        tasks.remove(number);
    }

    /**
     * Add another task to the list.
     * @return ArrayList<Task> tasks
     */
    public void addToTasks(Task task) {
        tasks.add(task);
    }
}

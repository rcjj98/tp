package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.tasks.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Interview> PREDICATE_SHOW_ALL_INTERVIEWS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces person list data.
     */
    void resetPersons();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if an interview with the same identity as {@code interview} exists in the address book.
     */
    boolean hasInterview(Interview interview);

    /**
     * Deletes the given interview.
     * The interview must exist in the address book.
     */
    void deleteInterview(Interview target);

    /**
     * Adds the given interview.
     * {@code interview} must not already exist in the address book.
     */
    void addInterview(Interview interview);

    /**
     * Replaces the given interview {@code target} with {@code editedInterview}.
     * {@code target} must exist in the address book.
     * The interview identity of {@code editedInterview} must not be the same as another
     * existing interview in the address book.
     */
    void setInterview(Interview target, Interview editedInterview);

    /**
     * Replaces interview list data.
     */
    void resetInterviews();

    /** Returns an unmodifiable view of the filtered interview list */
    ObservableList<Interview> getFilteredInterviewList();

    /**
     * Updates the filter of the filtered interview list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInterviewList(Predicate<Interview> predicate);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Adds the given Task.
     * {@code task} must not already exist in the address book.
    */
    void addTask(Task toAdd);

    /**
     * Deletes the given Task.
     * The Task must exist in the address book.
     */
    void deleteTask(Task taskToDelete) throws Exception;

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    boolean hasTask(Task task);

}

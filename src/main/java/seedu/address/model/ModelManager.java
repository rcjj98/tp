package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.tasks.Deadlines;
import seedu.address.model.tasks.Events;
import seedu.address.model.tasks.Todos;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Interview> filteredInterviews;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredInterviews = new FilteredList<>(this.addressBook.getInterviewList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasInterview(Interview interview) {
        requireNonNull(interview);
        return addressBook.hasInterview(interview);
    }

    @Override
    public void deleteInterview(Interview target) {
        addressBook.removeInterview(target);
    }

    @Override
    public void addInterview(Interview interview) {
        addressBook.addInterview(interview);
        updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
    }

    @Override
    public void setInterview(Interview target, Interview editedInterview) {
        requireAllNonNull(target, editedInterview);
        addressBook.setInterview(target, editedInterview);
    }

    //=========== Filtered List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Interview} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Interview> getFilteredInterviewList() {
        return filteredInterviews;
    }

    @Override
    public void updateFilteredInterviewList(Predicate<Interview> predicate) {
        requireNonNull(predicate);
        filteredInterviews.setPredicate(predicate);
    }


    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    //=========== Task List methods =============================================================


    // @Override
    // public void addPerson(Person person) {
    //     addressBook.addPerson(person);
    //     updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    // }

    // @Override
    // public void addInterview(Interview interview) {
    //     addressBook.addInterview(interview);
    //     updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
    // }

    @Override
    public void addTodo(Todos todo) {
        requireNonNull(todo);    
        addressBook.addTask(todo);
        // need to get the overall list of tasks 
        // can get insipration from the UniqueInterviewList,
        // simple add delete functions, no edit required.
    }

    @Override 
    public void deleteTodo(Todos todo) throws Exception {
        requireNonNull(todo);
        addressBook.removeTask(todo);
    }

    @Override
    public void addDeadline(Deadlines deadline) {
        requireNonNull(deadline);
    }

    @Override
    public void addEvent(Events event) {
        requireNonNull(event);        
    }

}

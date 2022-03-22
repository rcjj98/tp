package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.application.Application;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Application> applications = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Application> applications) {
        requireAllNonNull(name, phone, email, address, applications);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.applications.addAll(applications);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable application set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Application> getApplications() {
        return Collections.unmodifiableSet(applications);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getApplications().equals(getApplications());
    }

    public boolean contains(String term) {

        if (term.contains("jobid:")) {
            String id = term.split(":")[1];

            for (Application app : getApplications()) {
                if (app.getJob().toString().equals(id)) {
                    return true;
                }
            }
        }

        if (term.contains("progress:")) {
            String stage = term.split(":")[1].toLowerCase();

            for (Application app : getApplications()) {
                if (app.getStage().toString().toLowerCase().equals(stage)) {
                    return true;
                }
            }
        }

        String thisName = getName().fullName.toLowerCase();
        String thisPhone = getPhone().toString().toLowerCase();
        String thisEmail = getEmail().toString().toLowerCase();
        String thisAddress = getAddress().toString().toLowerCase();

        if (thisName.contains(term) || thisPhone.contains(term)
                || thisEmail.contains(term) || thisAddress.contains(term)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, applications);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Application> applications = getApplications();
        if (!applications.isEmpty()) {
            builder.append("; Applications: ");
            applications.forEach(builder::append);
        }
        return builder.toString();
    }

}

package seedu.address.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.application.Application;
import seedu.address.model.application.Job;
import seedu.address.model.application.Stage;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
                new Person(new Name("Alex Lee"), new Phone("87438807"), new Email("alexlee@gmail.com"),
                        new Address("30 Defu Lane 10 #04-104"), getApplicationSet("1")),
                new Person(new Name("Alex Tan"), new Phone("94825832"), new Email("alext@gmail.com"),
                        new Address("1 Finlayson Green #13-00"), getApplicationSet("2", "3")),
                new Person(new Name("Alex Chan"), new Phone("82619180"), new Email("alex1996@yahoo.com"),
                        new Address("6 Ang Mo Kio Industrial Park 2"), getApplicationSet("2", "3")),
                new Person(new Name("Alex Lee Kai Jie"), new Phone("92462946"), new Email("alexlkj@gmail.com"),
                        new Address("76 Lorong 19 Geylang #03-01"), getApplicationSet("4")),
                new Person(new Name("Steven Tan Kai Ming"), new Phone("84303712"), new Email("stevent@yahoo.com"),
                        new Address("10 Admiralty Street #01-78 North Link Building"), getApplicationSet("1")),
                new Person(new Name("Lee Kai Jie"), new Phone("92821318"), new Email("lkj1994@gmail.com"),
                        new Address("252 Jurong East Street 24 #01-139"), getApplicationSet("2", "3")),
                new Person(new Name("Janet Tan"), new Phone("94303312"), new Email("janettan@yahoo.com"),
                        new Address("10 Admiralty Street #01-78 North Link Building"), getApplicationSet("1")),
                new Person(new Name("Lew Jia Xin"), new Phone("92573826"), new Email("lewjx@hotmail.com"),
                        new Address("261 Yishun St 22 #01-137"), getApplicationSet("1", "4")),
                new Person(new Name("Zhuo Jia Ling"), new Phone("92462946"), new Email("jialingz@hotmail.com"),
                        new Address("80 Marine Parade Rd #21-08"), getApplicationSet("2", "4")),
                new Person(new Name("Tan Jia Ling"), new Phone("93375454"), new Email("tjialing@outlook.com"),
                        new Address("12 New Industrial Road #05-05 Morningstar Centre"), getApplicationSet("2"))
        };


    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Application> getApplicationSet(String... strings) {
        Set<Application> applications = new HashSet<>();
        for (int i = 0; i < strings.length; i++) {
            applications.add(new Application(new Job(strings[i]), Stage.INPROGRESS));
        }
        return applications;
    }

}

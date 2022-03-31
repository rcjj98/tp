package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Exports all data from all job applicants to a CSV file\n";
    public static final String MISSING_JSON_FILE = "Json file containing job applicant data not found.";

    private final String strCsvFilePath;

    /**
     * Constructor for Export Command.
     *
     * @param strCsvFilePath File path of CSV file to export job applicant data to.
     */
    public ExportCommand(String strCsvFilePath) {
        requireNonNull(strCsvFilePath);
        this.strCsvFilePath = strCsvFilePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Person> personList = model.getFilteredPersonList(); //to read from json and write to CSV

        try {
            //create new csv file to export data into
            File csvFile = new File(strCsvFilePath);
            PrintWriter pw = new PrintWriter(csvFile);

            //export all persons data from addressbook.json into specified csv file
            for (Person p : personList) {
                String name = p.getName().toString();
                String phone = p.getPhone().toString();
                String email = p.getEmail().toString();
                String address = p.getAddress().toString();
                String job = p.getJob().toString();
                String stage = p.getStage().toString();
                pw.printf("%s\t%s\t%s\t%s\t%s\t%s\n", name, phone, email, address, job, stage);
            }
            pw.close();

        } catch (FileNotFoundException e) {
            throw new CommandException(MISSING_JSON_FILE);
        }
        return new CommandResult("Exported " + personList.size() + " job applicants' data to "
            + strCsvFilePath, getType());
    }

}

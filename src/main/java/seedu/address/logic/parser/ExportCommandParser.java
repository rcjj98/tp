package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.FileUtil.isValidPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;

public class ExportCommandParser implements Parser<ExportCommand> {

    public static final String INVALID_FILE_PATH = "That is not a valid file path\n"
        + "Please check for any illegal characters.";
    public static final String INVALID_JSON_FILE_FORMAT = "Error while parsing JSON file\n "
        + "Please ensure that all data fields is correct.";
    public static final String MISSING_JSON_FILE = "Json file containing job applicant data not found.";
    public static final String WRONG_FILE_TYPE = "No json file extension found.";

    /**
     * Parses the user input of the path to target csv file to export job applicant data into.
     *
     * @param args String path to the csv file.
     * @return ExportCommand with path to csv file.
     */
    public ExportCommand parse(String args) throws ParseException {

        //check whether valid path input given by the user
        String strCsvFilePath = args.strip();
        checkFilePath(strCsvFilePath);

        return writeToCsvFile(strCsvFilePath);

    }

    /**
     * Writes the details of every job applicant into the specified csv file in the path.
     *
     * @param filePath String filepath to the target csv file to export data into
     * @return ExportCommand with the same specified filePath.
     * @throws ParseException If addressbook.json is missing or has invalid json format.
     */
    public ExportCommand writeToCsvFile(String filePath) throws ParseException {

        try {
            //create new csv file to export data into
            File csvFile = new File(filePath);
            PrintWriter pw = new PrintWriter(csvFile);

            //retrieve data from current addressbook.json file
            Path addressBookFilePath = Paths.get("data", "addressbook.json");
            Optional<ReadOnlyAddressBook> newAddrBook;
            newAddrBook = new JsonAddressBookStorage(addressBookFilePath).readAddressBook();

            //export all persons data from addressbook.json into csv file
            List<Person> personList = newAddrBook.get().getPersonList();
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
            return new ExportCommand(filePath);

        } catch (FileNotFoundException e) {
            throw new ParseException(MISSING_JSON_FILE);

        } catch (DataConversionException e) {
            throw new ParseException(INVALID_JSON_FILE_FORMAT);
        }
    }

    /**
     * Checks if specified filepath to csv file is not empty string and a valid csv filepath.
     *
     * @param path filepath to target csv file.
     * @throws ParseException If filepath is "" or not a valid filepath or not a .csv file.
     */
    private void checkFilePath(String path) throws ParseException {
        if (path.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        } else if (!isValidPath(path)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, INVALID_FILE_PATH));
        } else if (!isValidCsvFile(path)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, WRONG_FILE_TYPE));
        }
    }

    /**
     * Checks if the given file path leads to a file with .csv extension.
     *
     * @param path String filepath to target file.
     * @return boolean true or false.
     */
    private boolean isValidCsvFile(String path) {
        return path.endsWith(".csv");
    }
}

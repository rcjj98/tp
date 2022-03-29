package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Job;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Stage;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;

import javax.swing.text.html.Option;

public class ImportCommandParser implements Parser<ImportCommand> {

    private static final int NUM_OF_FIELDS = 6;

    @Override
    public ImportCommand parse(String args) throws ParseException {
        String filepath = args.strip();

        if (filepath.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        } else if (!FileUtil.isValidPath(filepath)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "That is not a valid file path"));
        } else if (!FileUtil.isFileExists(Paths.get(filepath))) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        "The file does not exists or it is not an actual file.\n"
                        + "If using relative path, your current working directory is "
                        + System.getProperty("user.dir")));
        }

        try {
            if (filepath.endsWith(".csv")) {
                return new ImportCommand(readCsv(filepath));
            } else if (filepath.endsWith(".json")) {
                return new ImportCommand(readJson(filepath));
            } else {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, "No csv or json file extension found"));
            }
        } catch (IOException e) {
            throw new ParseException("File cannot be parsed due to invalid entries.");
        }
    }

    private List<Person> readCsv(String path) throws IOException, ParseException {
        requireNonNull(path);
        List<Person> personList = new ArrayList<>();
        List<String> entries = FileUtil.readFromFileLines(Paths.get(path));

        for (int i = 0; i < entries.size(); i++) {
            String[] fields = entries.get(i).strip().split("\t");

            if (fields.length != NUM_OF_FIELDS) {
                int lineno = i + 1;
                throw new ParseException("Line " + lineno + " length of fields is not correct.");
            }

            try {
                Name name = ParserUtil.parseName(fields[0]);
                Phone phone = ParserUtil.parsePhone(fields[1]);
                Email email = ParserUtil.parseEmail(fields[2]);
                Address address = ParserUtil.parseAddress(fields[3]);
                Job job = ParserUtil.parseJob(fields[4]);
                Stage stage = ParserUtil.parseStage(fields[5]);
                personList.add(new Person(name, phone, email, address, job, stage));
            } catch (ParseException e) {
                int lineno = i + 1;
                throw new ParseException("Line " + lineno + " " + e);
            }
        }

        return personList;
    }

    private List<Person> readJson(String path) throws ParseException {

        try {
            Optional<ReadOnlyAddressBook> newAddrBook = new JsonAddressBookStorage(Paths.get(path)).readAddressBook();

            if (newAddrBook.isPresent()) {
                return newAddrBook.get().getPersonList();
            } else {
                return new ArrayList<>();
            }

        } catch (DataConversionException e) {
            throw new ParseException(e.toString());
        }
    }
}

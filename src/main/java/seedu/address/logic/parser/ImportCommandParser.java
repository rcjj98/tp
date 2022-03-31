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
import seedu.address.storage.JsonAddressBookStorage;


public class ImportCommandParser implements Parser<ImportCommand> {

    public static final String FILE_DOES_NOT_EXIST = "The file does not exists or it is not an actual file.\n"
            + "If using relative path, your current working directory is "
            + System.getProperty("user.dir");
    public static final String INVALID_FILE_PATH = "That is not a valid file path\n"
            + "Please check for any illegal characters.";
    public static final String PARSE_ERROR = "File cannot be parsed due to invalid entries";
    public static final String WRONG_FILE_TYPE = "No csv or json file extension found";
    private static final int NUM_OF_FIELDS = 6;

    @Override
    public ImportCommand parse(String args) throws ParseException {
        String filepath = args.strip();
        checkFilePath(filepath);

        try {
            if (filepath.endsWith(".csv")) {
                return new ImportCommand(readCsv(Paths.get(filepath)));
            } else {
                return new ImportCommand(readJson(Paths.get(filepath)));
            }
        } catch (IOException e) {
            throw new ParseException(PARSE_ERROR);
        }
    }


    private List<Person> readCsv(Path path) throws IOException, ParseException {
        requireNonNull(path);
        List<Person> personList = new ArrayList<>();
        List<String> entries = FileUtil.readFromFileLines(path);

        for (int i = 0; i < entries.size(); i++) {
            String[] allFields = entries.get(i).strip().split("\t");
            ArrayList<String> fieldList = new ArrayList<>();

            for (String f : allFields) {
                String field = f.strip();

                if (!field.equals("")) {
                    fieldList.add(field);
                }
            }

            if (fieldList.size() != NUM_OF_FIELDS) {
                int lineNo = i + 1;
                throw new ParseException("Line " + lineNo + ": length of fields is not correct");
            }

            try {
                Name name = ParserUtil.parseName(fieldList.get(0).strip());
                Phone phone = ParserUtil.parsePhone(fieldList.get(1).strip());
                Email email = ParserUtil.parseEmail(fieldList.get(2).strip());
                Address address = ParserUtil.parseAddress(fieldList.get(3).strip());
                Job job = ParserUtil.parseJob(fieldList.get(4).strip());
                Stage stage = ParserUtil.parseStage(fieldList.get(5).strip());
                personList.add(new Person(name, phone, email, address, job, stage));
            } catch (ParseException e) {
                int lineno = i + 1;
                throw new ParseException("Line " + lineno + ": " + e.getMessage());
            }
        }

        return personList;
    }

    private List<Person> readJson(Path path) throws ParseException {

        try {
            Optional<ReadOnlyAddressBook> newAddrBook = new JsonAddressBookStorage(path).readAddressBook();

            if (newAddrBook.isPresent()) {
                return newAddrBook.get().getPersonList();
            } else {
                return new ArrayList<>();
            }

        } catch (DataConversionException e) {
            throw new ParseException("Error while parsing JSON file.\n"
                    + "Please ensure that all data fields exists and is valid.");
        }
    }

    private void checkFilePath(String path) throws ParseException {
        if (path.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        } else if (!FileUtil.isValidPath(path)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, INVALID_FILE_PATH));
        } else if (!FileUtil.isFileExists(Paths.get(path))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FILE_DOES_NOT_EXIST));
        } else if (!(path.endsWith(".csv") || path.endsWith(".json"))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, WRONG_FILE_TYPE));
        }
    }
}

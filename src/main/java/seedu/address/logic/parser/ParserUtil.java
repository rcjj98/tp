package seedu.address.logic.parser;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STAGE_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_STAGE_FIELD;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.Application;
import seedu.address.model.application.Job;
import seedu.address.model.application.Stage;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;




/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String jobId} into a {@code Job}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code jobId} is invalid.
     */
    public static Job parseJob(String jobId) throws ParseException {
        requireNonNull(jobId);
        String trimmedJobId = jobId.trim();
        if (!Job.isValidJobId(trimmedJobId)) {
            throw new ParseException(Job.MESSAGE_CONSTRAINTS);
        }
        return new Job(trimmedJobId);
    }

    /**
     * Parses a {@code Job job} into a {@code Application}.
     *
     * @throws ParseException if the given {@code job} is invalid.
     */
    public static Application parseAppDetails(String application) throws ParseException {
        requireNonNull(application);
        String[] appDetails = application.split(",");

        if (appDetails.length == 1) {
            throw new ParseException(MESSAGE_MISSING_STAGE_FIELD);
        }

        String jobId = appDetails[0]; //1
        Job job = parseJob(jobId);

        requireNonNull(appDetails[1]);
        String strStage = appDetails[1];
        String trimmedStrStage = strStage.trim();

        try {
            Stage stage = Stage.valueOf(trimmedStrStage);
            return new Application(job, stage);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new ParseException(MESSAGE_INVALID_STAGE_FORMAT);
        }
    }

    /**
     * Parses {@code Collection<String> jobIds} into a {@code Set<Job>}.
     */
    public static Set<Job> parseJobs(Collection<String> jobIds) throws ParseException {
        requireNonNull(jobIds);
        final Set<Job> jobSet = new HashSet<>();
        for (String jobId : jobIds) {
            jobSet.add(parseJob(jobId));
        }
        return jobSet;
    }

    /**
     * Parses {@code Collection<Job> jobs} into a {@code Set<Application>}.
     */
    public static Set<Application> parseApplications(Collection<String> applications) throws ParseException {
        requireNonNull(applications);
        final Set<Application> applicationSet = new HashSet<>();
        for (String application: applications) {
            applicationSet.add(parseAppDetails(application));
        }
        return applicationSet;
    }
}

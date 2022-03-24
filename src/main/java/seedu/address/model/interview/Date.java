package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Represents a Interview's date in the interview list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {


    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format dd-MM-YYYY like 26-Jun-2001";
    public final String value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidDate(String test) {
        DateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
        sdf.setLenient(false);
        try {
            sdf.parse(test);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

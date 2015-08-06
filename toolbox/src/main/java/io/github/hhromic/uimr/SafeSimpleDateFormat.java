package io.github.hhromic.uimr;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

/**
 * A Thread-safe (re-entrant) {@code SimpleDateFormat} wrapper class.
 *
 * <p>This class implements a Thread-Safe (re-entrant) {@code SimpleDateFormat}
 * class.  It does this by using a {@code ThreadLocal} object that holds a map,
 * instead of a traditional approach to hold the {@code SimpleDateFormat} object
 * in a {@code ThreadLocal} object.</p>
 *
 * <p>Each {@code ThreadLocal} object holds a single {@code HashMap} containing
 * {@code SimpleDateFormat}s, keyed by a string format (e.g. "yyyy/M/d", etc.),
 * for each new {@code SimpleDateFormat} instance that was created within the
 * threads execution context.</p>
 *
 * <p>Original code taken from <a href="http://code.google.com/p/safe-simple-date-format/">here</a>,
 * by John DeRegnaucourt. See link for more information.</p>
 *
 * @see SimpleDateFormat
 * @author John DeRegnaucourt
 * @since 1.0
 */
public class SafeSimpleDateFormat {
    /** The string representation of the format. */
    private final String format;

    /** The {@code ThreadLocal} instance to hold unsafe {@code DateFormat} objects. */
    private static final ThreadLocal<Map<String,DateFormat>> dateFormats = new ThreadLocal<Map<String,DateFormat>>() {
        @Override
        protected Map<String,DateFormat> initialValue() {
            return new HashMap<String,DateFormat>();
        }
    };

    /**
     * Gets an instance of {@code SimpleDateFormat}.
     *
     * <p>This is thread-safe.</p>
     *
     * @param format the format to use.
     * @return the {@code SimpleDateFormat} instance.
     */
    private DateFormat getDateFormat(final String format) {
        final Map<String,DateFormat> formatters = dateFormats.get();
        DateFormat formatter = formatters.get(format);
        if (formatter == null) {
            formatter = new SimpleDateFormat(format, Locale.ROOT);
            formatters.put(format, formatter);
        }
        return formatter;
    }

    /**
     * Creates a new {@code SafeSimpleDateFormat} with specified format.
     *
     * @param format the format to use.
     * @see SimpleDateFormat
     */
    public SafeSimpleDateFormat(final String format) {
        this.format = format;
    }

    /**
     * Formats a {@code Date} into a string using this formatter.
     *
     * @param date the {@code Date} object to format.
     * @return the string representation of the date.
     * @see SimpleDateFormat
     */
    public String format(final Date date) {
        return getDateFormat(format).format(date);
    }

    /**
     * Formats an {@code Object} into a string using this formatter.
     *
     * @param object the {@code Object} to format.
     * @return the string representation of the object.
     * @see SimpleDateFormat
     */
    public String format(final Object object) {
        return getDateFormat(format).format(object);
    }

    /**
     * Parses a string into a {@code Date} using this formatter.
     *
     * @param string the string to parse.
     * @return the parsed {@code Date} object representing the string.
     * @see SimpleDateFormat
     */
    public Date parse(final String string) throws ParseException {
        return getDateFormat(format).parse(string);
    }
}

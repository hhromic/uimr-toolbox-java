package io.github.hhromic.uimr;

import java.util.Date;
import java.text.ParseException;

/**
 * Handy conversion methods between UNIX timestamps (long) and strings.
 *
 * <p><strong>Example usage:</strong></p>
 *
 * <pre>
 * import io.github.hhromic.uimr.Timestamp;
 *
 * public class MyClass {
 *     public static void main(final String[] args) {
 *         // This example uses the default format
 *         System.out.println(Timestamp.toString(System.currentTimeMillis());
 *         System.out.println(Timestamp.fromString("2011-05-15 14:30:00.250"));
 *     }
 * }
 * </pre>
 *
 * @author Hugo Hromic
 * @since 1.0
 */
public class Timestamp {
    /**
     * Default timestamp string representation to parse from/format to.
     *
     * <p>The default format is defined as: {@code new SafeSimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")}.</p>
     *
     * @see java.util.text.SimpleDateFormat documentation for date formats.
     * @see SafeSimpleDateFormat
     */
    public static final SafeSimpleDateFormat DEFAULT_FORMAT = new SafeSimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Formats the specified timestamp (long) using the specified date format.
     *
     * @param timestamp the timestamp to format.
     * @param dateFormat the date format to use for formatting.
     * @return the string representation of the timestamp.
     */
    public static String toString(final long timestamp, final SafeSimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timestamp));
    }

    /**
     * Formats the specified timestamp (long) using the default date format.
     *
     * @param timestamp the timestamp to format.
     * @return the string representation of the timestamp.
     * @see #DEFAULT_FORMAT
     */
    public static String toString(final long timestamp) {
        return toString(timestamp, DEFAULT_FORMAT);
    }

    /**
     * Parses the specified string using a specified date format.
     *
     * @param string the string to parse into a timestamp.
     * @param dateFormat the date format to use for parsing.
     * @return the parsed timestamp.
     * @see SafeSimpleDateFormat
     */
    public static long fromString(final String string, final SafeSimpleDateFormat dateFormat) throws ParseException {
        return dateFormat.parse(string).getTime();
    }

    /**
     * Parses the specified string using the default date format.
     *
     * @param string the string to parse into a timestamp.
     * @return the parsed timestamp.
     * @see #DEFAULT_FORMAT
     */
    public static long fromString(final String string) throws ParseException {
        return fromString(string, DEFAULT_FORMAT);
    }
}

package io.github.hhromic.uimr;

import java.util.Properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * General purpose, easy to use, program properties handling class.
 *
 * <p>This class makes working with program properties much more convenient. It handles default values and
 * provides easy parsing methods from textual values to different numeric/array data types, including timestamps.
 * It's strongly recommended that you use this class by extending it with custom getter methods.
 * See the example for details.</p>
 *
 * <p><strong>Example usage:</strong></p>
 *
 * <pre>{@code
 * import io.github.hhromic.uimr.EasyProperties;
 *
 * public class MyProperties extends EasyProperties {
 *     public MyProperties(final String fileName) {
 *         loadFromFile(fileName);
 *     }
 *
 *     public double getMyProperty() {
 *         return parseDouble("program.my_property", 123.45);  // provide a default value
 *     }
 * }
 *
 * public class MyClass {
 *     private final static MyProperties props = new MyProperties("program.properties");
 *
 *     public static void main(final String[] args) {
 *         System.out.println(props.getMyProperty());  // returns either the configured or default value
 *     }
 * }
 * }</pre>
 *
 * @author Hugo Hromic
 * @since 1.0
 */
public class EasyProperties {
    /** Logger object for this EasyProperties object. */
    private final static Logger logger = LoggerFactory.getLogger(EasyProperties.class);

    /** String lists item separator. */
    public final static String LIST_ITEM_SEPARATOR = ",";

    /** Base Java Properties object to hold the properties. */
    private final Properties properties = new Properties();

    /**
     * Loads program properties from a specified file name.
     *
     * <p>If there is any error, default values will be used. This method is expected to never fail.</p>
     *
     * @param fileName file to read program properties from
     */
    public void loadFromFile(final String fileName) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            properties.load(reader);
        } catch (FileNotFoundException e) {
            logger.warn("Properties file '{}' not found, using defaults.", fileName);
        } catch (IOException e) {
            logger.warn("I/O exception reading properties file '{}' (now using defaults): {}", fileName, e.getMessage());
        }
    }

    /**
     * Gets a {@code String} value from a property.
     *
     * @param property the property name to get the value from
     * @param defaultValue the default value to use if property can't be found
     * @return {@code String} value of the property or default value
     */
    public String getString(final String property, final String defaultValue) {
        return properties.getProperty(property, defaultValue);
    }

    /**
     * Parses a {@code boolean} value from a property.
     *
     * @param property the property name to parse the {@code boolean} from
     * @param defaultValue the default value to use if a {@code boolean} value can't be parsed
     * @return parsed {@code boolean} value or default value
     */
    public boolean parseBoolean(final String property, final boolean defaultValue) {
        try {
            return Boolean.parseBoolean(properties.getProperty(property, Boolean.toString(defaultValue)));
        } catch (NumberFormatException e) {
            logger.warn("Invalid boolean value for '{}', using default: {}", property, Boolean.toString(defaultValue));
            return defaultValue;
        }
    }

    /**
     * Parses an {@code int} value from a property.
     *
     * @param property the property name to parse the {@code int} from
     * @param defaultValue the default value to use if a {@code int} value can't be parsed
     * @return parsed {@code int} value or default value
     */
    public int parseInteger(final String property, final int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(property, Integer.toString(defaultValue)));
        } catch (NumberFormatException e) {
            logger.warn("Invalid integer value for '{}', using default: {}", property, Integer.toString(defaultValue));
            return defaultValue;
        }
    }

    /**
     * Parses a {@code long} value from a property.
     *
     * @param property the property name to parse the {@code long} from
     * @param defaultValue the default value to use if a {@code long} value can't be parsed
     * @return parsed {@code long} value or default value
     */
    public long parseLong(final String property, final long defaultValue) {
        try {
            return Long.parseLong(properties.getProperty(property, Long.toString(defaultValue)));
        } catch (NumberFormatException e) {
            logger.warn("Invalid long value for '{}', using default: {}", property, Long.toString(defaultValue));
            return defaultValue;
        }
    }

    /**
     * Parses a {@code double} value from a property.
     *
     * @param property the property name to parse the {@code double} from
     * @param defaultValue the default value to use if a {@code double} value can't be parsed
     * @return parsed {@code double} value or default value
     */
    public double parseDouble(final String property, final double defaultValue) {
        try {
            return Double.parseDouble(properties.getProperty(property, Double.toString(defaultValue)));
        } catch (NumberFormatException e) {
            logger.warn("Invalid double value for '{}', using default: %s", property, Double.toString(defaultValue));
            return defaultValue;
        }
    }

    /**
     * Parses an {@code Enum} value from a property.
     *
     * @param <T> the type for the {@code Enum}
     * @param property the property name to parse the {@code Enum} from
     * @param defaultValue the default value to use if an {@code Enum} value can't be parsed
     * @param enumClass the {@code Enum} class to use for parsing the value from
     * @return parsed {@code Enum} value or default value
     */
    public <T extends Enum<T>> T parseEnumeration(final String property, final T defaultValue, final Class<T> enumClass) {
        try {
            return Enum.valueOf(enumClass, properties.getProperty(property, defaultValue.toString()));
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid enumeration value for '{}', using default: %s", property, defaultValue.toString());
            return defaultValue;
        }
    }

    /**
     * Parses a timestamp ({@code long}) value from a property.
     *
     * <p>This method uses {@link Timestamp#fromString(String,SafeSimpleDateFormat)} to parse the timestamp.</p>
     *
     * @param property the property name to parse the timestamp from
     * @param dateFormat the date format to use when parsing
     * @param defaultValue the default value to use if timestamp value can't be parsed
     * @return parsed timestamp ({@code long}) value or default value
     * @see SafeSimpleDateFormat
     */
    public long parseTimestamp(final String property, final SafeSimpleDateFormat dateFormat, final long defaultValue) {
        try {
            return Timestamp.fromString(properties.getProperty(property, Timestamp.toString(defaultValue, dateFormat)), dateFormat);
        } catch (ParseException e) {
            logger.warn("Invalid timestamp for '{}', using default: %s", property, Timestamp.toString(defaultValue));
            return defaultValue;
        }
    }

    /**
     * Parses a timestamp ({@code long}) value from a property, with a default format.
     *
     * <p>This method uses {@link #parseTimestamp(String,SafeSimpleDateFormat,long)} with the
     * default format value from {@link Timestamp#DEFAULT_FORMAT}.</p>
     *
     * @param property the property name to parse the timestamp from
     * @param defaultValue the default value to use if timestamp value can't be parsed
     * @return parsed timestamp ({@code long}) value or default value
     */
    public long parseTimestamp(final String property, final long defaultValue) {
        return parseTimestamp(property, Timestamp.DEFAULT_FORMAT, defaultValue);
    }

    /**
     * Parses an array of strings from a property.
     *
     * @param property the property name to parse the array of strings from
     * @param defaultValue the default value to use if array value can't be parsed
     * @return parsed array of strings
     */
    public String[] parseStringArray(final String property, final String[] defaultValue) {
        final String propertyValue = properties.getProperty(property);
        if (propertyValue == null)
            return defaultValue;

        // Parse and trim the property value
        final String[] parsedArray = propertyValue.split(LIST_ITEM_SEPARATOR, -1);
        final String[] trimmedArray = new String[parsedArray.length];
        for (int i=0; i<parsedArray.length; i++)
            trimmedArray[i] = parsedArray[i].trim();
        return trimmedArray;
    }
}

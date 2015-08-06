package io.github.hhromic.uimr.mysql;

import io.github.hhromic.uimr.EasyProperties;

/**
 * Properties class for using a MySQL server.
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public class MySQLProperties extends EasyProperties {
    /** Default properties file name. */
    public static final String PROPERTIES_FILE = "mysql.properties";

    /** Internal singleton holder class. */
    private static class SingletonHolder {
        public static final MySQLProperties instance = new MySQLProperties();
    }

    /** Get a singleton instance of this class. */
    public static MySQLProperties getInstance() {
        return SingletonHolder.instance;
    }

    /** Private constructor for singleton class. */
    private MySQLProperties() {
        loadFromFile(PROPERTIES_FILE);
    }

    /**
     * Get the host of the MySQL server.
     *
     * <p>Default value: {@code 127.0.0.1}.</p>
     *
     * @return the configured value.
     */
    public String getHost() {
        return getString("host", "127.0.0.1");
    }

    /**
     * Get the port number of the MySQL server.
     *
     * <p>Default value: {@code 3306}.</p>
     *
     * @return the configured value.
     */
    public int getPort() {
        return parseInteger("port", 3306);
    }

    /**
     * Get the user for the MySQL server.
     *
     * <p>Default value: {@code guest}.</p>
     *
     * @return the configured value.
     */
    public String getUser() {
        return getString("user", "guest");
    }

    /**
     * Get the password for the MySQL server.
     *
     * <p>Default value: {@code guest}.</p>
     *
     * @return the configured value.
     */
    public String getPassword() {
        return getString("password", "guest");
    }

    /**
     * Get the database name for the MySQL server.
     *
     * <p>Default value: {@code database}.</p>
     *
     * @return the configured value.
     */
    public String getDatabase() {
        return getString("database", "database");
    }
}

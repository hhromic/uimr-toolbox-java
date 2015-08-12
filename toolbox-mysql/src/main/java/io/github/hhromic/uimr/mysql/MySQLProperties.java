package io.github.hhromic.uimr.mysql;

import io.github.hhromic.uimr.EasyProperties;

/**
 * Properties class for using a MySQL server.
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public class MySQLProperties extends EasyProperties {
    public static final String PROPERTIES_FILE = "mysql.properties";
    public static final String DEF_HOST = "localhost";
    public static final int DEF_PORT = 3306;
    public static final String DEF_USER = "guest";
    public static final String DEF_PASSWORD = "guest";
    public static final String DEF_DATABASE = "database";

    /** Internal singleton holder class. */
    private static class SingletonHolder {
        public static final MySQLProperties instance = new MySQLProperties();
    }

    /**
     * Gets a singleton instance of this class.
     *
     * @return the instance of this class
     */
    public static MySQLProperties getInstance() {
        return SingletonHolder.instance;
    }

    /** Private constructor for singleton class. */
    private MySQLProperties() {
        loadFromFile(PROPERTIES_FILE);
    }

    /**
     * Gets the configured host of the MySQL server.
     *
     * @return the configured host of the MySQL server
     */
    public String getHost() {
        return getString("host", DEF_HOST);
    }

    /**
     * Gets the configured port number of the MySQL server.
     *
     * @return the configured port number of the MySQL server
     */
    public int getPort() {
        return parseInteger("port", DEF_PORT);
    }

    /**
     * Gets the configured user for the MySQL server.
     *
     * @return the configured user for the MySQL server
     */
    public String getUser() {
        return getString("user", DEF_USER);
    }

    /**
     * Gets the configured password for the MySQL server.
     *
     * @return the configured password for the MySQL server
     */
    public String getPassword() {
        return getString("password", DEF_PASSWORD);
    }

    /**
     * Gets the configured database name for the MySQL server.
     *
     * @return the configured database name for the MySQL server
     */
    public String getDatabase() {
        return getString("database", DEF_DATABASE);
    }
}

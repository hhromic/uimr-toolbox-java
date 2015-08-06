package io.github.hhromic.uimr.mysql;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MySQL databases base management class.
 *
 * <p>This class makes working with MySQL much easier. It's intended that you extend this
 * class to suit your needs. See the following example.</p>
 *
 * <p><strong>Example usage:</strong></p>
 *
 * <pre>
 * import io.github.hhromic.uimr.mysql.BaseMySQLManager;
 *
 * public class DatabaseManager extends BaseMySQLManager {
 *     public List<Person> getPeople() {
 *        Connection connection = null;
 *        try {
 *            Connection connection = getConnection();
 *            connection.setAutoCommit(false); // set for transaction support if you want
 *            final Statement st = connection.createStatement(); // you can also use prepared statements
 *            final List<Person> results = new ArrayList<>();
 *
 *            ... usual JDBC stuff with the statement ...
 *
 *            connection.commit();
 *            return results;
 *        } catch (SQLException e) {
 *            System.out.println("Something went wrong: " + e.getMessage());
 *            rollback(connection);  // If you used transactions (autoCommit = false)
 *            return null;
 *        } finally {
 *            close(connection);
 *        }
 *     }
 * }
 *
 * public class MyClass {
 *     public static void main(final String[] args) {
 *         DatabaseManager dbManager = new DatabaseManager("127.0.0.1", 3306, "myuser", "mypass", "mydb");
 *         System.out.println(dbManager.getPeople());
 *     }
 * }
 * </pre>
 *
 * @author Hugo Hromic
 * @since 1.0
 */
public class BaseMySQLManager {
    /** Logger object for this MySQL manager. */
    private final static Logger logger = LoggerFactory.getLogger(BaseMySQLManager.class);

    /** Internal datasource holder. */
    private final DataSource dataSource;

    /**
     * Creates a new MySQL manager with connection details read from MySQL properties.
     */
    protected BaseMySQLManager() {
        this(MySQLProperties.getInstance().getHost(),
            MySQLProperties.getInstance().getPort(),
            MySQLProperties.getInstance().getUser(),
            MySQLProperties.getInstance().getPassword(),
            MySQLProperties.getInstance().getDatabase());
    }

    /**
     * Creates a new MySQL manager with given connection details.
     *
     * @param serverHost the server host IP/address to connect
     * @param serverPort the server port number
     * @param dbUser the username to use when connecting
     * @param dbPassword the user password to use when connecting
     * @param dbName the database name to use when connecting
     */
    protected BaseMySQLManager(final String serverHost, final int serverPort, final String dbUser, final String dbPassword, final String dbName) {
        final MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName(serverHost);
        mysqlDataSource.setPortNumber(serverPort);
        mysqlDataSource.setUser(dbUser);
        mysqlDataSource.setPassword(dbPassword);
        mysqlDataSource.setDatabaseName(dbName);
        dataSource = mysqlDataSource;
        logger.info("MySQL resource @ mysql://{}:***@{}:{}/{}", dbUser, serverHost, serverPort, dbName);
    }

    /**
     * Gets/opens a MySQL connection.
     *
     * @return the connection to the configured MySQL resource
     * @see #BaseMySQLManager
     */
    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Rolls back an open connection.
     *
     * @param connection the connection to roll back
     */
    protected void rollback(final Connection connection) {
        try {
            if (connection != null)
                connection.rollback();
        }
        catch (SQLException e) {
            logger.error("Can't rollback MySQL connection: {}", e.getMessage());
        }
    }

    /**
     * Closes an open statement and connection.
     *
     * <p>This is a shortcut method for calling {@link #close(Statement)} and then
     * {@link #close(Connection)} in one go.</p>
     *
     * @param statement the statement to close
     * @param connection the connection to close
     */
    protected void close(final Statement statement, final Connection connection) {
        close(statement);
        close(connection);
    }

    /**
     * Closes an open statement.
     *
     * @param statement the statement to close
     */
    protected void close(final Statement statement) {
        try {
            if (statement != null)
                statement.close();
        }
        catch (SQLException e) {
            logger.error("Can't close MySQL statement: {}", e.getMessage());
        }
    }

    /**
     * Closes an open connection.
     *
     * @param connection the connection to close
     */
    protected void close(final Connection connection) {
        try {
            if (connection != null)
                connection.close();
        }
        catch (SQLException e) {
            logger.error("Can't close MySQL connection: {}", e.getMessage());
        }
    }

    /**
     * Inserts a list of rows using a single transaction.
     *
     * @param rows the list of rows to insert
     * @return true or false on success
     */
    public boolean transactionalInsert(final List<RowForInsertion> rows) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            for (final RowForInsertion row : rows) {
                final StringBuilder query = new StringBuilder();
                query.append(String.format("INSERT%s INTO `%s` SET ", (row.insertIgnore?" IGNORE":""), row.table));
                for (final ColumnForInsertion column : row.columns)
                    query.append(String.format("`%s`='%s',", column.name, column.value));
                query.deleteCharAt(query.length() - 1);

                // Execute statement
                statement.executeUpdate(query.toString());
            }
            connection.commit();
            return true;
        }
        catch (SQLException e) {
            rollback(connection);
            logger.error("Can't insert rows: {}", e.getMessage());
            return false;
        }
        finally {
            close(statement, connection);
        }
    }
}

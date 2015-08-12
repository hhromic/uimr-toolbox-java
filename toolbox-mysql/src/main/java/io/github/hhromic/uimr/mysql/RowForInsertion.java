package io.github.hhromic.uimr.mysql;

import java.util.List;
import java.util.Arrays;

/**
 * Represents a MySQL row to be inserted.
 *
 * <p>Use this class to build rows to be used for INSERT statements.</p>
 *
 * <p><strong>Example usage:</strong></p>
 *
 * <pre>{@code
 * import io.github.hhromic.uimr.mysql.MySQLManager;
 * import io.github.hhromic.uimr.mysql.RowForInsertion;
 * import io.github.hhromic.uimr.mysql.ColumnForInsertion;
 *
 * public class MyClass {
 *     public static void main(final String[] args) {
 *         MySQLManager mysql = new MySQLManager("127.0.0.1", 3306, "myuser", "mypass", "mydb");
 *
 *         final List<ColumnForInsertion> columns = new ArrayList<>();
 *         columns.add(new ColumnForInsertion("id", 1234));
 *         columns.add(new ColumnForInsertion("name", "hair dryer"));
 *         columns.add(new ColumnForInsertion("price", 225.60));
 *         final RowForInsertion row = new RowForInsertion("products_table", columns, true);
 *         System.out.println(row);
 *
 *         mysql.transactionalInsert(row);
 *     }
 * }
 * }</pre>
 *
 * @author Hugo Hromic
 * @since 1.0
 */
public class RowForInsertion {
    /** Database table name for insertion. */
    public String table;

    /** List of columns for the row. */
    public List<ColumnForInsertion> columns;

    /** Flag to indicate if the insertion should ignore duplicates. */
    public boolean insertIgnore;

    /**
     * Creates a new row with a single column to be inserted.
     *
     * @param table the table name on the database to insert this row
     * @param column the single column to use for this row
     * @param insertIgnore flag to indicate if the INSERT operation should ignore duplicates
     * @see ColumnForInsertion
     */
    public RowForInsertion(final String table, final ColumnForInsertion column, final boolean insertIgnore) {
        this(table, Arrays.asList(new ColumnForInsertion[]{ column }), insertIgnore);
    }

    /**
     * Creates a new row with a list of columns to be inserted.
     *
     * @param table the table name on the database to insert this row
     * @param columns the list of columns to use for this row
     * @param insertIgnore flag to indicate if the INSERT operation should ignore duplicates
     * @see ColumnForInsertion
     */
    public RowForInsertion(final String table, final List<ColumnForInsertion> columns, final boolean insertIgnore) {
        this.table = table;
        this.columns = columns;
        this.insertIgnore = insertIgnore;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("<RowForInsertion in '%s': %s>", table, columns);
    }
}

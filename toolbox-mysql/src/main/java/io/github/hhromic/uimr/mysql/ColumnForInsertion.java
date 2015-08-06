package io.github.hhromic.uimr.mysql;

/**
 * Represents a MySQL column to be used in a row for insertion.
 *
 * <p>Use this class to build columns to be used in rows for INSERT statements.</p>
 *
 * <p><strong>Example usage:</strong></p>
 * <p>See the {@link RowForInsertion} example usage.</p>
 *
 * @author Hugo Hromic
 * @see RowForInsertion
 * @since 1.0
 */
public class ColumnForInsertion {
    /** Name of the column for insertion. */
    public String name;

    /** Value for the column. */
    public Object value;

    /**
     * Creates a new column for insertion.
     *
     * @param name the name for the new column
     * @param value the value to be used for the new column
     */
    public ColumnForInsertion(final String name, final Object value) {
        this.name = name;
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("<ColumnForInsertion '%s': '%s'>", name, value.toString());
    }
}

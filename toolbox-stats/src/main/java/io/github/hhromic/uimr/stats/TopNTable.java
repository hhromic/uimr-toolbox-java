package io.github.hhromic.uimr.stats;

import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

/**
 * This class handles a top-n data table.
 *
 * <p>The {@code K} type defines the keys type and the {@code V} defines the values type.
 * the top-n data table sorts its entries by value in descending order. For same values,
 * the entries are then sorted by the key in ascending order.</p>
 *
 * <p>The top-n table is internally backed by a {@code TreeSet} object.</p>
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public class TopNTable<K extends Comparable<K>,V extends Comparable<V>> {
    private final int n;
    private final TreeSet<Entry<K,V>> entries;

    /** Table Entry class. */
    public static class Entry<K extends Comparable<K>,V extends Comparable<V>> implements Comparable<Entry<K,V>> {
        private final K key;
        private V value;

        /** Creates a new {@code Entry} object. */
        public Entry() {
            this(null, null);
        }

        /** Creates a new {@code Entry} object. */
        public Entry(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Gets the key of this entry.
         *
         * @return the key of this entry.
         */
        public K getKey() {
            return key;
        }

        /**
         * Gets the value of this entry.
         *
         * @return the value of this entry.
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets or replaces the value for this entry.
         *
         * @param value the value to set or replace.
         * @return the old value of this entry.
         */
        public V setValue(final V value) {
            final V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        /** {@inheritDoc} */
        @Override
        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^
                (value == null ? 0 : value.hashCode());
        }

        /** {@inheritDoc} */
        @Override
        public boolean equals(final Object other) {
            if (!(other instanceof Entry))
                return false;
            final Entry entry = (Entry)other;
            return (key == null ? entry.getKey() == null : key.equals(entry.getKey())) &&
                (value == null ? entry.getValue() == null : value.equals(entry.getValue()));
        }

        /** {@inheritDoc} */
        @Override
        public int compareTo(final Entry<K,V> other) {
            final int cmp = other.getValue().compareTo(value);
            if (cmp == 0)
               return other.getKey().compareTo(key);
            return cmp;
        }

        /** {@inheritDoc} */
        @Override
        public String toString() {
            return String.format("%s=%s", key, value);
        }
    }

    /** Creates a new {@code TopNTable} object with default cap (n) of 10. */
    public TopNTable() {
        this(10);
    }

    /**
     * Creates a new {@code TopNTable} object.
     *
     * @param n the cap (n) of the top-n data table.
     */
    public TopNTable(final int n) {
        this.n = n;
        entries = new TreeSet<Entry<K,V>>();
    }

    /**
     * Gets the cap (n) of the top-n data table.
     *
     * @return the cap (n) of the top-n table.
     */
    public int getN() {
        return n;
    }

    /**
     * Gets a list view of current entries from the top-n data table.
     *
     * <p>The returned list is a copy of the internal top-n data table,
     * modifications to this list are not reflected into the internal structure.</p>
     *
     * @return the list view of current entries from the top-n data table.
     */
    public List<Entry<K,V>> getEntries() {
        return new ArrayList<Entry<K,V>>(entries);
    }

    /**
     * Gets a particular entry from the top-n data table.
     *
     * @param key the key of the entry to get from the top-n data table.
     * @return the desired entry, or {@code null} if the key is not found.
     * @throws NullPointerException if the key is null.
     */
    public Entry<K,V> getEntry(final K key) {
        if (key == null)
            throw new NullPointerException();
        for (final Entry<K,V> entry : entries)
            if (entry.getKey().equals(key))
                return entry;
        return null;
    }

    /**
     * Updates a data element in the top-n data table.
     *
     * <p>If the data element is not found, it is created and inserted.</p>
     *
     * @param key the key for the data element to update or add.
     * @param value the value for the data element to update or add.
     * @return this same object.
     * @throws NullPointerException if the key and/or value are null.
     */
    public TopNTable<K,V> update(final K key, final V value) {
        if (key == null || value == null)
            throw new NullPointerException(key == null ? "key":"value");
        final Entry<K,V> oldEntry = getEntry(key);
        if (oldEntry != null) {
            if (oldEntry.getValue().equals(value))
                return this;
            entries.remove(oldEntry);
        }
        entries.add(new Entry<K,V>(key, value));
        if (entries.size() > n)
            entries.remove(entries.last());
        return this;
    }

    /**
     * Adds all data from a top-n data table object to this top-n data table.
     *
     * @param topNTable top-n data table object to be added to this top-n data table.
     * @return this same object.
     * @throws NullPointerException if the top-n data table is null.
     */
    public TopNTable<K,V> addAll(final TopNTable<K,V> topNTable) {
        if (topNTable == null)
            throw new NullPointerException();
        for (final Entry<K,V> entry : topNTable.getEntries())
            update(entry.getKey(), entry.getValue());
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("{n=%d, entries=%s}", n, entries);
    }
}

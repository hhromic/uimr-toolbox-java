package io.github.hhromic.uimr.stats;

import java.util.Map;
import java.util.HashMap;

/**
 * This class handles a data distribution table.
 *
 * <p>The internal counters are backed by {@code long} numbers
 * and a {@code HashMap} object.</p>
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public class Distribution<E> {
    private final Map<E,Long> distribution;
    private long totalCount;

    /** Creates a new {@code Distribution} object. */
    public Distribution() {
        distribution = new HashMap<E,Long>();
        totalCount = 0L;
    }

    /**
     * Gets the current data distribution seen so far.
     *
     * @return the current data distribution seen so far
     */
    public Map<E,Long> getDistribution() {
        return distribution;
    }

    /**
     * Gets the total count of the data seen so far.
     *
     * @return the total count of the data seen so far
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * Increments the count of a data element by some amount.
     *
     * <p>If the data element is not found, it is initialised to zero.</p>
     *
     * @param element the data element to increment its count
     * @param amount the amount to increment the count
     * @return this same object
     * @throws NullPointerException if the element is null
     */
    public Distribution<E> increment(final E element, final long amount) {
        if (element == null)
            throw new NullPointerException();
        if (!distribution.containsKey(element))
            distribution.put(element, amount);
        else
            distribution.put(element, distribution.get(element) + amount);
        totalCount += amount;
        return this;
    }

    /**
     * Adds all data from a data distribution object to this distribution.
     *
     * @param distribution distribution object to be added to this distribution
     * @return this same object
     * @throws NullPointerException if the distribution is null
     */
    public Distribution<E> addAll(final Distribution<E> distribution) {
        if (distribution == null)
            throw new NullPointerException();
        for (final Map.Entry<E,Long> entry : distribution.getDistribution().entrySet())
            increment(entry.getKey(), entry.getValue());
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("{distribution=%s, totalCount=%d}",
            distribution, totalCount);
    }
}

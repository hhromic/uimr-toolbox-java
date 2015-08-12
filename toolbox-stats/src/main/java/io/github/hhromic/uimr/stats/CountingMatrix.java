package io.github.hhromic.uimr.stats;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Generic Counting Matrix class.
 *
 * <p>This class implements a counting sparse matrix with generic key types.
 * The internal counts are backed by {@code long} numbers.</p>
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public class CountingMatrix<K1,K2> {
    private final Map<K1,Map<K2,Long>> sparseMatrix;
    private final Map<K1,Long> totalCounts;

    /** Creates a new {@code CountingMatrix} object. */
    public CountingMatrix() {
        sparseMatrix = new HashMap<K1,Map<K2,Long>>();
        totalCounts = new HashMap<K1,Long>();
    }

    /**
     * Gets all the keys stored for the first dimension.
     *
     * @return the keys of the first dimension
     */
    public Set<K1> getKeys() {
        return sparseMatrix.keySet();
    }

    /**
     * Gets all the keys stored for the second dimension for a particular first dimension key.
     *
     * @param k1 the key for the first dimension
     * @return the keys of the second dimension, {@code null} if the key is not found
     */
    public Set<K2> getKeys(final K1 k1) {
        if (sparseMatrix.containsKey(k1))
            return sparseMatrix.get(k1).keySet();
        return null;
    }

    /**
     * Gets the count of a particular (k1,k2) key pair.
     *
     * @param k1 the key for the first dimension
     * @param k2 the key for the second dimension
     * @return the count of the key pair, zero if the (k1,k2) pair is not found
     */
    public long getCount(final K1 k1, final K2 k2) {
        if (sparseMatrix.containsKey(k1) && sparseMatrix.get(k1).containsKey(k2))
            return sparseMatrix.get(k1).get(k2);
        return 0L;
    }

    /**
     * Increments the count for a particular (k1,k2) key pair.
     *
     * <p>If there is no count yet, it is initialised to 0.</p>
     *
     * @param k1 the key for the first dimension
     * @param k2 the key for the second dimension
     * @param amount the amount to increment the count
     */
    public void increment(final K1 k1, final K2 k2, final long amount) {
        // Increment count for (k1,k2) key pair
        if (!sparseMatrix.containsKey(k1))
            sparseMatrix.put(k1, new HashMap<K2,Long>());
        if (!sparseMatrix.get(k1).containsKey(k2))
            sparseMatrix.get(k1).put(k2, amount);
        else
            sparseMatrix.get(k1).put(k2, sparseMatrix.get(k1).get(k2) + amount);

        // Update total count for the first dimension key
        if (!totalCounts.containsKey(k1))
            totalCounts.put(k1, amount);
        else
            totalCounts.put(k1, totalCounts.get(k1) + amount);
    }

    /**
     * Gets the total count for a particular first dimension key.
     *
     * @param k1 the key for the first dimension
     * @return the total count for the first dimension key, zero if the key is not found
     */
    public long getTotalCount(final K1 k1) {
        if (totalCounts.containsKey(k1))
            return totalCounts.get(k1);
        return 0L;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("{sparseMatrix=%s, totalCounts=%s}",
            sparseMatrix, totalCounts);
    }
}

package io.github.hhromic.uimr.stats;

/**
 * This interface defines Statistics for {@code Number}s.
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public interface Statistics<T extends Number> {
    /**
     * Gets the current value of the data accumulator.
     *
     * @return the current value of the data accumulator.
     */
    public T getAccumulator();

    /**
     * Gets the current value of the squared data accumulator.
     *
     * @return the current value of the squared data accumulator.
     */
    public T getSquaredAccumulator();

    /**
     * Gets the minimum data value seen so far.
     *
     * @return the minimum data value seen so far.
     */
    public T getMin();

    /**
     * Gets the maximum data value seen so far.
     *
     * @return the maximum data value seen so far.
     */
    public T getMax();

    /**
     * Gets the mean of the data seen so far.
     *
     * @param n the sample size to use.
     * @return the mean of the data seen so far.
     */
    public double getMean(final long n);

    /**
     * Gets the variance of the data seen so far.
     *
     * @param n the sample size to use.
     * @return the variance of the data seen so far.
     */
    public double getVariance(final long n);

    /**
     * Gets the standard deviation of the data seen so far.
     *
     * @param n the sample size to use.
     * @return the standard deviation of the data seen so far.
     */
    public double getStdDeviation(final long n);

    /**
     * Adds a new data value point.
     *
     * @param data the data point to add.
     * @return this same object.
     */
    public Statistics<T> add(final T data);

    /**
     * Adds all data from a statistics object to this statistics.
     *
     * @param statistics statistics object to be added to this statistics.
     * @return this same object.
     */
    public Statistics<T> addAll(final Statistics<T> statistics);

    /** {@inheritDoc} */
    @Override
    public String toString();
}

package io.github.hhromic.uimr.stats;

/**
 * This class implements Statistics for {@code Long} data values.
 *
 * @author Hugo Hromic
 * @since 4.0
 * @see Statistics
 */
public class LongStatistics implements Statistics<Long> {
    private long accumulator;
    private long squaredAccumulator;
    private long min;
    private long max;

    /** Creates a new {@code LongStatistics} object. */
    public LongStatistics() {
        accumulator = 0L;
        squaredAccumulator = 0L;
        min = Long.MAX_VALUE;
        max = Long.MIN_VALUE;
    }

    /** {@inheritDoc} */
    public Long getAccumulator() {
        return accumulator;
    }

    /** {@inheritDoc} */
    public Long getSquaredAccumulator() {
        return squaredAccumulator;
    }

    /** {@inheritDoc} */
    public Long getMin() {
        return min;
    }

    /** {@inheritDoc} */
    public Long getMax() {
        return max;
    }

    /** {@inheritDoc} */
    public double getMean(final long n) {
        return accumulator * 1.0 / n;
    }

    /** {@inheritDoc} */
    public double getVariance(final long n) {
        final double mean = getMean(n);
        return (squaredAccumulator * 1.0 / n) - (mean * mean);
    }

    /** {@inheritDoc} */
    public double getStdDeviation(final long n) {
        return Math.sqrt(getVariance(n));
    }

    /** {@inheritDoc}
     * @throws NullPointerException if the data is null
     */
    public LongStatistics add(Long data) {
        if (data == null)
            throw new NullPointerException();
        accumulator += data;
        squaredAccumulator += data * data;
        min = Math.min(min, data);
        max = Math.max(max, data);
        return this;
    }

    /** {@inheritDoc}
     * @throws NullPointerException if the statistics is null
     */
    public LongStatistics addAll(final Statistics<Long> statistics) {
        if (statistics == null)
            throw new NullPointerException();
        accumulator += statistics.getAccumulator();
        squaredAccumulator += statistics.getSquaredAccumulator();
        min = Math.min(min, statistics.getMin());
        max = Math.max(max, statistics.getMax());
        return this;
    }

    /** {@inheritDoc} */
    public String toString() {
        return String.format("{accumulator=%d, squaredAccumulator=%d, min=%d, max=%d}",
            accumulator, squaredAccumulator, min, max);
    }
}

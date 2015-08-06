package io.github.hhromic.uimr.stats;

/**
 * This class implements Statistics for {@code Double} data values.
 *
 * @author Hugo Hromic
 * @since 4.0
 * @see Statistics
 */
public class DoubleStatistics implements Statistics<Double> {
    private double accumulator;
    private double squaredAccumulator;
    private double min;
    private double max;

    /** Creates a new {@code DoubleStatistics} object. */
    public DoubleStatistics() {
        accumulator = 0.0;
        squaredAccumulator = 0.0;
        min = Double.MAX_VALUE;
        max = -Double.MAX_VALUE;
    }

    /** {@inheritDoc} */
    public Double getAccumulator() {
        return accumulator;
    }

    /** {@inheritDoc} */
    public Double getSquaredAccumulator() {
        return squaredAccumulator;
    }

    /** {@inheritDoc} */
    public Double getMin() {
        return min;
    }

    /** {@inheritDoc} */
    public Double getMax() {
        return max;
    }

    /** {@inheritDoc} */
    public double getMean(final long n) {
        return accumulator / n;
    }

    /** {@inheritDoc} */
    public double getVariance(final long n) {
        final double mean = getMean(n);
        return (squaredAccumulator / n) - (mean * mean);
    }

    /** {@inheritDoc} */
    public double getStdDeviation(final long n) {
        return Math.sqrt(getVariance(n));
    }

    /** {@inheritDoc}
     * @throws NullPointerException if the data is null.
     */
    public DoubleStatistics add(Double data) {
        if (data == null)
            throw new NullPointerException();
        accumulator += data;
        squaredAccumulator += data * data;
        min = Math.min(min, data);
        max = Math.max(max, data);
        return this;
    }

    /** {@inheritDoc}
     * @throws NullPointerException if the statistics is null.
     */
    public DoubleStatistics addAll(final Statistics<Double> statistics) {
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
        return String.format("{accumulator=%f, squaredAccumulator=%f, min=%f, max=%f}",
            accumulator, squaredAccumulator, min, max);
    }
}

package io.github.hhromic.uimr.concurrent;

/**
 * A named {@code Runnable} with handy features to be used with an UIMR executor.
 *
 * @author Hugo Hromic
 * @since 1.0
 * @see Runnable
 * @see UIMRExecutor
 */
public interface UIMRRunnable extends Runnable {
    /**
      * Gets this UIMR runnable name.
      *
      * @return the name of this runnable
      */
    public abstract String getName();

    /**
      * Skips any current suspension of this UIMR runnable.
      *
      * <p>This method skips any current thread suspension and continues execution without raising an {@link InterruptedException}.</p>
      */
    public abstract void skipCurrentSuspend();
}

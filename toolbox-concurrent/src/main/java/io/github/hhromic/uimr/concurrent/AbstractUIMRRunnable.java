package io.github.hhromic.uimr.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/**
 * This class provides a skeletal implementation of the {@link UIMRRunnable} interface
 * to minimize the effort required to implement the interface.
 *
 * <p><strong>Example usage:</strong></p>
 * <pre><code>
 * import io.github.hhromic.uimr.concurrent.AbstractUIMRRunnable;
 *
 * public class MySmartRunnable extends AbstractUIMRRunnable {
 *    {@literal @}Override
 *     public void run() {
 *         System.out.println("I'm smart, my name is " + getName());
 *         try {
 *             // You can call skipCurrentSuspend() to continue without an exception
 *             suspendRunnable(10500.5);  // Nanosecond precision (if available)
 *         } catch (InterruptedException e) {
 *             System.out.println("Oops, I got interrupted.");
 *         }
 *     }
 * }
 * </code></pre>
 *
 * <p>For a complete use case, see the {@link UIMRExecutor} class example usage.</p>
 *
 * @author Hugo Hromic
 * @since 1.0
 * @see UIMRExecutor
 */
public abstract class AbstractUIMRRunnable implements UIMRRunnable {
    /** Name of this runnable. */
    protected final String name;

    /** Internal lock used for runnable suspending. */
    private final Lock suspendLock = new ReentrantLock();

    /** Internal condition used for runnable suspending. */
    private final Condition suspendStopWaiting = suspendLock.newCondition();

    /** Internal flag to keep waiting while suspending. */
    private boolean suspendKeepWaiting = false;

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public void skipCurrentSuspend() {
        suspendLock.lock();
        try {
            suspendKeepWaiting = false;
            suspendStopWaiting.signalAll();
        } finally {
            suspendLock.unlock();
        }
    }

    /**
      * Creates a new {@code UIMRRunnable} with the given name.
      *
      * @param name the name to assign to this runnable
      */
    public AbstractUIMRRunnable(final String name) {
        this.name = name;
    }

    /**
      * Suspend current execution of this runnable.
      *
      * <p>If available, this method has nanosecond precision for suspending.</p>
      *
      * @param millisecs the amount of time (in milliseconds) to suspend
      * @throws InterruptedException if the thread is interrupted during suspension
      */
    protected void suspendRunnable(final double millisecs) throws InterruptedException {
        suspendLock.lock();
        try {
            suspendKeepWaiting = true;
            while (suspendKeepWaiting)
                if (suspendStopWaiting.awaitNanos((long)(millisecs * 1000000)) <= 0)
                    suspendKeepWaiting = false;
        } finally {
            suspendLock.unlock();
        }
    }
}

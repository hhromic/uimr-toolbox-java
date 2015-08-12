package io.github.hhromic.uimr.concurrent;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * A fixed thread pool executor designed to handle {@code UIMRRunnable} instances.
 *
 * <p>Use this executor to run your UIMR Runnables. This class allows to properly run UIMR Runnables,
 * which are named. Also, this class offers an easy way to get running UIMR Runnables by name prefix searching.</p>
 *
 * <p><strong>Example usage:</strong></p>
 *
 * <pre><code>
 * import io.github.hhromic.uimr.concurrent.UIMRExecutor;
 * import io.github.hhromic.uimr.concurrent.AbstractUIMRRunnable;
 *
 * public class MyWorkerRunnable extends AbstractUIMRRunnable {
 *    {@literal @}Override
 *     public void run() {
 *         System.out.println("I'm a runnable!");
 *         try {
 *             suspendRunnable(5000);  // Make the thread sleep for some time
 *         } catch (InterruptedException e) {
 *             System.out.println("Oops, I got interrupted.");
 *         }
 *         System.out.println("Ok, I'm done!");
 *     }
 * }
 *
 * public class MyClass {
 *     private static UIMRExecutor executor = null;
 *     public static void main(final String[] args) {
 *         System.out.println("I'm the main thread!");
 *         executor = new UIMRExecutor(2);  // Number of threads to allocate
 *         executor.execute(new MyWorkerRunnable("myworker0"));
 *         executor.execute(new MyWorkerRunnable("myworker1"));
 *
 *         // Will match "myworker*" named runnables
 *         System.out.println(executor.getRunnables("myworker"));
 *     }
 * }
 * </code></pre>
 *
 * @author Hugo Hromic
 * @since 1.0
 * @see UIMRRunnable
 * @see AbstractUIMRRunnable
 * @see ThreadPoolExecutor
 */
public class UIMRExecutor extends ThreadPoolExecutor {
    /** Internal name-runnable mapping for this executor. */
    private final Map<String,UIMRRunnable> runnables = new HashMap<String,UIMRRunnable>();

    /** Internal runnable handler for this executor. */
    private class HandlerRunnable implements Runnable {
        private final String name;
        private final Runnable runnable;
        private final boolean keepAfterExecute;

        HandlerRunnable(final String name, final Runnable runnable, final boolean keepAfterExecute) {
            this.name = name;
            this.runnable = runnable;
            this.keepAfterExecute = keepAfterExecute;
        }

        public String getName() {
            return name;
        }

        public boolean getKeepAfterExecute() {
            return keepAfterExecute;
        }

        @Override
        public void run() {
            final String originalThreadName = Thread.currentThread().getName();
            try {
                Thread.currentThread().setName(name);
                runnable.run();
            } finally {
                Thread.currentThread().setName(originalThreadName);
            }
        }
    }

    /**
     * Creates a new {@code UIMRExecutor} with specified thread pool size.
     *
     * <p>Internally, this executor uses a {@code LinkedBlockingQueue} for handling the runnables.</p>
     *
     * @param poolSize the desired allocated thread pool size
     * @see ThreadPoolExecutor
     * @see LinkedBlockingQueue
     */
    public UIMRExecutor(final int poolSize) {
        super(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    /**
     * {@inheritDoc}
     *
     * <p>After a runnable terminates, remove it from the internal {@code UIMRRunnable} map (if applicable).</p>
     */
    @Override
    protected void afterExecute(final Runnable runnable, final Throwable throwable) {
        super.afterExecute(runnable, throwable);
        if (!(runnable instanceof HandlerRunnable)) return;

        final HandlerRunnable handlerRunnable = (HandlerRunnable)runnable;
        if (!handlerRunnable.getKeepAfterExecute())
            runnables.remove(handlerRunnable.getName());
    }

    /**
     * Executes a {@code UIMRRunnable} using this executor.
     *
     * <p>The {@code keepAfterExecute} flag allows to control if the runnable should be forgotten by this executor after finishing.</p>
     *
     * @param runnable the {@code UIMRRunnable} to execute
     * @param keepAfterExecute {@code false} if the runnable should be forgotten after finishing, {@code true} otherwise
     * @see UIMRRunnable
     */
    public void execute(final UIMRRunnable runnable, final boolean keepAfterExecute) {
        final String name = runnable.getName();
        runnables.put(name, runnable);
        super.execute(new HandlerRunnable(name, runnable, keepAfterExecute));
    }

    /**
     * Executes a {@code UIMRRunnable} using this executor.
     *
     * <p>This method calls {@code execute(UIMRRunnable,boolean)} with the {@code keepAfterExecute} parameter set to {@code false}.
     *
     * @param runnable the {@code UIMRRunnable} to execute
     * @see #execute(UIMRRunnable, boolean)
     */
    public void execute(final UIMRRunnable runnable) {
        execute(runnable, false);
    }

    /**
     * Executes a list of {@code UIMRRunnable}s and wait for all of them to finish.
     *
     * <p>This method calls {@code execute(UIMRRunnable)} and immediatelly after calls {@code ThreadPoolExecutor#shutdown()} to not
     * accept further tasks. Then, it waits for all runnables to finish and returns.</p>
     *
     * @param runnables the list of {@code UIMRRunnable}s to execute
     * @return {@code true} if all {@code UIMRRunnable}s finished normally or {@code false} if the wait was interrupted
     * @see #execute(UIMRRunnable)
     */
    public boolean executeAndAwaitTermination(final List<UIMRRunnable> runnables) {
        for (final UIMRRunnable runnable : runnables)
            execute(runnable);
        shutdown();
        while (!Thread.currentThread().isInterrupted())
            try {
                if (awaitTermination(1L, TimeUnit.MINUTES))
                    return true;
            } catch (InterruptedException e) {
                break;
            }
        return false;
    }

    /**
     * Gets the {@code UIMRRunnable} with the specified exact name from this executor's pool.
     *
     * @param name the name of the {@code UIMRRunnable} to get
     * @return the {@code UIMRRunnable}, or {@code null} if not found
     * @see UIMRRunnable
     */
    public UIMRRunnable getRunnable(final String name) {
        return runnables.get(name);
    }

    /**
     * Gets {@code UIMRRunnable} objects whose name matches the given prefix from this executor's pool.
     *
     * @param prefix the prefix to use for name matching
     * @return a list of {@code UIMRRunnable} objects matching the name prefix
     * @see UIMRRunnable
     */
    public List<UIMRRunnable> getRunnables(final String prefix) {
        final List<UIMRRunnable> results = new ArrayList<UIMRRunnable>();
        for (final Map.Entry<String,UIMRRunnable> entry : runnables.entrySet())
            if (entry.getKey().startsWith(prefix))
                results.add(entry.getValue());
        return results;
    }

    /**
     * Gets all {@code UIMRRunnable} objects from this executor's pool.
     *
     * @return a list of {@code UIMRRunnable} objects matching the name prefix
     * @see UIMRRunnable
     */
    public List<UIMRRunnable> getRunnables() {
        return new ArrayList<UIMRRunnable>(runnables.values());
    }
}

package io.github.hhromic.uimr.servlet;

import java.io.IOException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;

/**
 * Handles streaming text clients asynchronously with an included distribution thread.
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public class TextStreamingHttpServlet extends StreamingHttpServlet {
    public static final String EVENT_DELIMITER = "\r\n";

    /** Events blocking queue of this streaming text servlet. */
    private final BlockingQueue eventsQueue;

    /** Message distribution thread of this streaming text servlet. */
    private Thread distributionThread = new Thread(new DistributionRunnable());

    /** Distributes messages among connected clients. */
    private class DistributionRunnable implements Runnable {
        /** {@inheritDoc} */
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    final Object event = eventsQueue.take();
                    for (final AsyncContext asyncContext : asyncContextsQueue)
                        try {
                            final ServletResponse response = asyncContext.getResponse();
                            response.getWriter().print(event);
                            response.getWriter().print(EVENT_DELIMITER);
                            response.flushBuffer();
                        } catch (IOException e) {
                            asyncContextsQueue.remove(asyncContext);
                            asyncContext.complete();
                        }
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    /**
     * Creates a new {@code TextStreamingHttpServlet} with the specified content type,
     * character encoding and events blocking queue.
     *
     * @param contentType the content type to use with this streaming text servlet
     * @param characterEncoding the character encoding to use with this streaming text servlet
     * @param eventsQueue the events blocking queue to use with this streaming text servlet
     * @see BlockingQueue
     */
    public TextStreamingHttpServlet(final String contentType, final String characterEncoding, final BlockingQueue eventsQueue) {
        super(contentType, characterEncoding, new ConcurrentLinkedQueue<AsyncContext>());
        this.eventsQueue = eventsQueue;
    }

    /** {@inheritDoc} */
    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init();
        distributionThread.start();
    }

    /** {@inheritDoc} */
    @Override
    public void destroy() {
        super.destroy();
        if (distributionThread.isAlive()) {
            distributionThread.interrupt();
        }
    }
}

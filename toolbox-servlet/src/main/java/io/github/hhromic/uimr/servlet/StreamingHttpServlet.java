package io.github.hhromic.uimr.servlet;

import java.io.IOException;

import java.util.Queue;

import javax.servlet.annotation.WebServlet;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncListener;
import javax.servlet.AsyncEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles streaming clients asynchronously.
 *
 * @author Hugo Hromic
 * @since 4.0
 */
@WebServlet(asyncSupported=true, urlPatterns={"/"})
public class StreamingHttpServlet extends HttpServlet {
    /** Content type for this streaming servlet. */
    protected final String contentType;

    /** Character encoding for this streaming servlet. */
    protected final String characterEncoding;

    /** Async contexts queue of this streaming servlet. */
    protected final Queue<AsyncContext> asyncContextsQueue;

    /** Stream async listener of this streaming servlet. */
    private final StreamingAsyncListener streamingAsyncListener = new StreamingAsyncListener();

    /** Handles asynchronous events. */
    private class StreamingAsyncListener implements AsyncListener {
        /** {@inheritDoc}
         * <p>Unsubscribes the async context from the async context queue.</p>
         */
        @Override
        public void onComplete(final AsyncEvent event) throws IOException {
            asyncContextsQueue.remove(event.getAsyncContext());
        }

        /** {@inheritDoc}
         * <p>Unsubscribes the async context from the async context queue.</p>
         */
        @Override
        public void onTimeout(final AsyncEvent event) throws IOException {
            asyncContextsQueue.remove(event.getAsyncContext());
        }

        /** {@inheritDoc}
         * <p>Unsubscribes the async context from the async context queue.</p>
         */
        @Override
        public void onError(final AsyncEvent event) throws IOException {
            asyncContextsQueue.remove(event.getAsyncContext());
        }

        /** {@inheritDoc}
         * <p>Does nothing.</p>
         */
        @Override
        public void onStartAsync(final AsyncEvent event) throws IOException {
        }
    }

    /**
     * Creates a new {@code StreamingHttpServlet} with specified content type,
     * character encoding and async contexts queue.
     *
     * @param contentType the content type to use with this streaming servlet
     * @param characterEncoding the character encoding to use with this streaming servlet ({@code null} to not set)
     * @param asyncContextsQueue the async contexts queue to use with this streaming servlet
     * @see Queue
     */
    public StreamingHttpServlet(final String contentType, final String characterEncoding, final Queue<AsyncContext> asyncContextsQueue) {
        this.contentType = contentType;
        this.characterEncoding = characterEncoding;
        this.asyncContextsQueue = asyncContextsQueue;
    }

    /** {@inheritDoc} */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        // Async supported?
        if (!request.isAsyncSupported()) {
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, "This servlet requires async support from server.");
            return;
        }

        // Initialize the response
        response.setContentType(contentType);
        if (characterEncoding != null)
            response.setCharacterEncoding(characterEncoding);
        response.setStatus(HttpServletResponse.SC_OK);

        // Disable client caching
        response.setHeader("Cache-Control", "no-cache, must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        // Flush response buffer
        response.flushBuffer();

        // Initialize async context and subscribe it to queue
        final AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(0);
        asyncContext.addListener(streamingAsyncListener);
        asyncContextsQueue.add(asyncContext);
    }

    /** {@inheritDoc} */
    @Override
    public void destroy() {
        asyncContextsQueue.clear();
    }
}

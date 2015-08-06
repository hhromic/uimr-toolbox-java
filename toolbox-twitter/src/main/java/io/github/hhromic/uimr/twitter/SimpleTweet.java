package io.github.hhromic.uimr.twitter;

import io.github.hhromic.uimr.Timestamp;
import io.github.hhromic.uimr.SafeSimpleDateFormat;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import java.text.ParseException;

/**
 * A handy Tweet representation class.
 *
 * <p><strong>Example usage:</strong></p>
 *
 * <pre>
 * import io.github.hhromic.uimr.twitter.SimpleTweet;
 * import io.github.hhromic.uimr.twitter.SimpleUser;
 *
 * public class MyClass {
 *     public static void main(final String[] args) {
 *         final SimpleUser user = new SimpleUser(5678, "testauthor");
 *         final SimpleTweet tweet = new SimpleTweet(1234, 1305469800000, user, "hello!!");
 *         tweet.setText("@Bob: #this is an #example @tweet! http://t.co/abc123 via @Alice");
 *         System.out.println(tweet);
 *         System.out.println(tweet.formatNeo4J());
 *     }
 * }
 * </pre>
 *
 * @author Hugo Hromic
 * @since 1.0
 * @see SimpleUser
 */
public class SimpleTweet implements Comparable<SimpleTweet> {
    /** Field separator for Neo4J formatting. <p>Value: {@code ;} (semi-colon).</p> */
    public static final String NEO4J_FIELD_SEP = ";";

    /** Field list separator for Neo4J formatting. <p>Value: {@code ,} (comma).</p> */
    public static final String NEO4J_LIST_SEP = ",";

    /**
     * Date format for Twitter.
     *
     * <p>Defined as: {@code new SafeSimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy")}</p>
     *
     * @see java.util.text.SimpleDateFormat documentation for date formats.
     * @see SafeSimpleDateFormat
     */
    public static final SafeSimpleDateFormat TWITTER_DATE_FORMAT = new SafeSimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy");

    // Internal fields
    protected Long id;
    protected Long timestamp;
    protected SimpleUser user;
    protected String text;
    protected Map<String,Object> reply;
    protected List<Map<String,Object>> mentions;
    protected List<Map<String,Object>> hashtags;
    protected List<Map<String,Object>> urls;
    protected List<Map<String,Object>> rtOrigins;

    /**
     * Gets the ID of this Tweet.
     *
     * @return the ID of this Tweet.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of this Tweet.
     *
     * @param id the ID to set.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the timestamp ({@code long}) of this Tweet.
     *
     * @return the timestamp ({@code long}) of this Tweet.
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp ({@code long}) of this Tweet.
     *
     * @param timestamp the timestamp ({@code long}) to set.
     */
    public void setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the user (author) of this Tweet.
     *
     * @return the user (author) of this Tweet.
     */
    public SimpleUser getUser() {
        return user;
    }

    /**
     * Sets the user (author) of this Tweet.
     *
     * @param user the user (author) to set.
     */
    public void setUser(final SimpleUser user) {
        this.user = user;
    }

    /**
     * Gets the text content of this Tweet.
     *
     * @return the text content of this Tweet.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text content of this Tweet.
     *
     * <p>This method also triggers population of this Tweet's entities private fields.</p>
     *
     * @param text the text content to set.
     * @see EntitiesExtractor
     */
    public void setText(final String text) {
        this.text = text;
        reply = EntitiesExtractor.getReply(text);
        mentions = EntitiesExtractor.getMentions(text);
        hashtags = EntitiesExtractor.getHashTags(text);
        urls = EntitiesExtractor.getURLs(text);
        rtOrigins = EntitiesExtractor.getRTOrigins(text);
    }

    /**
     * Gets the reply entity of this Tweet.
     *
     * @return the reply entity of this Tweet or {@code null} if not found.
     * @see EntitiesExtractor
     */
    public Map<String,Object> getReply() {
        return reply;
    }

    /**
     * Gets the mention entities of this Tweet.
     *
     * @return the list of mention entities of this Tweet.
     * @see EntitiesExtractor
     */
    public List<Map<String,Object>> getMentions() {
        return mentions;
    }

    /**
     * Gets the hashtag entities of this Tweet.
     *
     * @return the list of hashtag entities of this Tweet.
     * @see EntitiesExtractor
     */
    public List<Map<String,Object>> getHashTags() {
        return hashtags;
    }

    /**
     * Gets the URL entities of this Tweet.
     *
     * @return the list of URL entities of this Tweet.
     * @see EntitiesExtractor
     */
    public List<Map<String,Object>> getURLs() {
        return urls;
    }

    /**
     * Gets the retweet origin entities of this Tweet.
     *
     * @return the list of retweet origin entities of this Tweet.
     * @see EntitiesExtractor
     */
    public List<Map<String,Object>> getRTOrigins() {
        return rtOrigins;
    }

    /**
     * Creates a simple Tweet instance.
     *
     * <p>This method also triggers population of this Tweet's entities private fields.</p>
     *
     * @param id the ID of this Tweet.
     * @param timestamp the timestamp ({@code long}) of this Tweet.
     * @param user the user (author) of this Tweet.
     * @param text the text content of this Tweet.
     * @see EntitiesExtractor
     */
    public SimpleTweet(final Long id, final Long timestamp, final SimpleUser user, final String text) {
        setId(id);
        setTimestamp(timestamp);
        setUser(user);
        setText(text);
    }

    /**
     * Format this simple tweet into Neo4J TwitterPlugin representation.
     *
     * <p>The format used is: {@code AUTHOR;TIMESTAMP;REPLY;MENTIONS;HASHTAGS;RT_ORIGINS}</p>
     *
     * @return the string format of this Tweet to push into Neo4J Twitter Plugin.
     */
    public String formatNeo4J() {
        final StringBuilder output = new StringBuilder();
        output.append(user.getScreenName() + NEO4J_FIELD_SEP);
        output.append(Timestamp.toString(this.timestamp) + NEO4J_FIELD_SEP);

        if (reply != null)
            output.append((String)reply.get("text"));
        output.append(NEO4J_FIELD_SEP);

        for (final List list : new List[]{mentions, hashtags,rtOrigins }) {
            for (final Object item : list)
                output.append((String)((Map)item).get("text") + NEO4J_LIST_SEP);
            if (list.size() != 0)
                output.deleteCharAt(output.length() - 1);
            output.append(NEO4J_FIELD_SEP);
        }
        output.deleteCharAt(output.length() - 1);

        return output.toString();
    }

    /**
     * Parses a Twitter date into a timestamp ({@code long}).
     *
     * @param string the string to parse into a Twitter date.
     * @return the parsed timestamp ({@code long}).
     * @see #TWITTER_DATE_FORMAT
     */
    public static long parseTwitterDate(final String string) throws ParseException {
        return Timestamp.fromString(string, TWITTER_DATE_FORMAT);
    }

    /**
     * Formats a timestamp ({@code long}) into a Twitter date.
     *
     * @param timestamp the timestamp ({@code long}) to format into a Twitter date.
     * @return the formatted Twitter date.
     * @see #TWITTER_DATE_FORMAT
     */
    public static String formatTwitterDate(final long timestamp) {
        return Timestamp.toString(timestamp, TWITTER_DATE_FORMAT);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("<SimpleTweet @ %s from '%s': '%s'>",  Timestamp.toString(timestamp), user, text);
    }

    /** {@inheritDoc}
     *
     * <p>The comparison is done using the {@code timestamp} field.</p>
     */
    @Override
    public int compareTo(SimpleTweet tweet) {
        return timestamp.compareTo(tweet.timestamp);
    }

    /** {@inheritDoc}
     *
     * <p>The simple tweet objects are compared according to their {@code id} field.</p>
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof SimpleTweet)) return false;
        final SimpleTweet otherSimpleTweet = (SimpleTweet)obj;
        return id != null && id.equals(otherSimpleTweet.id);
    }

    /** {@inheritDoc}
     *
     * <p>The hash code is generated using this simple tweet {@code id} field.</p>
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}

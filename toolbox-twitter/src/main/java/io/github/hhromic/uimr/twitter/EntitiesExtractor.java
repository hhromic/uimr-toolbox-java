package io.github.hhromic.uimr.twitter;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Twitter entities extraction class.
 *
 * <p>This class helps extracting Tweet entities from textual content. The entities handled
 *  are {@code reply}, {@code mentions}, {@code hashtags}, {@code URLs} and
 *  {@code retweet origins}.</p>
 *
 * <p>All extracted entities include the {@code start} and {@code end} indices where they
 *  were found in the text.</p>
 *
 * <p><strong>Example usage:</strong></p>
 *
 * <pre>
 * import io.github.hhromic.uimr.twitter.EntitiesExtractor;
 *
 * public class MyClass {
 *     public static String TEXT = "@Bob: #this is an #example @tweet! http://t.co/abc123 via @Alice";
 *     public static void main(final String[] args) {
 *         System.out.println(EntitiesExtractor.getReply(TEXT));
 *         System.out.println(EntitiesExtractor.getMentions(TEXT));
 *         System.out.println(EntitiesExtractor.getHashTags(TEXT));
 *         System.out.println(EntitiesExtractor.getURLs(TEXT));
 *         System.out.println(EntitiesExtractor.getRTOrigins(TEXT));
 *     }
 * }
 * </pre>
 *
 * @author Hugo Hromic
 * @since 1.0
 */
public class EntitiesExtractor {
    /**
     * Regular expression for {@code reply} entity.
     *
     * <p>Pattern: {@code ^@(\\w+)}</p>
     *
     * @see Pattern
     */
    public static final Pattern REPLY = Pattern.compile("^@(\\w+)");

    /**
     * Regular expression for {@code mention} entities.
     *
     * <p>Pattern: {@code (?>^|\\s)@(\\w+)}</p>
     *
     * @see Pattern
     */
    public static final Pattern MENTIONS = Pattern.compile("(?>^|\\s)@(\\w+)");

    /**
     * Regular expression for {@code hashtag} entities.
     *
     * <p>Pattern: {@code (?>^|\\s)#(\\w+)}</p>
     *
     * @see Pattern
     */
    public static final Pattern HASHTAGS = Pattern.compile("(?>^|\\s)#(\\w+)");

    /**
     * Regular expression for {@code URL} entities.
     *
     * <p>Pattern (case insensitive): {@code ((https?:\\/\\/([-\\w\\.]+)+(\\/([\\w/_\\.]*(\\?\\S+)?(#\\S+)?)?)?))}</p>
     *
     * @see Pattern
     */
    public static final Pattern URLS = Pattern.compile("((https?:\\/\\/([-\\w\\.]+)+(\\/([\\w/_\\.]*(\\?\\S+)?(#\\S+)?)?)?))", Pattern.CASE_INSENSITIVE);

    /**
     * Regular expression for {@code retweet origin} entities.
     *
     * <p>Pattern (case insensitive): {@code (?>RT|via)(?>\\b\\W*@(\\w+)+)}</p>
     *
     * @see Pattern
     */
    public static final Pattern RTORIGINS = Pattern.compile("(?>RT|via)(?>\\b\\W*@(\\w+)+)", Pattern.CASE_INSENSITIVE);

    /**
     * Internal pattern matching method.
     *
     * @param pattern the {@code Pattern} to use for matching.
     * @param group the group name within the {@code Pattern} to use for matching.
     * @param string the string to match against.
     * @return the list of matched texts.
     * @see Pattern
     */
    private static List<Map<String,Object>> getPattern(final Pattern pattern, final String string) {
        final List<Map<String,Object>> matches = new ArrayList<Map<String,Object>>();
        final Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            final Map<String,Object> match = new HashMap<String,Object>();
            final List<Integer> indices = new ArrayList<Integer>();
            indices.add(matcher.start());
            indices.add(matcher.end());
            match.put("text", matcher.group(1));
            match.put("indices", indices);
            matches.add(match);
        }
        return matches;
    }

    /**
     * Gets the reply entity from a given text.
     *
     * @param string the text to extract entity from.
     * @return the reply entity or {@code null} if not found.
     */
    public static Map<String,Object> getReply(final String string) {
        final List<Map<String,Object>> reply = getPattern(REPLY, string);
        if (reply.isEmpty())
            return null;
        return reply.get(0);
    }

    /**
     * Gets the mention entities from a given text.
     *
     * @param string the text to extract entities from.
     * @return the list of found mention entities.
     */
    public static List<Map<String,Object>> getMentions(final String string) {
        return getPattern(MENTIONS, string);
    }

    /**
     * Gets the hashtags entities from a given text.
     *
     * @param string the text to extract entities from.
     * @return the list of found hashtag entities.
     */
    public static List<Map<String,Object>> getHashTags(final String string) {
        return getPattern(HASHTAGS, string);
    }

    /**
     * Gets the URL entities from a given text.
     *
     * @param string the text to extract entities from.
     * @return the list of found URL entities.
     */
    public static List<Map<String,Object>> getURLs(final String string) {
        return getPattern(URLS, string);
    }

    /**
     * Gets the retweet origin entities from a given text.
     *
     * @param string the text to extract entities from.
     * @return the list found retweet origin entities.
     */
    public static List<Map<String,Object>> getRTOrigins(final String string) {
        return getPattern(RTORIGINS, string);
    }
}

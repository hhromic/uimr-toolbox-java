package io.github.hhromic.uimr.twitter;

import java.util.Set;
import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.Collections;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * Twitter Filter emulation class.
 *
 * <p>This class emulates the Twitter statuses/filter Streaming API endpoint.</p>
 *
 * <p><strong>Example usage:</strong></p>
 *
 * <pre>{@code
 * import io.github.hhromic.uimr.twitter.TwitterFilter;
 *
 * public class MyClass {
 *     public static void main(final String[] args) {
 *         final Tweet tweet = new Tweet();
 *         final TwitterFilter filter = new TwitterFilter();
 *         filter.setTrack(new String[]{"love", "#heart"});
 *         filter.setFollow(new long[]{51453467});
 *         if (filter.matches(tweet))
 *             System.out.println("Tweet matches Twitter Filter.");
 *     }
 * }
 * }</pre>
 *
 * @since 4.0
 */
public class TwitterFilter {
    /** Regular expression to split Tweet content into terms. */
    public static final Pattern RE_SPLIT_TERM = Pattern.compile("\\s|[@#\\:\\!\\'\"\\$\\%\\&\\(\\)\\*\\+\\,\\-\\.\\/\\:\\;<=>\\?\\[\\\\\\]\\^\\{\\|\\}~]|[\u2000-\u206f]|[\u0080-\u00ff]");

    /** Regular expression to split track parameter phrases. */
    public static final Pattern RE_SPLIT_PHRASE = Pattern.compile("\\s");

    /** This Twitter Filter's track parameter. */
    protected Set<String> track = null;

    /** This Twitter Filter's follow parameter. */
    protected Set<Long> follow = null;

    /**
     * Set this Twitter Filter's track parameter.
     *
     * @param track the track set to use
     */
    public void setTrack(final Set<String> track) {
        this.track = new HashSet<String>();
        for (final String term : track)
            this.track.add(term.toLowerCase());
    }

    /**
     * Set this Twitter Filter's follow parameter.
     *
     * @param follow the follow set to use
     */
    public void setFollow(final Set<Long> follow) {
        this.follow = follow;
    }

    /**
     * Evaluates if the given Tweet matches this Twitter Filter.
     *
     * @param tweet the Tweet to evaluate
     * @return {@code true} if the Tweet matches, {@code false} otherwise
     */
    public boolean matches(final ObjectNode tweet) {
        if (followMatches(tweet) || trackMatches(tweet))
            return true;
        return false;
    }

    /**
     * Evaluates if the given Tweet matches at least this Twitter Filter's track parameter.
     *
     * @param tweet the Tweet to evaluate
     * @return {@code true} if the Tweet matches the track parameter, {@code false} otherwise
     */
    public boolean trackMatches(final ObjectNode tweet) {
        if (track == null || track.isEmpty()) return false;

        // At least one track phrase matches?
        final Set<String> tweetTerms = buildTerms(tweet);
        final Set<String> phraseTerms = new HashSet<String>();
        for (final String phrase : track) {
            phraseTerms.clear();
            Collections.addAll(phraseTerms, RE_SPLIT_PHRASE.split(phrase, -1));
            if (tweetTerms.containsAll(phraseTerms))
                return true;
        }

        // Tweet does not match track
        return false;
    }

    /**
     * Computes terms (mentions, hashtags, tokenized text) contained in a Tweet.
     *
     * @param tweet the Tweet to get the terms from
     * @return a set of terms from the Tweet
     */
    public Set<String> buildTerms(final ObjectNode tweet) {
        final Set<String> tweetTerms = new HashSet<String>();

        // Add tweet text terms
        Collections.addAll(tweetTerms, RE_SPLIT_TERM.split(tweet.path("text").asText().toLowerCase(), -1));

        // Extract Tweet entities
        final ArrayNode hashtags = (ArrayNode)tweet.path("entities").path("hashtags");
        final ArrayNode mentions = (ArrayNode)tweet.path("entities").path("user_mentions");

        // Add content from Retweet (if any)
        if (tweet.has("retweeted_status")) {
            mentions.addAll((ArrayNode)tweet.path("retweeted_status").path("entities").path("user_mentions"));
            hashtags.addAll((ArrayNode)tweet.path("retweeted_status").path("entities").path("hashtags"));
            Collections.addAll(tweetTerms, RE_SPLIT_TERM.split(tweet.path("retweeted_status").path("text").asText().toLowerCase(), -1));
        }

        // Add mentions and hashtags
        for (final JsonNode mention : mentions )
            Collections.addAll(tweetTerms, mention.path("screen_name").asText().toLowerCase());
        for (final JsonNode hashtag : hashtags) {
            Collections.addAll(tweetTerms, hashtag.path("text").asText().toLowerCase());
            Collections.addAll(tweetTerms, '#' + hashtag.path("text").asText().toLowerCase());
        }

        // Return all found terms
        return tweetTerms;
    }

    /**
     * Evaluates if the given Tweet matches at least this Twitter Filter's follow parameter.
     *
     * @param tweet the Tweet to evaluate
     * @return {@code true} if the Tweet matches the follow parameter, {@code false} otherwise
     */
    public boolean followMatches(final ObjectNode tweet) {
        if (follow == null || follow.isEmpty()) return false;

        // Author matches?
        if (follow.contains(tweet.path("user").path("id").asLong()))
            return true;

        // Replied user matches?
        if (tweet.path("in_reply_to_user_id").isLong() &&
            follow.contains(tweet.path("in_reply_to_user_id").asLong()))
            return true;

        // Retweted user matches?
        if (tweet.has("retweeted_status") &&
            follow.contains(tweet.path("retweeted_status").path("user").path("id").asLong()))
                return true;

        // Tweet does not match follow
        return false;
    }
}

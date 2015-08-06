package io.github.hhromic.uimr.twitter;

import junit.framework.TestCase;

public class EntitiesExtractorTest extends TestCase {
    public static final String[] TWEETS = new String[] {
        "just voted @ZacharyLevi for \"Actor of the Month\" *VOTE* http://faxo.com/t",
        "#3 Ways To Recover From a #Thanksgiving Day Gorge! http://thehealthyapron.com",
        "@chrisrstrong b/c you weren't out fistfighting over flat sc#reen tvs, duh.",
        "RT @_lesantos: Quero esse Kit Natura Una que o Glamouragem est\u00E1 sorteando http://bit.ly/dsVbwj",
        "RT @kanguoficial: O video #4hermanas2009 esta c/9980 visualiza\u00E7\u00F5es se pas#sar de 10 mil ate as 15hs sortearemos+1cortesia.Da #RT e assiste! http://kingo.to/mK2"
    };

    public EntitiesExtractorTest(final String name) {
        super(name);
    }

    public void testEntitiesExtractor() throws Exception {
        for (final String tweet : TWEETS) {
            System.out.println("Tweet     : " + tweet);
            System.out.println("Reply     : " + EntitiesExtractor.getReply(tweet));
            System.out.println("Mentions  : " + EntitiesExtractor.getMentions(tweet));
            System.out.println("HashTags  : " + EntitiesExtractor.getHashTags(tweet));
            System.out.println("URLs      : " + EntitiesExtractor.getURLs(tweet));
            System.out.println("RTOrigins : " + EntitiesExtractor.getRTOrigins(tweet));
            System.out.println();
        }
    }
}

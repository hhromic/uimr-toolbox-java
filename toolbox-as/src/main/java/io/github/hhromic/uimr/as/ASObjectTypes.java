package io.github.hhromic.uimr.as;

/**
 * <p>This class contains base Active Streams Objects Types definitions.</p>
 *
 * <p>The definitions come from the Activity Streams <a href="http://activitystrea.ms">website</a>.</p>
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public class ASObjectTypes {
    /**
     * <p>The "activity" object type indicates that an Object represents an Activity. It can be used whenever an event involving another activity is described within an Activity Stream. For instance, when one user "shares" or "reposts" an activity from another user.</p>
     */
    public static final String ACTIVITY = "activity";

    /**
     * <p>Represents any kind of significant notification.</p>
     */
    public static final String ALERT = "alert";

    /**
     * <p>Represents any kind of software application.</p>
     */
    public static final String APPLICATION = "application";

    /**
     * <p>Represents objects such as news articles, knowledge base entries, or other similar construct. Such objects generally consist of paragraphs of text, in some cases incorporating embedded media such as photos and inline hyperlinks to other resources.</p>
     */
    public static final String ARTICLE = "article";

    /**
     * <p>Represents audio content of any kind. Objects of this type MAY contain an additional property as specified in Section 3.1.</p>
     */
    public static final String AUDIO = "audio";

    /**
     * <p>Represents a badge or award granted to an object (typically a person object)</p>
     */
    public static final String BADGE = "badge";

    /**
     * <p>Objects of this type are used to carry arbirary Base64-encoded binary data within an Activity Stream object. It is primarily intended to attach binary data to other types of objects through the use of the attachments property. Objects of this type will contain the additional properties specified in Section 3.2.</p>
     */
    public static final String BINARY = "binary";

    /**
     * <p>Represents a pointer to some URL -- typically a web page. In most cases, bookmarks are specific to a given user and contain metadata chosen by that user. Bookmark Objects are similar in principle to the concept of bookmarks or favorites in a web browser. A bookmark represents a pointer to the URL, not the URL or the associated resource itself. Objects of this type SHOULD contain an additional targetUrl property whose value is a String containing the IRI of the target of the bookmark.</p>
     */
    public static final String BOOKMARK = "bookmark";

    /**
     * <p>Represents a generic collection of objects of any type. This object type can be used, for instance, to represent a collection of files like a folder; a collection of photos like an album; and so forth. Objects of this type MAY contain an additional objectTypes property whose value is an Array of Strings specifying the expected objectType of objects contained within the collection.</p>
     */
    public static final String COLLECTION = "collection";

    /**
     * <p>Represents a textual response to another object. Objects of this type MAY contain an additional inReplyTo property whose value is an Array of one or more other Activity Stream Objects for which the object is to be considered a response.</p>
     */
    public static final String COMMENT = "comment";

    /**
     * <p>Represents a device of any sort.</p>
     */
    public static final String DEVICE = "device";

    /**
     * <p>Represents an event that occurs at a certain location during a particular period of time. Objects of this type MAY contain the additional properties specified in Section 3.3.</p>
     */
    public static final String EVENT = "event";

    /**
     * <p>Represents any form of document or file. Objects of this type MAY contain an additional fileUrl property whose value a dereferenceable IRI that can be used to retrieve the file; and an additional mimeType property whose value is the MIME type of the file described by the object.</p>
     */
    public static final String FILE = "file";

    /**
     * <p>Represents a game or competition of any kind.</p>
     */
    public static final String GAME = "game";

    /**
     * <p>Represents a grouping of objects in which member objects can join or leave.</p>
     */
    public static final String GROUP = "group";

    /**
     * <p>Represents a graphical image. Objects of this type MAY contain an additional fullImage property whose value is an Activity Streams Media Link to a "full-sized" representation of the image.</p>
     */
    public static final String IMAGE = "image";

    /**
     * <p>Represents a report about a problem or situation that needs to be resolved. For instance, the issue object can be used to represent reports detailing software defects, or reports of acceptable use violations, and so forth. Objects of this type MAY contain the additional properties specified in Section 3.4.</p>
     */
    public static final String ISSUE = "issue";

    /**
     * <p>Represents information about a job or a job posting.</p>
     */
    public static final String JOB = "job";

    /**
     * <p>Represents a short-form text message. This object is intended primarily for use in "micro-blogging" scenarios and in systems where users are invited to publish short, often plain-text messages whose useful lifespan is generally shorter than that of an article of weblog entry. A note is similar in structure to an article, but typically does not have a title or distinct paragraphs and tends to be much shorter in length.</p>
     */
    public static final String NOTE = "note";

    /**
     * <p>Represents an offer of any kind.</p>
     */
    public static final String OFFER = "offer";

    /**
     * <p>Represents an organization of any kind.</p>
     */
    public static final String ORGANIZATION = "organization";

    /**
     * <p>Represents an area, typically a web page, that is representative of, and generally managed by a particular entity. Such areas are usually dedicated to displaying descriptive information about the entity and showcasing recent content such as articles, photographs and videos. Most social networking applications, for example, provide individual users with their own dedicated "profile" pages. Several allow similar types of pages to be created for commercial entities, organizations or events. While the specific details of how pages are implemented, their characteristics and use may vary, the one unifying property is that they are typically "owned" by a single entity that is represented by the content provided by the page itself.</p>
     */
    public static final String PAGE = "page";

    /**
     * <p>Represents an individual person.</p>
     */
    public static final String PERSON = "person";

    /**
     * <p>Represents a physical location. Locations can be represented using geographic coordinates, a physical address, a free-form location name, or any combination of these. Objects of this type MAY contain the additional properties specified in Section 3.5.</p>
     */
    public static final String PLACE = "place";

    /**
     * <p>Represents any form of process. For instance, a long-running task that is started and expected to continue operating for a period of time.</p>
     */
    public static final String PROCESS = "process";

    /**
     * <p>Represents a commercial good or service. Objects of this type MAY contain an additional fullImage property whose value is an Activity Streams Media Link to an image resource representative of the product.</p>
     */
    public static final String PRODUCT = "product";

    /**
     * <p>Represents a question or a poll. Objects of this type MAY contain an additional options property whose value is an Array of possible answers to the question in the form of Activity Stream objects of any type.</p>
     */
    public static final String QUESTION = "question";

    /**
     * <p>Represents a primarily prose-based commentary on another object. Objects of this type MAY contain a rating property as specified in Section 4.4.</p>
     */
    public static final String REVIEW = "review";

    /**
     * <p>Represents any form of hosted or consumable service that performs some kind of work or benefit for other entities. Examples of such objects include websites, businesses, etc.</p>
     */
    public static final String SERVICE = "service";

    /**
     * <p>Represents an activity that has yet to be completed. Objects of this type can contain additional properties as specified in Section 3.6.</p>
     */
    public static final String TASK = "task";

    /**
     * <p>Represents video content of any kind. Objects of this type MAY contain additional properties as specified in Section 3.1.</p>
     */
    public static final String VIDEO = "video";
}

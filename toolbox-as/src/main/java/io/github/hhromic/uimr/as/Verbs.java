package io.github.hhromic.uimr.as;

/**
 * <p>This class contains base Active Streams Activity Verbs definitions.</p>
 *
 * <p>The definitions come from the Activity Streams <a href="http://activitystrea.ms">website</a>.</p>
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public class Verbs {
    /**
     * <p>The "post" verb describes the act of authoring an object and then publishing it online. The actor can be any entity;
     * the object can be of any object type; and the target, if specified, can be of any object type. A target, however, is not required.</p>
     */
    public static final String POST = "post";

    /**
     * <p>Indicates that that the actor has accepted the object. For instance, a person accepting an award, or accepting an assignment.</p>
     */
    public static final String ACCEPT = "accept";

    /**
     * <p>Indicates that the actor has accessed the object. For instance, a person accessing a room, or accessing a file.</p>
     */
    public static final String ACCESS = "access";

    /**
     * <p>Indicates that the actor has acknowledged the object. This effectively signals that the actor is aware of the object's existence.</p>
     */
    public static final String ACKNOWLEDGE = "acknowledge";

    /**
     * <p>Indicates that the actor has added the object to the target. For instance, adding a photo to an album.</p>
     */
    public static final String ADD = "add";

    /**
     * <p>Indicates that the actor agrees with the object. For example, a person agreeing with an argument, or expressing agreement with a particular issue.</p>
     */
    public static final String AGREE = "agree";

    /**
     * <p>Indicates that the actor has appended the object to the target. For instance, a person appending a new record to a database.</p>
     */
    public static final String APPEND = "append";

    /**
     * <p>Indicates that the actor has approved the object. For instance, a manager might approve a travel request.</p>
     */
    public static final String APPROVE = "approve";

    /**
     * <p>Indicates that the actor has archived the object.</p>
     */
    public static final String ARCHIVE = "archive";

    /**
     * <p>Indicates that the actor has assigned the object to the target.</p>
     */
    public static final String ASSIGN = "assign";

    /**
     * <p>Indicates that the actor is currently located at the object. For instance, a person being at a specific physical location.</p>
     */
    public static final String AT = "at";

    /**
     * <p>Indicates that the actor has attached the object to the target. For instance, a person attaching a file to a wiki page or an email.</p>
     */
    public static final String ATTACH = "attach";

    /**
     * <p>Indicates that the actor has attended the object. For instance, a person attending a meeting.</p>
     */
    public static final String ATTEND = "attend";

    /**
     * <p>Indicates that the actor has authored the object. Note that this is a more specific form of the verb "create".</p>
     */
    public static final String AUTHOR = "author";

    /**
     * <p>Indicates that the actor has authorized the object. If a target is specified, it means that the authorization is specifically in regards to the target. For instance, a service can authorize a person to access a given application; in which case the actor is the service, the object is the person, and the target is the application. In contrast, a person can authorize a request; in which case the actor is the person and the object is the request and there might be no explicit target.</p>
     */
    public static final String AUTHORIZE = "authorize";

    /**
     * <p>Indicates that the actor has borrowed the object. If a target is specified, it identifies the entity from which the object was borrowed. For instance, if a person borrows a book from a library, the person is the actor, the book is the object and the library is the target.</p>
     */
    public static final String BORROW = "borrow";

    /**
     * <p>Indicates that the actor has built the object. For example, if a person builds a model or compiles code.</p>
     */
    public static final String BUILD = "build";

    /**
     * <p>Indicates that the actor has canceled the object. For instance, canceling a calendar event.</p>
     */
    public static final String CANCEL = "cancel";

    /**
     * <p>Indicates that the actor has closed the object. For instance, the object could represent a ticket being tracked in an issue management system.</p>
     */
    public static final String CLOSE = "close";

    /**
     * <p>Indicates that the actor has completed the object.</p>
     */
    public static final String COMPLETE = "complete";

    /**
     * <p>Indicates that the actor has confirmed or agrees with the object. For instance, a software developer might confirm an issue reported against a product.</p>
     */
    public static final String CONFIRM = "confirm";

    /**
     * <p>Indicates that the actor has consumed the object. The specific meaning is dependent largely on the object's type. For instance, an actor may "consume" an audio object, indicating that the actor has listened to it; or an actor may "consume" a book, indicating that the book has been read. As such, the "consume" verb is a more generic form of other more specific verbs such as "read" and "play".</p>
     */
    public static final String CONSUME = "consume";

    /**
     * <p>Indicates that the actor has checked-in to the object. For instance, a person checking-in to a Place.</p>
     */
    public static final String CHECKIN = "checkin";

    /**
     * <p>Indicates that the actor has created the object.</p>
     */
    public static final String CREATE = "create";

    /**
     * <p>Indicates that the actor has deleted the object. This implies, but does not require, the permanent destruction of the object.</p>
     */
    public static final String DELETE = "delete";

    /**
     * <p>Indicates that the actor has delivered the object. For example, delivering a package.</p>
     */
    public static final String DELIVER = "deliver";

    /**
     * <p>Indicates that the actor has denied the object. For example, a manager may deny a travel request.</p>
     */
    public static final String DENY = "deny";

    /**
     * <p>Indicates that the actor disagrees with the object.</p>
     */
    public static final String DISAGREE = "disagree";

    /**
     * <p>Indicates that the actor dislikes the object. Note that the "dislike" verb is distinct from the "unlike" verb which assumes that the object had been previously "liked".</p>
     */
    public static final String DISLIKE = "dislike";

    /**
     * <p>Indicates that the actor has experienced the object in some manner. Note that, depending on the specific object types used for both the actor and object, the meaning of this verb can overlap that of the "consume" and "play" verbs. For instance, a person might "experience" a movie; or "play" the movie; or "consume" the movie. The "experience" verb can be considered a more generic form of other more specific verbs as "consume", "play", "watch", "listen", and "read"</p>
     */
    public static final String EXPERIENCE = "experience";

    /**
     * <p>Indicates that the actor marked the object as an item of special interest.</p>
     */
    public static final String FAVORITE = "favorite";

    /**
     * <p>Indicates that the actor has found the object.</p>
     */
    public static final String FIND = "find";

    /**
     * <p>Indicates that the actor has flagged the object as being inappropriate for some reason. When using this verb, the context property, as specified within Section 4.1 can be used to provide additional detail about why the object has been flagged.</p>
     */
    public static final String FLAG_AS_INAPPROPRIATE = "flag-as-inappropriate";

    /**
     * <p>Indicates that the actor began following the activity of the object. In most cases, the objectType will be a "person", but it can potentially be of any type that can sensibly generate activity. Processors MAY ignore (silently drop) successive identical "follow" activities.</p>
     */
    public static final String FOLLOW = "follow";

    /**
     * <p>Indicates that the actor is giving an object to the target. Examples include one person giving a badge object to another person. The object identifies the object being given. The target identifies the receiver.</p>
     */
    public static final String GIVE = "give";

    /**
     * <p>Indicates that the actor is hosting the object. As in hosting an event, or hosting a service.</p>
     */
    public static final String HOST = "host";

    /**
     * <p>Indicates that the actor has ignored the object. For instance, this verb may be used when an actor has ignored a friend request, in which case the object may be the request-friend activity.</p>
     */
    public static final String IGNORE = "ignore";

    /**
     * <p>Indicates that the actor has inserted the object into the target.</p>
     */
    public static final String INSERT = "insert";

    /**
     * <p>Indicates that the actor has installed the object, as in installing an application.</p>
     */
    public static final String INSTALL = "install";

    /**
     * <p>Indicates that the actor has interacted with the object. For instance, when one person interacts with another.</p>
     */
    public static final String INTERACT = "interact";

    /**
     * <p>Indicates that the actor has invited the object, typically a person object, to join or participate in the object described by the target. The target could, for instance, be an event, group or a service.</p>
     */
    public static final String INVITE = "invite";

    /**
     * <p>Indicates that the actor has become a member of the object. This specification only defines the meaning of this verb when the object of the Activity has an objectType of group, though implementors need to be prepared to handle other types of objects.</p>
     */
    public static final String JOIN = "join";

    /**
     * <p>Indicates that the actor has left the object. For instance, a Person leaving a Group or checking-out of a Place.</p>
     */
    public static final String LEAVE = "leave";

    /**
     * <p>Indicates that the actor marked the object as an item of special interest. The "like" verb is considered to be an alias of "favorite". The two verb are semantically identical.</p>
     */
    public static final String LIKE = "like";

    /**
     * <p>Indicates that the actor has listened to the object. This is typically only applicable for objects representing audio content, such as music, an audio-book, or a radio broadcast. The "listen" verb is a more specific form of the "consume", "experience" and "play" verbs.</p>
     */
    public static final String LISTEN = "listen";

    /**
     * <p>Indicates that the actor has lost the object. For instance, if a person loses a game.</p>
     */
    public static final String LOSE = "lose";

    /**
     * <p>Indicates the creation of a friendship that is reciprocated by the object. Since this verb implies an activity on the part of its object, processors MUST NOT accept activities with this verb unless they are able to verify through some external means that there is in fact a reciprocated connection. For example, a processor may have received a guarantee from a particular publisher that the publisher will only use this Verb in cases where a reciprocal relationship exists.</p>
     */
    public static final String MAKE_FRIEND = "make-friend";

    /**
     * <p>Indicates that the actor has opened the object. For instance, the object could represent a ticket being tracked in an issue management system.</p>
     */
    public static final String OPEN = "open";

    /**
     * <p>Indicates that the actor spent some time enjoying the object. For example, if the object is a video this indicates that the subject watched all or part of the video. The "play" verb is a more specific form of the "consume" verb.</p>
     */
    public static final String PLAY = "play";

    /**
     * <p>Indicates that the actor has presented the object. For instance, when a person gives a presentation at a conference.</p>
     */
    public static final String PRESENT = "present";

    /**
     * <p>Indicates that the actor has purchased the object. If a target is specified, in indicates the entity from which the object was purchased.</p>
     */
    public static final String PURCHASE = "purchase";

    /**
     * <p>Indicates that the actor has qualified for the object. If a target is specified, it indicates the context within which the qualification applies.</p>
     */
    public static final String QUALIFY = "qualify";

    /**
     * <p>Indicates that the actor read the object. This is typically only applicable for objects representing printed or written content, such as a book, a message or a comment. The "read" verb is a more specific form of the "consume", "experience" and "play" verbs.</p>
     */
    public static final String READ = "read";

    /**
     * <p>Indicates that the actor is receiving an object. Examples include a person receiving a badge object. The object identifies the object being received.</p>
     */
    public static final String RECEIVE = "receive";

    /**
     * <p>Indicates that the actor has rejected the object.</p>
     */
    public static final String REJECT = "reject";

    /**
     * <p>Indicates that the actor has removed the object from the target.</p>
     */
    public static final String REMOVE = "remove";

    /**
     * <p>Indicates that the actor has removed the object from the collection of friends.</p>
     */
    public static final String REMOVE_FRIEND = "remove-friend";

    /**
     * <p>Indicates that the actor has replaced the target with the object.</p>
     */
    public static final String REPLACE = "replace";

    /**
     * <p>Indicates that the actor has requested the object. If a target is specified, it indicates the entity from which the object is being requested.</p>
     */
    public static final String REQUEST = "request";

    /**
     * <p>Indicates the creation of a friendship that has not yet been reciprocated by the object.</p>
     */
    public static final String REQUEST_FRIEND = "request-friend";

    /**
     * <p>Indicates that the actor has resolved the object. For instance, the object could represent a ticket being tracked in an issue management system.</p>
     */
    public static final String RESOLVE = "resolve";

    /**
     * <p>Indicates that the actor has returned the object. If a target is specified, it indicates the entity to which the object was returned.</p>
     */
    public static final String RETURN = "return";

    /**
     * <p>Indicates that the actor has retracted the object. For instance, if an actor wishes to retract a previously published activity, the object would be the previously published activity that is being retracted.</p>
     */
    public static final String RETRACT = "retract";

    /**
     * <p>The "possible RSVP" verb indicates that the actor has made a possible RSVP for the object. This specification only defines the meaning of this verb when its object is an event (see Section 3.3), though implementors need to be prepared to handle other object types. The use of this verb is only appropriate when the RSVP was created by an explicit action by the actor. It is not appropriate to use this verb when a user has been added as an attendee by an event organiser or administrator.</p>
     */
    public static final String RSVP_MAYBE = "rsvp-maybe";

    /**
     * <p>The "negative RSVP" verb indicates that the actor has made a negative RSVP for the object. This specification only defines the meaning of this verb when its object is an event (see Section 3.3), though implementors need to be prepared to handle other object types. The use of this verb is only appropriate when the RSVP was created by an explicit action by the actor. It is not appropriate to use this verb when a user has been added as an attendee by an event organiser or administrator.</p>
     */
    public static final String RSVP_NO = "rsvp-no";

    /**
     * <p>The "positive RSVP" verb indicates that the actor has made a positive RSVP for an object. This specification only defines the meaning of this verb when its object is an event (see Section 3.3), though implementors need to be prepared to handle other object types. The use of this verb is only appropriate when the RSVP was created by an explicit action by the actor. It is not appropriate to use this verb when a user has been added as an attendee by an event organiser or administrator.</p>
     */
    public static final String RSVP_YES = "rsvp-yes";

    /**
     * <p>Indicates that the actor has satisfied the object. If a target is specified, it indicate the context within which the object was satisfied. For instance, if a person satisfies the requirements for a particular challenge, the person is the actor; the requirement is the object; and the challenge is the target.</p>
     */
    public static final String SATISFY = "satisfy";

    /**
     * <p>Indicates that the actor has called out the object as being of interest primarily to him- or herself. Though this action MAY be shared publicly, the implication is that the object has been saved primarily for the actor's own benefit rather than to show it to others as would be indicated by the "share" verb.</p>
     */
    public static final String SAVE = "save";

    /**
     * <p>Indicates that the actor has scheduled the object. For instance, scheduling a meeting.</p>
     */
    public static final String SCHEDULE = "schedule";

    /**
     * <p>Indicates that the actor is or has searched for the object. If a target is specified, it indicates the context within which the search is or has been conducted.</p>
     */
    public static final String SEARCH = "search";

    /**
     * <p>Indicates that the actor has sold the object. If a target is specified, it indicates the entity to which the object was sold.</p>
     */
    public static final String SELL = "sell";

    /**
     * <p>Indicates that the actor has sent the object. If a target is specified, it indicates the entity to which the object was sent.</p>
     */
    public static final String SEND = "send";

    /**
     * <p>Indicates that the actor has called out the object to readers. In most cases, the actor did not create the object being shared, but is instead drawing attention to it.</p>
     */
    public static final String SHARE = "share";

    /**
     * <p>Indicates that the actor has sponsored the object. If a target is specified, it indicates the context within which the sponsorship is offered. For instance, a company can sponsor an event; or an individual can sponsor a project; etc.</p>
     */
    public static final String SPONSOR = "sponsor";

    /**
     * <p>Indicates that the actor has started the object. For instance, when a person starts a project.</p>
     */
    public static final String START = "start";

    /**
     * <p>Indicates that the actor has stopped following the object.</p>
     */
    public static final String STOP_FOLLOWING = "stop-following";

    /**
     * <p>Indicates that the actor has submitted the object. If a target is specified, it indicates the entity to which the object was submitted.</p>
     */
    public static final String SUBMIT = "submit";

    /**
     * <p>Indicates that the actor has associated the object with the target. For example, if the actor specifies that a particular user appears in a photo. the object is the user and the target is the photo.</p>
     */
    public static final String TAG = "tag";

    /**
     * <p>Indicates that the actor has terminated the object.</p>
     */
    public static final String TERMINATE = "terminate";

    /**
     * <p>Indicates that the actor has neither won or lost the object. This verb is generally only applicable when the object represents some form of competition, such as a game.</p>
     */
    public static final String TIE = "tie";

    /**
     * <p>Indicates that the actor has removed the object from the collection of favorited items.</p>
     */
    public static final String UNFAVORITE = "unfavorite";

    /**
     * <p>Indicates that the actor has removed the object from the collection of liked items.</p>
     */
    public static final String UNLIKE = "unlike";

    /**
     * <p>Indicates that the actor has not satisfied the object. If a target is specified, it indicates the context within which the object was not satisfied. For instance, if a person fails to satisfy the requirements of some particular challenge, the person is the actor; the requirement is the object and the challenge is the target.</p>
     */
    public static final String UNSATISFY = "unsatisfy";

    /**
     * <p>Indicates that the actor has removed the object from the collection of saved items.</p>
     */
    public static final String UNSAVE = "unsave";

    /**
     * <p>Indicates that the actor is no longer sharing the object. If a target is specified, it indicates the entity with whom the object is no longer being shared.</p>
     */
    public static final String UNSHARE = "unshare";

    /**
     * <p>The "update" verb indicates that the actor has modified the object. Use of the "update" verb is generally reserved to indicate modifications to existing objects or data such as changing an existing user's profile information.</p>
     */
    public static final String UPDATE = "update";

    /**
     * <p>Indicates that the actor has used the object in some manner.</p>
     */
    public static final String USE = "use";

    /**
     * <p>Indicates that the actor has watched the object. This verb is typically applicable only when the object represents dynamic, visible content such as a movie, a television show or a public performance. This verb is a more specific form of the verbs "experience", "play" and "consume".</p>
     */
    public static final String WATCH = "watch";

    /**
     * <p>Indicates that the actor has won the object. This verb is typically applicable only when the object represents some form of competition, such as a game.</p>
     */
    public static final String WIN = "win";
}

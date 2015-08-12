package io.github.hhromic.uimr.as;

import java.util.Date;

/**
 * <p>This class represents an Activity Streams Object.</p>
 *
 * <p>This class is a direct representation of the JSON Serialization provided from the
 * Activity Streams <a href="http://activitystrea.ms">website</a> in
 * <a href="http://activitystrea.ms/specs/json/1.0/#object">http://activitystrea.ms/specs/json/1.0/#object</a>.</p>
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public class ASObject {
    /**
     * <p>A collection of one or more additional, associated objects, similar to the concept of attached files in an email message.
     * An {@code ASObject} MAY have an {@code attachments} property whose value is an Array of {@code ASObject}s.</p>
     */
    protected ASObject[] attachments = null;

    /**
     * <p>Describes the entity that created or authored the {@code ASObject}. An {@code ASObject} MAY contain a single {@code author}
     * property whose value is an {@code ASObject} of any type. Note that the {@code author} field identifies the entity that created
     * the {@code ASObject} and does not necessarily identify the entity that published the {@code ASObject}. For instance, it may be
     * the case that an object created by one person is posted and published to a system by an entirely different entity.</p>
     */
    protected ASObject author = null;

    /**
     * <p>Natural-language description of the {@code ASObject} encoded as a single String containing HTML markup. Visual elements such
     * as thumbnail images MAY be included. An {@code ASObject} MAY contain a {@code content} property.</p>
     */
    protected String content = null;

    /**
     * <p>A natural-language, human-readable and plain-text name for the {@code ASObject}. HTML markup MUST NOT be included.
     * An {@code ASObject} MAY contain a {@code displayName} property. If the {@code ASObject} does not specify an {@link #objectType}
     * property, the {@code ASObject} SHOULD specify a {@code displayName}.</p>
     */
    protected String displayName = null;

    /**
     * <p>An Array of one or more absolute IRI's [RFC3987] identifying {@code ASObject}s that duplicate this {@code ASObject}'s content.
     * An {@code ASObject} SHOULD contain a {@code downstreamDuplicates} property when there are known {@code ASObjects}, possibly in a
     * different system, that duplicate the content in this {@code ASObject}. This MAY be used as a hint for consumers to use when
     * resolving duplicates between {@code ASObjects} received from different sources.</p>
     */
    protected String[] downstreamDuplicates = null;

    /**
     * <p>Provides a permanent, universally unique identifier for the {@code ASObject} in the form of an absolute IRI [RFC3987]. An
     * {@code ASObject} SHOULD contain a single {@code id} property. If an {@code ASObject} does not contain an {@code id} property,
     * consumers MAY use the value of the {@link #url} property as a less-reliable, non-unique identifier.</p>
     */
    protected String id = null;

    /**
     * <p>Description of a resource providing a visual representation of the {@code ASObject}, intended for human consumption. An
     * {@code ASObject} MAY contain an {@code image} property whose value is a {@link MediaLink}.</p>
     */
    protected MediaLink image = null;

    /**
     * <p>Identifies the type of {@code ASObject}. An {@code ASObject} MAY contain an {@code objectType} property whose value is
     * a String that is non-empty and matches either the "isegment-nz-nc" or the "IRI" production in [RFC3987]. Note that the use
     * of a relative reference other than a simple name is not allowed. If no {@code objectType} property is contained, the
     * {@code ASObject} has no specific type.</p>
     */
    protected String objectType = null;

    /**
     * <p>The date and time at which the {@code ASObject} was published. An {@code ASObject} MAY contain a {@code published} property.</p>
     */
    protected Date published = null;

    /**
     * <p>Natural-language summarization of the {@code ASObject} encoded as a single String containing HTML markup. Visual elements such
     * as thumbnail images MAY be included. An {@link Activity} MAY contain a {@code summary} property.</p>
     */
    protected String summary = null;

    /**
     * <p>The date and time at which a previously published {@code ASOject} has been modified.
     * An {@code ASObject} MAY contain an {@code updated} property.</p>
     */
    protected Date updated = null;

    /**
     * <p>An Array of one or more absolute IRI's [RFC3987] identifying {@code ASObject}s that duplicate this {@code ASObject}'s content.
     * An {@code ASObject} SHOULD contain an {@code upstreamDuplicates} property when a publisher is knowingly duplicating with a new ID
     * the content from another {@code ASObject}. This MAY be used as a hint for consumers to use when resolving duplicates between
     * {@code ASObject}s received from different sources.</p>
     */
    protected String[] upstreamDuplicates = null;

    /**
     * <p>An IRI [RFC3987] identifying a resource providing an HTML representation of the {@code ASObject}.
     * An {@code ASObject} MAY contain a {@code url} property.</p>
     */
    protected String url = null;

    /**
     * <p>Gets the {@code attachments} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code attachments} property
     */
    public ASObject[] getAttachments() {
        return attachments;
    }

    /**
     * <p>Sets the {@code attachments} property for this {@code ASObject}.</p>
     *
     * @param attachments the value to set to the {@code attachments} property
     * @return this {@code ASObject} object
     */
    public ASObject setAttachments(final ASObject[] attachments) {
        this.attachments = attachments;
        return this;
    }

    /**
     * <p>Gets the {@code author} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code author} property
     */
    public ASObject getAuthor() {
        return author;
    }

    /**
     * <p>Sets the {@code author} property for this {@code ASObject}.</p>
     *
     * @param author the value to set to the {@code author} property
     * @return this {@code ASObject} object
     */
    public ASObject setAuthor(final ASObject author) {
        this.author = author;
        return this;
    }

    /**
     * <p>Gets the {@code content} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code content} property
     */
    public String getContent() {
        return content;
    }

    /**
     * <p>Sets the {@code content} property for this {@code ASObject}.</p>
     *
     * @param content the value to set to the {@code content} property
     * @return this {@code ASObject} object
     */
    public ASObject setContent(final String content) {
        this.content = content;
        return this;
    }

    /**
     * <p>Gets the {@code displayName} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code displayName} property
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * <p>Sets the {@code displayName} property for this {@code ASObject}.</p>
     *
     * @param displayName the value to set to the {@code displayName} property
     * @return this {@code ASObject} object
     */
    public ASObject setDisplayName(final String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * <p>Gets the {@code downstreamDuplicates} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code downstreamDuplicates} property
     */
    public String[] getDownstreamDuplicates() {
        return downstreamDuplicates;
    }

    /**
     * <p>Sets the {@code downstreamDuplicates} property for this {@code ASObject}.</p>
     *
     * @param downstreamDuplicates the value to set to the {@code downstreamDuplicates} property
     * @return this {@code ASObject} object
     */
    public ASObject setDownstreamDuplicates(final String[] downstreamDuplicates) {
        this.downstreamDuplicates = downstreamDuplicates;
        return this;
    }

    /**
     * <p>Gets the {@code id} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code id} property
     */
    public String getId() {
        return id;
    }

    /**
     * <p>Sets the {@code id} property for this {@code ASObject}.</p>
     *
     * @param id the value to set to the {@code id} property
     * @return this {@code ASObject} object
     */
    public ASObject setId(final String id) {
        this.id = id;
        return this;
    }

    /**
     * <p>Gets the {@code image} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code image} property
     */
    public MediaLink getImage() {
        return image;
    }

    /**
     * <p>Sets the {@code image} property for this {@code ASObject}.</p>
     *
     * @param image the value to set to the {@code image} property
     * @return this {@code ASObject} object
     */
    public ASObject setImage(final MediaLink image) {
        this.image = image;
        return this;
    }

    /**
     * <p>Gets the {@code objectType} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code objectType} property
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * <p>Sets the {@code objectType} property for this {@code ASObject}.</p>
     *
     * @param objectType the value to set to the {@code objectType} property
     * @return this {@code ASObject} object
     */
    public ASObject setObjectType(final String objectType) {
        this.objectType = objectType;
        return this;
    }

    /**
     * <p>Gets the {@code published} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code published} property
     */
    public Date getPublished() {
        return published;
    }

    /**
     * <p>Sets the {@code published} property for this {@code ASObject}.</p>
     *
     * @param published the value to set to the {@code published} property
     * @return this {@code ASObject} object
     */
    public ASObject setPublished(final Date published) {
        this.published = published;
        return this;
    }

    /**
     * <p>Gets the {@code summary} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code summary} property
     */
    public String getSummary() {
        return summary;
    }

    /**
     * <p>Sets the {@code summary} property for this {@code ASObject}.</p>
     *
     * @param summary the value to set to the {@code summary} property
     * @return this {@code ASObject} object
     */
    public ASObject setSummary(final String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * <p>Gets the {@code updated} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code updated} property
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * <p>Sets the {@code updated} property for this {@code ASObject}.</p>
     *
     * @param updated the value to set to the {@code updated} property
     * @return this {@code ASObject} object
     */
    public ASObject setUpdated(final Date updated) {
        this.updated = updated;
        return this;
    }

    /**
     * <p>Gets the {@code upstreamDuplicates} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code upstreamDuplicates} property
     */
    public String[] getUpstreamDuplicates() {
        return upstreamDuplicates;
    }

    /**
     * <p>Sets the {@code upstreamDuplicates} property for this {@code ASObject}.</p>
     *
     * @param upstreamDuplicates the value to set to the {@code upstreamDuplicates} property
     * @return this {@code ASObject} object
     */
    public ASObject setUpstreamDuplicates(final String[] upstreamDuplicates) {
        this.upstreamDuplicates = upstreamDuplicates;
        return this;
    }

    /**
     * <p>Gets the {@code url} property for this {@code ASObject}.</p>
     *
     * @return the value of the {@code url} property
     */
    public String getUrl() {
        return url;
    }

    /**
     * <p>Sets the {@code url} property for this {@code ASObject}.</p>
     *
     * @param url the value to set to the {@code url} property
     * @return this {@code ASObject} object
     */
    public ASObject setUrl(final String url) {
        this.url = url;
        return this;
    }
}

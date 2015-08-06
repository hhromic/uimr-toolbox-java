package io.github.hhromic.uimr.as;

/**
 * <p>This class represents an Activity Streams Media Link.</p>
 *
 * <p>This class is a direct representation of the JSON Serialization provided from the
 * Activity Streams <a href="http://activitystrea.ms">website</a> in
 * <a href="http://activitystrea.ms/specs/json/1.0/#media-link">http://activitystrea.ms/specs/json/1.0/#media-link</a>.</p>
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public class MediaLink {
    /**
     * <p>A hint to the consumer about the length, in seconds, of the media resource identified by the {@link #url} property.
     * A {@code MediaLink} MAY contain a {@code duration} property when the target resource is a time-based media item
     * such as an audio or video.</p>
     */
    protected Integer duration = null;

    /**
     * <p>A hint to the consumer about the height, in pixels, of the media resource identified by the {@link #url} property.
     * A {@code MediaLink} MAY contain a {@code height} property when the target resource is a visual media item
     * such as an image, video or embeddable HTML page.</p>
     */
    protected Integer height = null;

    /**
     * <p>The IRI of the media resource being linked. A {@code MediaLink} MUST have a {@code url} property.</p>
     */
    protected String url = null;

    /**
     * <p>A hint to the consumer about the width, in pixels, of the media resource identified by the {@link #url} property.
     * A {@code MediaLink} MAY contain a {@code width} property when the target resource is a visual media item
     * such as an image, video or embeddable HTML page.</p>
     */
    protected Integer width = null;

    /**
     * <p>Gets the {@code duration} property for this {@code MediaLink}.</p>
     *
     * @return the value of the {@code duration} property.
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * <p>Sets the {@code duration} property for this {@code MediaLink}.</p>
     *
     * @param duration the value to set to the {@code duration} property.
     * @return this {@code MediaLink} object.
     */
    public MediaLink setDuration(final Integer duration) {
        this.duration = duration;
        return this;
    }

    /**
     * <p>Gets the {@code height} property for this {@code MediaLink}.</p>
     *
     * @return the value of the {@code height} property.
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * <p>Sets the {@code height} property for this {@code MediaLink}.</p>
     *
     * @param height the value to set to the {@code height} property.
     * @return this {@code MediaLink} object.
     */
    public MediaLink setHeight(final Integer height) {
        this.height = height;
        return this;
    }

    /**
     * <p>Gets the {@code url} property for this {@code MediaLink}.</p>
     *
     * @return the value of the {@code url} property.
     */
    public String getUrl() {
        return url;
    }

    /**
     * <p>Sets the {@code url} property for this {@code MediaLink}.</p>
     *
     * @param url the value to set to the {@code url} property.
     * @return this {@code MediaLink} object.
     */
    public MediaLink setUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * <p>Gets the {@code width} property for this {@code MediaLink}.</p>
     *
     * @return the value of the {@code width} property.
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * <p>Sets the {@code width} property for this {@code MediaLink}.</p>
     *
     * @param width the value to set to the {@code width} property.
     * @return this {@code MediaLink} object.
     */
    public MediaLink setWidth(final Integer width) {
        this.width = width;
        return this;
    }
}

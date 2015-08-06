package io.github.hhromic.uimr.as;

/**
 * <p>This class represents an Activity Streams Objects Collection.</p>
 *
 * <p>This class is a direct representation of the JSON Serialization provided from the
 * Activity Streams <a href="http://activitystrea.ms">website</a> in
 * <a href="http://activitystrea.ms/specs/json/1.0/#collection">http://activitystrea.ms/specs/json/1.0/#collection</a>.</p>
 *
 * <p>A {@code Collection} is a generic list of {@link ASObject}s of any object type. The {@link ASObject#objectType} of each item
 * in the collection MAY be omitted if the type of object can be established through context. The collection is used primarily as
 * the root of an Activity Streams document, but can be used as the value of extension properties in a variety of situations.</p>
 *
 * <p>A valid {@code Collection} MUST contain at least the {@link #url} or {@link #items} properties.</p>
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public class Collection {
    /**
     * <p>Non-negative integer specifying the total number of activities within the stream.
     * The Stream serialization MAY contain a {@code totalItems} property.</p>
     */
    protected Integer totalItems = null;

    /**
     * <p>An array containing a listing of {@link ASObject}s of any object type. If used in combination with the
     * {@link #url} property, the items array can be used to provide a subset of the {@link ASObject}s that may be found
     * in the resource identified by the {@link #url}.</p>
     */
    protected ASObject[] items = null;

    /**
     * <p>An IRI [RFC3987] referencing a document containing the full listing of objects in the collection.</p>
     */
    protected String url = null;

    /**
     * <p>Gets the {@code totalItems} property for this {@code Collection}.</p>
     *
     * @return the value of the {@code totalItems} property.
     */
    public Integer getTotalItems() {
        return totalItems;
    }

    /**
     * <p>Sets the {@code totalItems} property for this {@code Collection}.</p>
     *
     * @param totalItems the value to set to the {@code totalItems} property.
     * @return this {@code Collection} object.
     */
    public Collection setTotalItems(final Integer totalItems) {
        this.totalItems = totalItems;
        return this;
    }

    /**
     * <p>Gets the {@code items} property for this {@code Collection}.</p>
     *
     * @return the value of the {@code items} property.
     */
    public ASObject[] getItems() {
        return items;
    }

    /**
     * <p>Sets the {@code items} property for this {@code Collection}.</p>
     *
     * @param items the value to set to the {@code items} property.
     * @return this {@code Collection} object.
     */
    public Collection setItems(final ASObject[] items) {
        this.items = items;
        return this;
    }

    /**
     * <p>Gets the {@code url} property for this {@code Collection}.</p>
     *
     * @return the value of the {@code url} property.
     */
    public String getUrl() {
        return url;
    }

    /**
     * <p>Sets the {@code url} property for this {@code Collection}.</p>
     *
     * @param url the value to set to the {@code url} property.
     * @return this {@code Collection} object.
     */
    public Collection setUrl(final String url) {
        this.url = url;
        return this;
    }
}

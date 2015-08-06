package io.github.hhromic.uimr.as;

/**
 * <p>This class represents an Activity Streams Activity Object.</p>
 *
 * <p>This class is a direct representation of the JSON Serialization provided from the
 * Activity Streams <a href="http://activitystrea.ms">website</a> in
 * <a href="http://activitystrea.ms/specs/json/1.0/#activity">http://activitystrea.ms/specs/json/1.0/#activity</a>.</p>
 *
 * <p>Fields borrowed from {@link ASObject}:</p>
 * <ul>
 * <li>{@link ASObject#content}</li>
 * <li>{@link ASObject#id}</li>
 * <li>{@link ASObject#published}</li>
 * <li>{@link ASObject#updated}</li>
 * <li>{@link ASObject#url}</li>
 * </ul>
 *
 * @author Hugo Hromic
 * @since 4.0
 */
public class Activity extends ASObject {
    /**
     * <p>Describes the entity that performed the activity. An {@code Activity} MUST contain one
     * {@code actor} property whose value is a single {@link ASObject}.</p>
     */
    protected ASObject actor = null;

    /**
     * <p>Describes the application that generated the activity. An {@code Activity} MAY contain a
     * {@code generator} property whose value is a single {@link ASObject}.</p>
     */
    protected ASObject generator = null;

    /**
     * <p>Description of a resource providing a visual representation of the object, intended for human consumption.
     * The image SHOULD have an aspect ratio of one (horizontal) to one (vertical) and SHOULD be suitable for presentation
     * at a small size. An {@code Activity} MAY have an {@code icon} property.</p>
     */
    protected MediaLink icon = null;

    /**
     * <p>Describes the primary object of the activity. For instance, in the activity, "John saved a movie to his wishlist",
     * the object of the activity is "movie". An {@code Activity} SHOULD contain an {@code object} property whose value
     * is a single {@link ASObject}. If the {@code object} property is not contained, the primary object of the
     * {@code Activity} MAY be implied by context.</p>
     */
    protected ASObject object = null;

    /**
     * <p>Describes the application that published the activity. Note that this is not necessarily the same entity that generated
     * the activity. An {@code Activity} MAY contain a {@code provider} property whose value is a single {@link ASObject}.</p>
     */
    protected ASObject provider = null;

    /**
     * <p>Describes the target of the activity. The precise meaning of the activity's target is dependent on the activities verb,
     * but will often be the object the English preposition "to". For instance, in the activity, "John saved a movie to his wishlist",
     * the target of the activity is "wishlist". The activity target MUST NOT be used to identity an indirect object that is not
     * a target of the activity. An {@code Activity} MAY contain a {@code target} property whose value is a single {@link ASObject}.</p>
     */
    protected ASObject target = null;

    /**
     * <p>Natural-language title or headline for the activity encoded as a single String containing HTML markup.
     * An {@code Activity} MAY contain a {@code title} property.</p>
     */
    protected String title = null;

    /**
     * <p>Identifies the action that the activity describes. An {@code Activity} SHOULD contain a {@code verb} property whose value is
     * a String that is non-empty and matches either the "isegment-nz-nc" or the "IRI" production in [RFC3339]. Note that the use of a
     * relative reference other than a simple name is not allowed. If the {@code verb} is not specified, or if the value is {@code null}
     *, the verb is assumed to be "{@code post}".</p>
     */
    protected String verb = null;

    /**
     * <p>Gets the {@code actor} property for this {@code Activity}.</p>
     *
     * @return the value of the {@code actor} property.
     */
    public ASObject getActor() {
        return actor;
    }

    /**
     * <p>Sets the {@code actor} property for this {@code Activity}.</p>
     *
     * @param actor the value to set to the {@code actor} property.
     * @return this {@code Activity} object.
     */
    public Activity setActor(final ASObject actor) {
        this.actor = actor;
        return this;
    }

    /**
     * <p>Gets the {@code generator} property for this {@code Activity}.</p>
     *
     * @return the value of the {@code generator} property.
     */
    public ASObject getGenerator() {
        return generator;
    }

    /**
     * <p>Sets the {@code generator} property for this {@code Activity}.</p>
     *
     * @param generator the value to set to the {@code generator} property.
     * @return this {@code Activity} object.
     */
    public Activity setGenerator(final ASObject generator) {
        this.generator = generator;
        return this;
    }

    /**
     * <p>Gets the {@code icon} property for this {@code Activity}.</p>
     *
     * @return the value of the {@code icon} property.
     */
    public MediaLink getIcon() {
        return icon;
    }

    /**
     * <p>Sets the {@code icon} property for this {@code Activity}.</p>
     *
     * @param icon the value to set to the {@code icon} property.
     * @return this {@code Activity} object.
     */
    public Activity setIcon(final MediaLink icon) {
        this.icon = icon;
        return this;
    }

    /**
     * <p>Gets the {@code object} property for this {@code Activity}.</p>
     *
     * @return the value of the {@code object} property.
     */
    public ASObject getObject() {
        return object;
    }

    /**
     * <p>Sets the {@code object} property for this {@code Activity}.</p>
     *
     * @param object the value to set to the {@code object} property.
     * @return this {@code Activity} object.
     */
    public Activity setObject(final ASObject object) {
        this.object = object;
        return this;
    }

    /**
     * <p>Gets the {@code provider} property for this {@code Activity}.</p>
     *
     * @return the value of the {@code provider} property.
     */
    public ASObject getProvider() {
        return provider;
    }

    /**
     * <p>Sets the {@code provider} property for this {@code Activity}.</p>
     *
     * @param provider the value to set to the {@code provider} property.
     * @return this {@code Activity} object.
     */
    public Activity setProvider(final ASObject provider) {
        this.provider = provider;
        return this;
    }

    /**
     * <p>Gets the {@code target} property for this {@code Activity}.</p>
     *
     * @return the value of the {@code target} property.
     */
    public ASObject getTarget() {
        return target;
    }

    /**
     * <p>Sets the {@code target} property for this {@code Activity}.</p>
     *
     * @param target the value to set to the {@code target} property.
     * @return this {@code Activity} object.
     */
    public Activity setTarget(final ASObject target) {
        this.target = target;
        return this;
    }

    /**
     * <p>Gets the {@code title} property for this {@code Activity}.</p>
     *
     * @return the value of the {@code title} property.
     */
    public String getTitle() {
        return title;
    }

    /**
     * <p>Sets the {@code title} property for this {@code Activity}.</p>
     *
     * @param title the value to set to the {@code title} property.
     * @return this {@code Activity} object.
     */
    public Activity setTitle(final String title) {
        this.title = title;
        return this;
    }

    /**
     * <p>Gets the {@code verb} property for this {@code Activity}.</p>
     *
     * @return the value of the {@code verb} property.
     */
    public String getVerb() {
        return verb;
    }

    /**
     * <p>Sets the {@code verb} property for this {@code Activity}.</p>
     *
     * @param verb the value to set to the {@code verb} property.
     * @return this {@code Activity} object.
     */
    public Activity setVerb(final String verb) {
        this.verb = verb;
        return this;
    }
}

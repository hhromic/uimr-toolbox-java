package io.github.hhromic.uimr.twitter;

/**
 * A very simple Twitter user representation class.
 *
 * <p><strong>Example usage:</strong></p>
 *
 * <pre>{@code
 * import io.github.hhromic.uimr.twitter.SimpleUser;
 *
 * public class MyClass {
 *     public static void main(final String[] args) {
 *         final SimpleUser user = new SimpleUser(5678, "testuser");
 *         user.setId(1234);
 *         System.out.println(user);
 *     }
 * }
 * }</pre>
 *
 * @since 1.0
 */
public class SimpleUser {
    /** Internal id for this simple user. */
    protected Long id;

    /** Internal screen name for this simple user. */
    protected String screenName;

    /**
     * Gets the ID of this simple user.
     *
     * @return the ID of this simple user
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of this simple user.
     *
     * @param id the ID to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the screen name of this simple user.
     *
     * @return the screen name of this simple user
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * Sets the screen name of this simple user.
     *
     * @param screenName the screen name to set
     */
    public void setScreenName(final String screenName) {
        this.screenName = screenName;
    }

    /**
     * Creates a simple user instance.
     *
     * @param id the ID of the user
     * @param screenName the screen name of the user
     */
    public SimpleUser(final Long id, final String screenName) {
        this.id = id;
        this.screenName = screenName;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("<SimpleUser %d '%s'>", id, screenName);
    }

    /** {@inheritDoc}
     * <p>The simple user objects are compared according to their {@code id} field.</p>
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof SimpleUser)) return false;
        final SimpleUser otherSimpleUser = (SimpleUser)obj;
        return id != null && id.equals(otherSimpleUser.id);
    }

    /** {@inheritDoc}
     * <p>The hash code is generated using this simple user {@code id} field.</p>
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
